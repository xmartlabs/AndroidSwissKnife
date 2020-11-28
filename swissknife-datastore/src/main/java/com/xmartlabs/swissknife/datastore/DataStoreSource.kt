package com.xmartlabs.swissknife.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.xmartlabs.swissknife.datastore.extensions.get
import com.xmartlabs.swissknife.datastore.extensions.remove
import com.xmartlabs.swissknife.datastore.extensions.set
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Created by mirland on 02/10/20.
 */
class DataStoreSource(
    private val defaultSerializer: DataStoreSerializer,
    private val dataStore: DataStore<Preferences>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
  inline fun <reified T> getEntity(
      key: String,
      serializer: DataStoreEntitySerializer<T>? = null
  ): Flow<T?> = getEntity(key, T::class.java, serializer)

  suspend inline fun <reified T> putEntity(
      key: String,
      t: T,
      serializer: DataStoreEntitySerializer<T>? = null
  ) = putEntity(key, t, T::class.java, serializer)

  suspend inline fun <reified T> removeEntity(key: String) =
      removeEntity(key, T::class.java)

  fun <T> getEntity(
      key: String,
      aClass: Class<T>,
      serializer: DataStoreEntitySerializer<T>? = null
  ): Flow<T?> = dataStore.data
      .map { preferences ->
        withContext(dispatcher) {
          preferences[key, defaultSerializer, serializer, aClass]
        }
      }

  suspend fun <T> putEntity(
      key: String,
      t: T,
      aClass: Class<T>,
      serializer: DataStoreEntitySerializer<T>? = null
  ) {
    withContext(dispatcher) {
      dataStore.edit { settings ->
        settings[key, defaultSerializer, serializer, aClass] = t
      }
    }
  }

  suspend fun <T> removeEntity(
      key: String,
      aClass: Class<T>,
      serializer: DataStoreEntitySerializer<T>? = null,
  ) {
    withContext(dispatcher) {
      dataStore.edit { settings ->
        settings.remove(key, defaultSerializer, serializer, aClass)
      }
    }
  }
}
