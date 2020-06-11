package cn.thens.activity_request

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object AndroidPermissions {
    suspend fun requestAll(context: Context, vararg permissions: String): Map<String, Boolean> {
        val result = mutableMapOf<String, Boolean>()
        val notSurePermissions = mutableListOf<String>()
        permissions.forEach { permission ->
            if (isGranted(context, permission)) {
                result[permission] = true
            } else {
                result[permission] = false
                notSurePermissions.add(permission)
            }
        }
        if (notSurePermissions.isNotEmpty()) {
            ActivityRequest(context).permissions(*notSurePermissions.toTypedArray()).toMap(result)
        }
        return result
    }

    suspend fun request(context: Context, permission: String): Boolean {
        if (isGranted(context, permission)) return true
        return ActivityRequest(context).permissions(permission)[permission] == true
    }

    fun shouldShowRationale(activity: Activity, permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }

    fun isGranted(context: Context, vararg permissions: String): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }
}