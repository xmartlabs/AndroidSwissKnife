package com.xmartlabs.swissknife.sample

import com.google.gson.Gson
import com.xmartlabs.swissknife.datastore.DataStoreSerializer

/**
 * Created by mirland on 02/10/20.
 */
class GsonDataStoreSerializer(private val gson: Gson = Gson()) : DataStoreSerializer {
  override fun <T> toString(t: T, aClass: Class<T>): String = gson.toJson(t)

  override fun <T> fromString(value: String?, aClass: Class<T>): T? = gson.fromJson(value, aClass)
}
