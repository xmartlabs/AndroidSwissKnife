package com.xmartlabs.swissknife.datastore

import androidx.annotation.WorkerThread

/**
 * Created by mirland on 01/10/20.
 */
interface DataStoreEntitySerializer {
  @WorkerThread
  fun <T> toString(t: T, aClass: Class<T>): String

  @WorkerThread
  fun <T> fromString(value: String?, aClass: Class<T>): T?
}

inline fun <reified T> DataStoreEntitySerializer.toString(t: T) = toString(t, T::class.java)

inline fun <reified T> DataStoreEntitySerializer.fromString(value: String?) = fromString(value, T::class.java)
