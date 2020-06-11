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

    fun boolean(defaultValue: Boolean = false): ReadWriteProperty<Intent, Boolean> {
        return property(defaultValue, Intent::getBooleanExtra) { n, v -> putExtra(n, v) }
    }

    fun booleanArray(): ReadWriteProperty<Intent, BooleanArray> {
        return property(Intent::getBooleanArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun bundle(): ReadWriteProperty<Intent, Bundle> {
        return property(Intent::getBundleExtra) { n, v -> putExtra(n, v) }
    }

    fun byte(defaultValue: Byte = 0): ReadWriteProperty<Intent, Byte> {
        return property(defaultValue, Intent::getByteExtra) { n, v -> putExtra(n, v) }
    }

    fun byteArray(): ReadWriteProperty<Intent, ByteArray> {
        return property(Intent::getByteArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun char(defaultValue: Char = Char.MIN_VALUE): ReadWriteProperty<Intent, Char> {
        return property(defaultValue, Intent::getCharExtra) { n, v -> putExtra(n, v) }
    }

    fun charArray(): ReadWriteProperty<Intent, CharArray> {
        return property(Intent::getCharArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun charSequence(): ReadWriteProperty<Intent, CharSequence> {
        return property(Intent::getCharSequenceExtra) { n, v -> putExtra(n, v) }
    }

    fun charSequenceArray(): ReadWriteProperty<Intent, Array<CharSequence>> {
        return property(Intent::getCharSequenceArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun charSequenceArrayList(): ReadWriteProperty<Intent, java.util.ArrayList<CharSequence>> {
        return property(Intent::getCharSequenceArrayListExtra) { n, v -> putExtra(n, v) }
    }


    fun double(defaultValue: Double = 0.0): ReadWriteProperty<Intent, Double> {
        return property(defaultValue, Intent::getDoubleExtra) { n, v -> putExtra(n, v) }
    }

    fun doubleArray(): ReadWriteProperty<Intent, DoubleArray> {
        return property(Intent::getDoubleArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun float(defaultValue: Float = 0F): ReadWriteProperty<Intent, Float> {
        return property(defaultValue, Intent::getFloatExtra) { n, v -> putExtra(n, v) }
    }

    fun floatArray(): ReadWriteProperty<Intent, FloatArray> {
        return property(Intent::getFloatArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun int(defaultValue: Int = 0): ReadWriteProperty<Intent, Int> {
        return property(defaultValue, Intent::getIntExtra) { n, v -> putExtra(n, v) }
    }

    fun intArray(): ReadWriteProperty<Intent, IntArray> {
        return property(Intent::getIntArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun intArrayList(): ReadWriteProperty<Intent, java.util.ArrayList<Int>> {
        return property(Intent::getIntegerArrayListExtra) { n, v -> putExtra(n, v) }
    }

    fun long(defaultValue: Long = 0): ReadWriteProperty<Intent, Long> {
        return property(defaultValue, Intent::getLongExtra) { n, v -> putExtra(n, v) }
    }

    fun longArray(): ReadWriteProperty<Intent, LongArray> {
        return property(Intent::getLongArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun <T : Parcelable> parcelable(): ReadWriteProperty<Intent, T> {
        return property(Intent::getParcelableExtra) { n, v -> putExtra(n, v) }
    }

    fun parcelableArray(): ReadWriteProperty<Intent, Array<Parcelable>> {
        return property(Intent::getParcelableArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun <T : Parcelable> parcelableArrayList(): ReadWriteProperty<Intent, ArrayList<T>> {
        return property(Intent::getParcelableArrayListExtra) { n, v -> putExtra(n, v) }
    }

    fun serializable(): ReadWriteProperty<Intent, Serializable> {
        return property(Intent::getSerializableExtra) { n, v -> putExtra(n, v) }
    }

    fun short(defaultValue: Short = 0): ReadWriteProperty<Intent, Short> {
        return property(defaultValue, Intent::getShortExtra) { n, v -> putExtra(n, v) }
    }

    fun shortArray(): ReadWriteProperty<Intent, ShortArray> {
        return property(Intent::getShortArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun string(): ReadWriteProperty<Intent, String> {
        return property(Intent::getStringExtra) { n, v -> putExtra(n, v) }
    }

    fun stringArray(): ReadWriteProperty<Intent, Array<String>> {
        return property(Intent::getStringArrayExtra) { n, v -> putExtra(n, v) }
    }

    fun stringArrayList(): ReadWriteProperty<Intent, java.util.ArrayList<String>> {
        return property(Intent::getStringArrayListExtra) { n, v -> putExtra(n, v) }
    }

    companion object : IntentExtra

    interface CompanionOptions<out Options> : IntentExtra {
        private val className: String
            get() = javaClass.name.replace("\$Companion", "")

        @Suppress("UNCHECKED_CAST")
        private val options: Options
            get() = this as Options

        fun intent(context: Context, configure: Options.(Intent) -> Unit): Intent {
            val intent = Intent().setComponent(ComponentName(context.packageName, className))
            return apply(intent, configure)
        }

        fun apply(intent: Intent, configure: Options.(Intent) -> Unit): Intent {
            configure(options, intent)
            return intent
        }
    }
}