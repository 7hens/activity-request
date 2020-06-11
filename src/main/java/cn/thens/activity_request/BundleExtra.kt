package cn.thens.activity_request

import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import androidx.annotation.RequiresApi
import java.io.Serializable
import kotlin.properties.ReadWriteProperty

/**
 * @author 7hens
 */
@Suppress("unused")
interface BundleExtra {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    fun binder(): ReadWriteProperty<Bundle, IBinder?> {
        return property(Bundle::getBinder, Bundle::putBinder)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun boolean(defaultValue: Boolean = false): ReadWriteProperty<Bundle, Boolean> {
        return property(defaultValue, Bundle::getBoolean, Bundle::putBoolean)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun booleanArray(): ReadWriteProperty<Bundle, BooleanArray?> {
        return property(Bundle::getBooleanArray, Bundle::putBooleanArray)
    }

    fun bundle(): ReadWriteProperty<Bundle, Bundle?> {
        return property(Bundle::getBundle, Bundle::putBundle)
    }

    fun byte(defaultValue: Byte): ReadWriteProperty<Bundle, Byte> {
        return property(defaultValue, Bundle::getByte, Bundle::putByte)
    }

    fun byteArray(): ReadWriteProperty<Bundle, ByteArray?> {
        return property(Bundle::getByteArray, Bundle::putByteArray)
    }

    fun char(defaultValue: Char = Char.MIN_VALUE): ReadWriteProperty<Bundle, Char> {
        return property(defaultValue, Bundle::getChar, Bundle::putChar)
    }

    fun charArray(): ReadWriteProperty<Bundle, CharArray?> {
        return property(Bundle::getCharArray, Bundle::putCharArray)
    }

    fun charSequence(defaultValue: CharSequence = ""): ReadWriteProperty<Bundle, CharSequence> {
        return property(defaultValue, Bundle::getCharSequence, Bundle::putCharSequence)
    }

    fun charSequenceArray(): ReadWriteProperty<Bundle, Array<CharSequence>?> {
        return property(Bundle::getCharSequenceArray, Bundle::putCharSequenceArray)
    }

    fun charSequenceArrayList(): ReadWriteProperty<Bundle, java.util.ArrayList<CharSequence>?> {
        return property(Bundle::getCharSequenceArrayList, Bundle::putCharSequenceArrayList)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun double(defaultValue: Double = 0.0): ReadWriteProperty<Bundle, Double> {
        return property(defaultValue, Bundle::getDouble, Bundle::putDouble)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun doubleArray(): ReadWriteProperty<Bundle, DoubleArray?> {
        return property(Bundle::getDoubleArray, Bundle::putDoubleArray)
    }

    fun float(defaultValue: Float = 0F): ReadWriteProperty<Bundle, Float> {
        return property(defaultValue, Bundle::getFloat, Bundle::putFloat)
    }

    fun floatArray(): ReadWriteProperty<Bundle, FloatArray?> {
        return property(Bundle::getFloatArray, Bundle::putFloatArray)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun int(defaultValue: Int = 0): ReadWriteProperty<Bundle, Int> {
        return property(defaultValue, Bundle::getInt, Bundle::putInt)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun intArray(): ReadWriteProperty<Bundle, IntArray?> {
        return property(Bundle::getIntArray, Bundle::putIntArray)
    }

    fun intArrayList(): ReadWriteProperty<Bundle, java.util.ArrayList<Int>?> {
        return property(Bundle::getIntegerArrayList, Bundle::putIntegerArrayList)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun long(defaultValue: Long = 0): ReadWriteProperty<Bundle, Long> {
        return property(defaultValue, Bundle::getLong, Bundle::putLong)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun longArray(): ReadWriteProperty<Bundle, LongArray?> {
        return property(Bundle::getLongArray, Bundle::putLongArray)
    }

    fun <T : Parcelable> parcelable(): ReadWriteProperty<Bundle, T> {
        return property(Bundle::getParcelable, Bundle::putParcelable)
    }

    fun parcelableArray(): ReadWriteProperty<Bundle, Array<Parcelable>?> {
        return property(Bundle::getParcelableArray, Bundle::putParcelableArray)
    }

    fun <T : Parcelable> parcelableArrayList(): ReadWriteProperty<Bundle, ArrayList<T>> {
        return property(Bundle::getParcelableArrayList, Bundle::putParcelableArrayList)
    }

    fun serializable(): ReadWriteProperty<Bundle, Serializable?> {
        return property(Bundle::getSerializable, Bundle::putSerializable)
    }

    fun short(defaultValue: Short = 0): ReadWriteProperty<Bundle, Short> {
        return property(defaultValue, Bundle::getShort, Bundle::putShort)
    }

    fun shortArray(): ReadWriteProperty<Bundle, ShortArray?> {
        return property(Bundle::getShortArray, Bundle::putShortArray)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun size(): ReadWriteProperty<Bundle, Size?> {
        return property(Bundle::getSize, Bundle::putSize)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun sizeF(): ReadWriteProperty<Bundle, SizeF?> {
        return property(Bundle::getSizeF, Bundle::putSizeF)
    }

    fun <T : Parcelable> sparseParcelableArray(): ReadWriteProperty<Bundle, SparseArray<T>> {
        return property(Bundle::getSparseParcelableArray, Bundle::putSparseParcelableArray)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun string(defaultValue: String = ""): ReadWriteProperty<Bundle, String> {
        return property(defaultValue, Bundle::getString, Bundle::putString)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun stringArray(): ReadWriteProperty<Bundle, Array<String>?> {
        return property(Bundle::getStringArray, Bundle::putStringArray)
    }

    fun stringArrayList(): ReadWriteProperty<Bundle, java.util.ArrayList<String>?> {
        return property(Bundle::getStringArrayList, Bundle::putStringArrayList)
    }

    companion object : BundleExtra

    interface CompanionOptions<out Options> : BundleExtra {
        @Suppress("UNCHECKED_CAST")
        private val options: Options
            get() = this as Options

        fun bundle(configure: Options.(Bundle) -> Unit): Bundle {
            return apply(Bundle(), configure)
        }

        fun apply(bundle: Bundle, configure: Options.(Bundle) -> Unit): Bundle {
            configure(options, bundle)
            return bundle
        }
    }
}