package cn.thens.activity_request.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_target.*

/**
 * @author 7hens
 */
class TargetActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        vReturnHello.setOnClickListener {
            setResult(RESULT_OK, Intent("hello"))
            finish()
        }
        vCancel.setOnClickListener {
            finish()
        }
    }
}