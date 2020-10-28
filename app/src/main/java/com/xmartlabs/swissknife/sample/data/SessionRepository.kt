package com.xmartlabs.swissknife.sample.data

import android.content.Context
import androidx.datastore.preferences.createDataStore
import com.xmartlabs.swissknife.datastore.DataStoreSource
import com.xmartlabs.swissknife.sample.GsonDataStoreEntitySerializer
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

  private val dataStoreSource = DataStoreSource(
      dataStore = context.createDataStore(name = DATA_STORE_NAME),
      serializer = GsonDataStoreEntitySerializer()
  )

  val session: Flow<Session?>
    get() = dataStoreSource.getEntity(SESSION_REPOSITORY_KEY)

  suspend fun saveSession(session: Session?) =
      dataStoreSource.putEntity(SESSION_REPOSITORY_KEY, session)
}
