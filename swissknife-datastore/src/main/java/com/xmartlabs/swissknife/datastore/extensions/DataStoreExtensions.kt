package com.xmartlabs.swissknife.datastore.extensions

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.core.remove
import com.xmartlabs.swissknife.datastore.DataStoreEntitySerializer
import com.xmartlabs.swissknife.datastore.DataStoreSerializer

/**
 * Created by mirland on 01/10/20.
 */
internal operator fun <T> MutablePreferences.set(
    key: String,
    defaultSerializer: DataStoreSerializer,
    serializer: DataStoreEntitySerializer<T>?,
    aClass: Class<T>,
    value: T
) {
  when (aClass) {
    Int::class.java -> set(preferencesKey(key), value as Int)
    String::class.java -> set(preferencesKey(key), value as String)
    Boolean::class.java -> set(preferencesKey(key), value as Boolean)
    Float::class.java -> set(preferencesKey(key), value as Float)
    Long::class.java -> set(preferencesKey(key), value as Long)
    else -> {
      val serializedValue = serializer?.toString(value)
          ?: defaultSerializer.toString(value, aClass)
      set(preferencesKey(key), serializedValue)
    }
  }
}

internal operator fun <T> Preferences.get(
    key: String,
    defaultSerializer: DataStoreSerializer,
    serializer: DataStoreEntitySerializer<T>?,
    aClass: Class<T>
): T? = when (aClass) {
  Int::class -> get(preferencesKey<Int>(key)) as T?
  String::class.java -> get(preferencesKey<String>(key)) as T?
  Boolean::class.java -> get(preferencesKey<Boolean>(key)) as T?
  Float::class.java -> get(preferencesKey<Float>(key)) as T?
  Long::class.java -> get(preferencesKey<Long>(key)) as T?
  else -> {
    val serializedValue = get(preferencesKey<String>(key))
    if (serializer == null) {
      defaultSerializer.fromString(serializedValue, aClass)
    } else {
      serializer.fromString(serializedValue)
    }
  }
}

internal fun <T> MutablePreferences.remove(
    key: String,
    defaultSerializer: DataStoreSerializer,
    serializer: DataStoreEntitySerializer<T>?,
    aClass: Class<T>
): T? = when (aClass) {
  Int::class -> remove(preferencesKey<Int>(key)) as? T
  String::class.java -> remove(preferencesKey<String>(key)) as T?
  Boolean::class.java -> remove(preferencesKey<Boolean>(key)) as T?
  Float::class.java -> remove(preferencesKey<Float>(key)) as T?
  Long::class.java -> remove(preferencesKey<Long>(key)) as T?
  else -> {
    val serializedValue = remove(preferencesKey<String>(key))
    if (serializer == null) {
      defaultSerializer.fromString(serializedValue, aClass)
    } else {
      serializer.fromString(serializedValue)
    }
  }
}
