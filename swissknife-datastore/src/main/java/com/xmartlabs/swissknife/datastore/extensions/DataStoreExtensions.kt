package com.xmartlabs.swissknife.datastore.extensions

import androidx.datastore.preferences.MutablePreferences
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import androidx.datastore.preferences.remove
import com.xmartlabs.swissknife.datastore.DataStoreEntitySerializer

/**
 * Created by mirland on 01/10/20.
 */
inline fun <reified T> MutablePreferences.set(key: String, serializer: DataStoreEntitySerializer, value: T) =
    set(key, serializer, T::class.java, value)

inline fun <reified T> Preferences.get(key: String, serializer: DataStoreEntitySerializer): T? =
    get(key, serializer, T::class.java)

inline fun <reified T> MutablePreferences.remove(key: String, serializer: DataStoreEntitySerializer): T? =
    remove(key, serializer, T::class.java)

operator fun <T> MutablePreferences.set(key: String, serializer: DataStoreEntitySerializer, aClass: Class<T>, value: T) {
  when (aClass) {
    Int::class.java -> set(preferencesKey(key), value as Int)
    String::class.java -> set(preferencesKey(key), value as String)
    Boolean::class.java -> set(preferencesKey(key), value as Boolean)
    Float::class.java -> set(preferencesKey(key), value as Float)
    Long::class.java -> set(preferencesKey(key), value as Long)
    else -> {
      val serializedValue = serializer.toString(value, aClass)
      set(preferencesKey(key), serializedValue)
    }
  }
}

operator fun <T> Preferences.get(key: String, serializer: DataStoreEntitySerializer, aClass: Class<T>): T? =
    when (aClass) {
      Int::class -> get(preferencesKey<Int>(key)) as T?
      String::class.java -> get(preferencesKey<String>(key)) as T?
      Boolean::class.java -> get(preferencesKey<Boolean>(key)) as T?
      Float::class.java -> get(preferencesKey<Float>(key)) as T?
      Long::class.java -> get(preferencesKey<Long>(key)) as T?
      else -> {
        val serializedValue = get(preferencesKey<String>(key))
        serializer.fromString(serializedValue, aClass)
      }
    }

fun <T> MutablePreferences.remove(key: String, serializer: DataStoreEntitySerializer, aClass: Class<T>): T? =
    when (aClass) {
      Int::class -> remove(preferencesKey<Int>(key)) as? T
      String::class.java -> remove(preferencesKey<String>(key)) as T?
      Boolean::class.java -> remove(preferencesKey<Boolean>(key)) as T?
      Float::class.java -> remove(preferencesKey<Float>(key)) as T?
      Long::class.java -> remove(preferencesKey<Long>(key)) as T?
      else -> {
        val serializedValue = remove(preferencesKey<String>(key))
        serializer.fromString(serializedValue, aClass)
      }
    }
