package com.xmartlabs.swissknife.datastore.extensions

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
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
    Int::class.java -> set(intPreferencesKey(key), value as Int)
    String::class.java -> set(stringPreferencesKey(key), value as String)
    Boolean::class.java -> set(booleanPreferencesKey(key), value as Boolean)
    Float::class.java -> set(floatPreferencesKey(key), value as Float)
    Long::class.java -> set(longPreferencesKey(key), value as Long)
    else -> {
      val serializedValue = serializer.toString(value, aClass)
      set(stringPreferencesKey(key), serializedValue)
    }
  }
}

operator fun <T> Preferences.get(key: String, serializer: DataStoreEntitySerializer, aClass: Class<T>): T? =
    when (aClass) {
      Int::class -> get(intPreferencesKey(key)) as T?
      String::class.java -> get(stringPreferencesKey(key)) as T?
      Boolean::class.java -> get(booleanPreferencesKey(key)) as T?
      Float::class.java -> get(floatPreferencesKey(key)) as T?
      Long::class.java -> get(longPreferencesKey(key)) as T?
      else -> {
        val serializedValue = get(stringPreferencesKey(key))
        serializer.fromString(serializedValue, aClass)
      }
    }

fun <T> MutablePreferences.remove(key: String, serializer: DataStoreEntitySerializer, aClass: Class<T>): T? =
    when (aClass) {
      Int::class -> remove(intPreferencesKey(key)) as? T
      String::class.java -> remove(stringPreferencesKey(key)) as T?
      Boolean::class.java -> remove(booleanPreferencesKey(key)) as T?
      Float::class.java -> remove(floatPreferencesKey(key)) as T?
      Long::class.java -> remove(longPreferencesKey(key)) as T?
      else -> {
        val serializedValue = remove(stringPreferencesKey(key))
        serializer.fromString(serializedValue, aClass)
      }
    }
