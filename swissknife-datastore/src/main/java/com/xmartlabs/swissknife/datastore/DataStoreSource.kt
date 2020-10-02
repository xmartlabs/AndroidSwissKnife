package com.xmartlabs.swissknife.datastore

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
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
    val dataStore: DataStore<Preferences>,
    val serializer: DataStoreSerializer,
    val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
  inline fun <reified T> getEntity(key: String): Flow<T?> = dataStore.data
      .map { preferences ->
        withContext(backgroundDispatcher) {
          preferences.get<T>(key, serializer)
        }
      }

  suspend inline fun <reified T> putEntity(key: String, t: T) {
    withContext(backgroundDispatcher) {
      dataStore.edit { settings ->
        settings[key, serializer] = t
      }
    }
  }

  suspend inline fun <reified T> removeEntity(key: String) {
    withContext(backgroundDispatcher) {
      dataStore.edit { settings ->
        settings.remove<T>(key, serializer)
      }
    }
  }
}
