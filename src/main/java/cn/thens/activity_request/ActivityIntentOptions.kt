package cn.thens.activity_request

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import android.os.Bundle

/**
 * @author 7hens
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
interface ActivityIntentOptions<out T> : IntentExtra.Options<T> {
    fun start(context: Context, options: Bundle? = null, configure: T.(Intent) -> Unit = {}) {
        val intent = intent(context, configure)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            context.startActivity(intent, options)
        } else {
            context.startActivity(intent)
        }
    }

    fun start(context: Context, intentSender: IntentSender, flagsMask: Int,
              flagsValues: Int, extraFlags: Int, options: Bundle? = null,
              configure: T.(Intent) -> Unit = {}) {
        val intent = intent(context, configure)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            context.startIntentSender(intentSender, intent, flagsMask, flagsValues, extraFlags, options)
        } else {
            context.startIntentSender(intentSender, intent, flagsMask, flagsValues, extraFlags)
        }
    }

    suspend fun startForResult(
        context: Context, bundle: Bundle? = null,
        configure: T.(Intent) -> Unit = {}): ActivityRequest.Result {
        return ActivityRequest(context).startForResult(intent(context, configure), bundle)
    }

    suspend fun startForResult(context: Context,
                               intent: IntentSender, flagsMask: Int,
                               flagsValues: Int, extraFlags: Int, options: Bundle? = null,
                               configure: T.(Intent) -> Unit = {}): ActivityRequest.Result {
        return ActivityRequest(context)
            .startForResult(intent, intent(context, configure), flagsMask, flagsValues, extraFlags, options)
    }
}