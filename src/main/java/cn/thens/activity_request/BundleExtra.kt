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
    fun binder(name: String? = null): ReadWriteProperty<Bundle, IBinder?> {
        return property(name, Bundle::getBinder, Bundle::putBinder)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun boolean(name: String? = null, defaultValue: Boolean = false): ReadWriteProperty<Bundle, Boolean> {
        return property(name, defaultValue, Bundle::getBoolean, Bundle::putBoolean)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun booleanArray(name: String? = null): ReadWriteProperty<Bundle, BooleanArray?> {
        return property(name, Bundle::getBooleanArray, Bundle::putBooleanArray)
    }

    fun bundle(name: String? = null): ReadWriteProperty<Bundle, Bundle?> {
        return property(name, Bundle::getBundle, Bundle::putBundle)
    }

    fun byte(name: String? = null, defaultValue: Byte): ReadWriteProperty<Bundle, Byte> {
        return property(name, defaultValue, Bundle::getByte, Bundle::putByte)
    }

    fun byteArray(name: String? = null): ReadWriteProperty<Bundle, ByteArray?> {
        return property(name, Bundle::getByteArray, Bundle::putByteArray)
    }

    fun char(name: String? = null, defaultValue: Char = Char.MIN_VALUE): ReadWriteProperty<Bundle, Char> {
        return property(name, defaultValue, Bundle::getChar, Bundle::putChar)
    }

    fun charArray(name: String? = null): ReadWriteProperty<Bundle, CharArray?> {
        return property(name, Bundle::getCharArray, Bundle::putCharArray)
    }

    fun charSequence(name: String? = null, defaultValue: CharSequence = ""): ReadWriteProperty<Bundle, CharSequence> {
        return property(name, defaultValue, Bundle::getCharSequence, Bundle::putCharSequence)
    }

    fun charSequenceArray(name: String? = null): ReadWriteProperty<Bundle, Array<CharSequence>?> {
        return property(name, Bundle::getCharSequenceArray, Bundle::putCharSequenceArray)
    }

    fun charSequenceArrayList(name: String? = null): ReadWriteProperty<Bundle, java.util.ArrayList<CharSequence>?> {
        return property(name, Bundle::getCharSequenceArrayList, Bundle::putCharSequenceArrayList)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun double(name: String? = null, defaultValue: Double = 0.0): ReadWriteProperty<Bundle, Double> {
        return property(name, defaultValue, Bundle::getDouble, Bundle::putDouble)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun doubleArray(name: String? = null): ReadWriteProperty<Bundle, DoubleArray?> {
        return property(name, Bundle::getDoubleArray, Bundle::putDoubleArray)
    }

    fun float(name: String? = null, defaultValue: Float = 0F): ReadWriteProperty<Bundle, Float> {
        return property(name, defaultValue, Bundle::getFloat, Bundle::putFloat)
    }

    fun floatArray(name: String? = null): ReadWriteProperty<Bundle, FloatArray?> {
        return property(name, Bundle::getFloatArray, Bundle::putFloatArray)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun int(name: String? = null, defaultValue: Int = 0): ReadWriteProperty<Bundle, Int> {
        return property(name, defaultValue, Bundle::getInt, Bundle::putInt)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun intArray(name: String? = null): ReadWriteProperty<Bundle, IntArray?> {
        return property(name, Bundle::getIntArray, Bundle::putIntArray)
    }

    fun intArrayList(name: String? = null): ReadWriteProperty<Bundle, java.util.ArrayList<Int>?> {
        return property(name, Bundle::getIntegerArrayList, Bundle::putIntegerArrayList)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun long(name: String? = null, defaultValue: Long = 0): ReadWriteProperty<Bundle, Long> {
        return property(name, defaultValue, Bundle::getLong, Bundle::putLong)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun longArray(name: String? = null): ReadWriteProperty<Bundle, LongArray?> {
        return property(name, Bundle::getLongArray, Bundle::putLongArray)
    }

    fun <T : Parcelable> parcelable(name: String? = null): ReadWriteProperty<Bundle, T?> {
        return property(name, Bundle::getParcelable, Bundle::putParcelable)
    }

    fun parcelableArray(name: String? = null): ReadWriteProperty<Bundle, Array<Parcelable>?> {
        return property(name, Bundle::getParcelableArray, Bundle::putParcelableArray)
    }

    fun <T : Parcelable> parcelableArrayList(name: String? = null): ReadWriteProperty<Bundle, ArrayList<T>?> {
        return property(name, Bundle::getParcelableArrayList, Bundle::putParcelableArrayList)
    }

    fun serializable(name: String? = null): ReadWriteProperty<Bundle, Serializable?> {
        return property(name, Bundle::getSerializable, Bundle::putSerializable)
    }

    fun short(name: String? = null, defaultValue: Short = 0): ReadWriteProperty<Bundle, Short> {
        return property(name, defaultValue, Bundle::getShort, Bundle::putShort)
    }

    fun shortArray(name: String? = null): ReadWriteProperty<Bundle, ShortArray?> {
        return property(name, Bundle::getShortArray, Bundle::putShortArray)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun size(name: String? = null): ReadWriteProperty<Bundle, Size?> {
        return property(name, Bundle::getSize, Bundle::putSize)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun sizeF(name: String? = null): ReadWriteProperty<Bundle, SizeF?> {
        return property(name, Bundle::getSizeF, Bundle::putSizeF)
    }

    fun <T : Parcelable> sparseParcelableArray(name: String? = null): ReadWriteProperty<Bundle, SparseArray<T>?> {
        return property(name, Bundle::getSparseParcelableArray, Bundle::putSparseParcelableArray)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun string(name: String? = null, defaultValue: String = ""): ReadWriteProperty<Bundle, String> {
        return property(name, defaultValue, Bundle::getString, Bundle::putString)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun stringArray(name: String? = null): ReadWriteProperty<Bundle, Array<String>?> {
        return property(name, Bundle::getStringArray, Bundle::putStringArray)
    }

    fun stringArrayList(name: String? = null): ReadWriteProperty<Bundle, java.util.ArrayList<String>?> {
        return property(name, Bundle::getStringArrayList, Bundle::putStringArrayList)
    }

    companion object : BundleExtra

    interface Options<out T> : BundleExtra {
        fun bundle(configure: T.(Bundle) -> Unit): Bundle {
            return apply(Bundle(), configure)
        }

        @Suppress("UNCHECKED_CAST")
        fun apply(bundle: Bundle, configure: T.(Bundle) -> Unit): Bundle {
            configure(this as T, bundle)
            return bundle
        }
    }
}