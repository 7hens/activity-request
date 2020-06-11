package cn.thens.activity_request

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal inline fun <R : Any, T> property(
    crossinline get: R.(String) -> T,
    crossinline set: R.(String, T) -> Unit
) = run {
    object : ReadWriteProperty<R, T> {
        override fun getValue(thisRef: R, property: KProperty<*>): T {
            return get(thisRef, property.name)
        }

        override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
            set(thisRef, property.name, value)
        }
    }
}

internal inline fun <R : Any, T> property(
    defaultValue: T,
    crossinline get: R.(String, T) -> T,
    crossinline set: R.(String, T) -> Unit
) = run {
    object : ReadWriteProperty<R, T> {
        override fun getValue(thisRef: R, property: KProperty<*>): T {
            return get(thisRef, property.name, defaultValue)
        }

        override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
            set(thisRef, property.name, value)
        }
    }
}
