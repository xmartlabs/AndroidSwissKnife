package com.xmartlabs.swissknife.sample.data

import android.content.Context
import androidx.datastore.preferences.createDataStore
import com.xmartlabs.swissknife.datastore.DataStoreSource
import com.xmartlabs.swissknife.sample.GsonDataStoreSerializer
import com.xmartlabs.swissknife.sample.data.model.Session
import kotlinx.coroutines.flow.Flow

/**
 * Created by mirland on 02/10/20.
 */
class SessionRepository(context: Context) {
  companion object {
    private const val DATA_STORE_NAME = "data_store_name"
    private const val SESSION_REPOSITORY_KEY = "session"
  }

  private val dataStore = DataStoreSource(context.createDataStore(name = DATA_STORE_NAME), GsonDataStoreSerializer())

  val session: Flow<Session?>
    get() = dataStore.getEntity()

  suspend fun saveSession(session: Session?) =
      dataStore.putEntity(SESSION_REPOSITORY_KEY, session)
}
