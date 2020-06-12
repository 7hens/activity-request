package cn.thens.activity_request

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import kotlin.properties.ReadWriteProperty

/**
 * @author 7hens
 */
@Suppress("unused")
interface IntentExtra {

    fun boolean(name: String? = null, defaultValue: Boolean = false): ReadWriteProperty<Intent, Boolean> {
        return property(name, defaultValue, Intent::getBooleanExtra) { k, v -> putExtra(k, v) }
    }

    fun booleanArray(name: String? = null): ReadWriteProperty<Intent, BooleanArray?> {
        return property(name, Intent::getBooleanArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun bundle(name: String? = null): ReadWriteProperty<Intent, Bundle?> {
        return property(name, Intent::getBundleExtra) { k, v -> putExtra(k, v) }
    }

    fun byte(name: String? = null, defaultValue: Byte = 0): ReadWriteProperty<Intent, Byte> {
        return property(name, defaultValue, Intent::getByteExtra) { k, v -> putExtra(k, v) }
    }

    fun byteArray(name: String? = null): ReadWriteProperty<Intent, ByteArray?> {
        return property(name, Intent::getByteArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun char(name: String? = null, defaultValue: Char = Char.MIN_VALUE): ReadWriteProperty<Intent, Char> {
        return property(name, defaultValue, Intent::getCharExtra) { k, v -> putExtra(k, v) }
    }

    fun charArray(name: String? = null): ReadWriteProperty<Intent, CharArray?> {
        return property(name, Intent::getCharArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun charSequence(name: String? = null): ReadWriteProperty<Intent, CharSequence?> {
        return property(name, Intent::getCharSequenceExtra) { k, v -> putExtra(k, v) }
    }

    fun charSequenceArray(name: String? = null): ReadWriteProperty<Intent, Array<CharSequence>?> {
        return property(name, Intent::getCharSequenceArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun charSequenceArrayList(name: String? = null): ReadWriteProperty<Intent, java.util.ArrayList<CharSequence>?> {
        return property(name, Intent::getCharSequenceArrayListExtra) { k, v -> putExtra(k, v) }
    }


    fun double(name: String? = null, defaultValue: Double = 0.0): ReadWriteProperty<Intent, Double> {
        return property(name, defaultValue, Intent::getDoubleExtra) { k, v -> putExtra(k, v) }
    }

    fun doubleArray(name: String? = null): ReadWriteProperty<Intent, DoubleArray?> {
        return property(name, Intent::getDoubleArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun float(name: String? = null, defaultValue: Float = 0F): ReadWriteProperty<Intent, Float> {
        return property(name, defaultValue, Intent::getFloatExtra) { k, v -> putExtra(k, v) }
    }

    fun floatArray(name: String? = null): ReadWriteProperty<Intent, FloatArray?> {
        return property(name, Intent::getFloatArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun int(name: String? = null, defaultValue: Int = 0): ReadWriteProperty<Intent, Int> {
        return property(name, defaultValue, Intent::getIntExtra) { k, v -> putExtra(k, v) }
    }

    fun intArray(name: String? = null): ReadWriteProperty<Intent, IntArray?> {
        return property(name, Intent::getIntArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun intArrayList(name: String? = null): ReadWriteProperty<Intent, java.util.ArrayList<Int>?> {
        return property(name, Intent::getIntegerArrayListExtra) { k, v -> putExtra(k, v) }
    }

    fun long(name: String? = null, defaultValue: Long = 0): ReadWriteProperty<Intent, Long> {
        return property(name, defaultValue, Intent::getLongExtra) { k, v -> putExtra(k, v) }
    }

    fun longArray(name: String? = null): ReadWriteProperty<Intent, LongArray?> {
        return property(name, Intent::getLongArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun <T : Parcelable> parcelable(name: String? = null): ReadWriteProperty<Intent, T?> {
        return property(name, Intent::getParcelableExtra) { k, v -> putExtra(k, v) }
    }

    fun parcelableArray(name: String? = null): ReadWriteProperty<Intent, Array<Parcelable>?> {
        return property(name, Intent::getParcelableArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun <T : Parcelable> parcelableArrayList(name: String? = null): ReadWriteProperty<Intent, ArrayList<T>?> {
        return property(name, Intent::getParcelableArrayListExtra) { k, v -> putExtra(k, v) }
    }

    fun serializable(name: String? = null): ReadWriteProperty<Intent, Serializable?> {
        return property(name, Intent::getSerializableExtra) { k, v -> putExtra(k, v) }
    }

    fun short(name: String? = null, defaultValue: Short = 0): ReadWriteProperty<Intent, Short> {
        return property(name, defaultValue, Intent::getShortExtra) { k, v -> putExtra(k, v) }
    }

    fun shortArray(name: String? = null): ReadWriteProperty<Intent, ShortArray?> {
        return property(name, Intent::getShortArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun string(name: String? = null): ReadWriteProperty<Intent, String?> {
        return property(name, Intent::getStringExtra) { k, v -> putExtra(k, v) }
    }

    fun stringArray(name: String? = null): ReadWriteProperty<Intent, Array<String>?> {
        return property(name, Intent::getStringArrayExtra) { k, v -> putExtra(k, v) }
    }

    fun stringArrayList(name: String? = null): ReadWriteProperty<Intent, java.util.ArrayList<String>?> {
        return property(name, Intent::getStringArrayListExtra) { k, v -> putExtra(k, v) }
    }

    companion object : IntentExtra

    interface Options<out T> : IntentExtra {
        fun intent(context: Context, configure: T.(Intent) -> Unit): Intent {
            val className = javaClass.name.run { substring(0, lastIndexOf('$')) }
            val intent = Intent().setComponent(ComponentName(context.packageName, className))
            return apply(intent, configure)
        }

        @Suppress("UNCHECKED_CAST")
        fun apply(intent: Intent, configure: T.(Intent) -> Unit): Intent {
            configure(this as T, intent)
            return intent
        }
    }
}