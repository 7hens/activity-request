package cn.thens.activity_request

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal inline fun <R : Any, T> property(
    name: String?,
    crossinline get: R.(String) -> T,
    crossinline set: R.(String, T) -> Unit
) = run {
    object : ReadWriteProperty<R, T> {
        override fun getValue(thisRef: R, property: KProperty<*>): T {
            return get(thisRef, name ?: property.name)
        }

        override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
            set(thisRef, name ?: property.name, value)
        }
    }
}

internal inline fun <R : Any, T> property(
    name: String?,
    defaultValue: T,
    crossinline get: R.(String, T) -> T,
    crossinline set: R.(String, T) -> Unit
) = run {
    object : ReadWriteProperty<R, T> {
        override fun getValue(thisRef: R, property: KProperty<*>): T {
            return get(thisRef, name ?: property.name, defaultValue)
        }

        override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
            set(thisRef, name ?: property.name, value)
        }
    }
}
