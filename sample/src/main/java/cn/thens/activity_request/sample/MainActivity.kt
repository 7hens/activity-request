package cn.thens.activity_request.sample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.thens.activity_request.ActivityRequest
import cn.thens.activity_request.AndroidPermissions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vStartActivity.setOnClickListener {
            launch { startActivitySample() }
        }
        vStartActivity2.setOnClickListener {
            launch { applicationContext.startActivitySample() }
        }
        vRequestPermissions.setOnClickListener {
            launch { requestPermissionsSample() }
        }
        vRequestPermissions2.setOnClickListener {
            launch { applicationContext.requestPermissionsSample() }
        }
    }

    private suspend fun Context.startActivitySample() {
        val intent = Intent(this, TargetActivity::class.java)
        val (code, data) = ActivityRequest(this).result(intent)
        when (code) {
            Activity.RESULT_OK -> toast("result: ${data.action}")
            Activity.RESULT_CANCELED -> toast("canceled")
        }
    }

    private suspend fun Context.requestPermissionsSample() {
        val storagePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        val cameraPermission = android.Manifest.permission.CAMERA
//        val result = AndroidPermissions.request(this, storagePermission)
//        toast("storage: $result")
        val result = AndroidPermissions.requestAll(this, storagePermission, cameraPermission)
        toast("storage: ${result[storagePermission]}, camera: ${result[cameraPermission]}")
    }

    private fun toast(text: CharSequence) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}
