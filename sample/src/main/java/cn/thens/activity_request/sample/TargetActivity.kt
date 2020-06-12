package cn.thens.activity_request.sample

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import cn.thens.activity_request.ActivityIntentOptions
import kotlinx.android.synthetic.main.activity_target.*

/**
 * @author 7hens
 */
class TargetActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        Log.d("ActivityRequest", "actionName: ${intent.actionName}")
        Log.d("ActivityRequest", "hello: ${intent.hello}")
        Log.d("ActivityRequest", "activityInfo: ${intent.activityInfo}")
        vReturnHello.setOnClickListener {
            setResult(RESULT_OK, Intent("hello"))
            finish()
        }
        vCancel.setOnClickListener {
            finish()
        }
    }

    companion object : ActivityIntentOptions<Companion> {
        var Intent.actionName by string()
        var Intent.hello by boolean()
        var Intent.activityInfo by parcelable<ActivityInfo>()
    }
}