package cn.thens.activity_request

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.SparseArray
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ActivityRequest(private val context: Context) {
    @Suppress("UNCHECKED_CAST")
    private suspend fun <T> request(request: Fragment.(requestCode: Int) -> Unit): T {
        return suspendCoroutine<Any> {
            Contract(request, it).launch(context, ViewCompat.generateViewId())
        } as T
    }

    suspend fun startForResult(intent: Intent, options: Bundle? = null): Result {
        return request { startActivityForResult(intent, it, options) }
    }

    suspend fun startForResult(intent: IntentSender, fillInIntent: Intent, flagsMask: Int,
                               flagsValues: Int, extraFlags: Int, options: Bundle? = null): Result {
        return request {
            startIntentSenderForResult(intent, it, fillInIntent, flagsMask,
                flagsValues, extraFlags, options)
        }
    }

    suspend fun requestPermissions(vararg permissions: String): Map<String, Boolean> {
        return request { requestPermissions(permissions, it) }
    }

    private data class Contract(
        val request: Fragment.(requestCode: Int) -> Unit,
        val continuation: Continuation<Any>
    ) {
        private var isRequested = false

        fun launch(context: Context, requestCode: Int) {
            if (isRequested) return
            try {
                contracts.put(requestCode, this)
                if (context is FragmentActivity) {
                    isRequested = true
                    context.supportFragmentManager.run {
                        findFragmentByTag(FRAGMENT_TAG) ?: BridgeFragment().also {
                            beginTransaction().add(it, FRAGMENT_TAG).commitNow()
                        }
                    }.request(requestCode)
                } else {
                    context.startActivity(
                        Intent(context, BridgeActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra(REQUEST_CODE, requestCode)
                    )
                }
            } catch (e: Throwable) {
                contracts.remove(requestCode)
                continuation.resumeWithException(e)
            }
        }
    }

    data class Result(val code: Int, val data: Intent)

    companion object {
        private const val FRAGMENT_TAG = "kotlin.ActivityRequest"
        private const val REQUEST_CODE = "REQUEST_CODE"
        private val contracts = SparseArray<Contract>()
    }

    class BridgeActivity : FragmentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            overridePendingTransition(0, 0)
            window.attributes.flags = window.attributes.flags or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            super.onCreate(savedInstanceState)
            launch(intent)
        }

        override fun onNewIntent(intent: Intent?) {
            overridePendingTransition(0, 0)
            super.onNewIntent(intent)
            launch(intent!!)
        }

        private fun launch(intent: Intent) {
            val requestCode = intent.getIntExtra(REQUEST_CODE, 0)
            contracts[requestCode]?.launch(this, requestCode)
        }

        override fun finish() {
            super.finish()
            overridePendingTransition(0, 0)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            moveTaskToBack(true)
        }
    }

    class BridgeFragment : Fragment() {
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            onResult(requestCode, Result(resultCode, data ?: Intent()))
        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            onResult(requestCode, mutableMapOf<String, Boolean>().also {
                permissions.forEachIndexed { index, permission ->
                    it[permission] = grantResults[index] == PackageManager.PERMISSION_GRANTED
                }
            })
        }

        private fun onResult(requestCode: Int, result: Any) {
            contracts[requestCode]?.continuation?.resume(result)
            contracts.remove(requestCode)
        }
    }
}