package com.xmartlabs.swissknife.datastore.extensions

import androidx.datastore.preferences.MutablePreferences
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import androidx.datastore.preferences.remove
import com.xmartlabs.swissknife.datastore.DataStoreSerializer

/**
 * Created by mirland on 01/10/20.
 */
inline operator fun <reified T> MutablePreferences.set(key: String, serializer: DataStoreSerializer, value: T) {
  when (T::class.java) {
    Int::class.java -> set(preferencesKey(key), value as Int)
    String::class.java -> set(preferencesKey(key), value as String)
    Boolean::class.java -> set(preferencesKey(key), value as Boolean)
    Float::class.java -> set(preferencesKey(key), value as Float)
    Long::class.java -> set(preferencesKey(key), value as Long)
    else -> {
      val jsonValue = serializer.toString(value, T::class.java)
      set(preferencesKey(key), jsonValue)
    }
  }
}

inline operator fun <reified T> Preferences.get(key: String, serializer: DataStoreSerializer): T? =
    when (T::class.java) {
      Int::class -> get(preferencesKey<Int>(key)) as T?
      String::class.java -> get(preferencesKey<String>(key)) as T?
      Boolean::class.java -> get(preferencesKey<Boolean>(key)) as T?
      Float::class.java -> get(preferencesKey<Float>(key)) as T?
      Long::class.java -> get(preferencesKey<Long>(key)) as T?
      else -> {
        val jsonValue = get(preferencesKey<String>(key))
        serializer.fromString(jsonValue, T::class.java)
      }
    }

inline fun <reified T> MutablePreferences.remove(key: String, serializer: DataStoreSerializer)=
    when (T::class.java) {
      Int::class -> remove(preferencesKey<Int>(key)) as? T
      String::class.java -> remove(preferencesKey<String>(key)) as T?
      Boolean::class.java -> remove(preferencesKey<Boolean>(key)) as T?
      Float::class.java -> remove(preferencesKey<Float>(key)) as T?
      Long::class.java -> remove(preferencesKey<Long>(key)) as T?
      else -> {
        val jsonValue = remove(preferencesKey<String>(key))
        serializer.fromString(jsonValue, T::class.java)
      }
    }
