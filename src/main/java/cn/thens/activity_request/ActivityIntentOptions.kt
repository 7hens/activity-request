package cn.thens.activity_request

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi

/**
 * @author 7hens
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
interface ActivityIntentOptions<out Options> : IntentExtra.CompanionOptions<Options> {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun start(context: Context, bundle: Bundle?, configure: Options.(Intent) -> Unit) {
        context.startActivity(intent(context, configure), bundle)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun start(context: Context, bundle: Bundle?) {
        start(context, bundle) {}
    }

    fun start(context: Context, configure: Options.(Intent) -> Unit) {
        context.startActivity(intent(context, configure))
    }

    fun start(context: Context) {
        start(context) {}
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun startForResult(context: Activity, requestCode: Int, bundle: Bundle?, configure: Options.(Intent) -> Unit) {
        context.startActivityForResult(intent(context, configure), requestCode, bundle)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun startForResult(context: Activity, requestCode: Int, bundle: Bundle?) {
        startForResult(context, requestCode, bundle) {}
    }

    fun startForResult(context: Activity, requestCode: Int, configure: Options.(Intent) -> Unit) {
        context.startActivityForResult(intent(context, configure), requestCode)
    }

    fun startForResult(context: Activity, requestCode: Int) {
        startForResult(context, requestCode) {}
    }

    suspend fun startForResult(
        context: Context, bundle: Bundle? = null,
        configure: Options.(Intent) -> Unit = {}): ActivityRequest.Result {
        return ActivityRequest(context).startForResult(intent(context, configure), bundle)
    }

    suspend fun startForResult(context: Context,
                               intent: IntentSender, flagsMask: Int,
                               flagsValues: Int, extraFlags: Int, options: Bundle? = null,
                               configure: Options.(Intent) -> Unit = {}): ActivityRequest.Result {
        return ActivityRequest(context)
            .startForResult(intent, intent(context, configure), flagsMask, flagsValues, extraFlags, options)
    }

}