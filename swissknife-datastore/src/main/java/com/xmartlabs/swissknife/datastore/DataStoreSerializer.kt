package com.xmartlabs.swissknife.datastore

import androidx.annotation.WorkerThread

/**
 * Created by mirland on 01/10/20.
 */
interface DataStoreEntitySerializer<T> {
  @WorkerThread
  fun toString(t: T): String

  @WorkerThread
  fun fromString(value: String?): T?
}

interface DataStoreSerializer {
  @WorkerThread
  fun <T> toString(t: T, aClass: Class<T>): String

  @WorkerThread
  fun <T> fromString(value: String?, aClass: Class<T>): T?
}

