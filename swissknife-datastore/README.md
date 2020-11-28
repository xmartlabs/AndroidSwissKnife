# DataStoreSource
It contains a Custom [DataStoreSource] that provides the ability to store custom entities types without the requirement of using Protocol Buffers.

[Jetpack DataStore] provides two implementations, one that stores key-value primitives types and another to stores typed objects with protocol buffers.
This library provides a DataStore implementation that enables storing typed objects, just providing a [DataStoreSerializer].

The [DataStoreSerializer] is an interface with two methods, one to serialize an entity from a string and the other to serialize the entity into a string.

```kotlin
interface DataStoreSerializer {
  @WorkerThread
  fun <T> toString(t: T, aClass: Class<T>): String

  @WorkerThread
  fun <T> fromString(value: String?, aClass: Class<T>): T?
}
```
You can choose what serializer do you prefer, for example, if you are using [Gson] you could define a `GsonSerializer` like this:

```kotlin
class GsonDataStoreEntitySerializer(private val gson: Gson = Gson()) : DataStoreSerializer {
  override fun <T> toString(t: T, aClass: Class<T>): String = gson.toJson(t)

  override fun <T> fromString(value: String?, aClass: Class<T>): T? = gson.fromJson(value, aClass)
```


After you have created the serializer, you can use the [DataStoreSource]. 
To create a [DataStoreSource], you need to provide a [Jetpack DataStore], a [DataStoreEntitySerializer] and a [CoroutineDispatcher
] to specify where you want to run the background tasks.

```kotlin
  private val dataStoreSource = DataStoreSource(
      dataStore = context.createDataStore(name = "my-data-store"),
      serializer = GsonDataStoreEntitySerializer()
  )
```

The [DataStoreSource] provides three methods to manage the data:

```kotlin
  suspend inline fun <reified T> getEntity(
      key: String,
      serializer: DataStoreEntitySerializer<T>? = null
  )

  suspend inline fun <reified T> putEntity(
      key: String,
      t: T,
      serializer: DataStoreEntitySerializer<T>? = null
  ): T

  suspend inline fun <reified T> removeEntity(
      key: String,
      serializer: DataStoreEntitySerializer<T>? = null
  ): T

```

Each method accept a custom [DataStoreEntitySerializer] to a specify custom serialized for specific type.

After that you can create a storage to use the [DataStoreSource].
In the sample app, it's defined a [SessionRepository] which manages the app's session information.

```kotlin
class SessionRepository(context: Context) {
  companion object {
    private const val DATA_STORE_NAME = "data_store_name"
    private const val SESSION_REPOSITORY_KEY = "session"
  }

  private val dataStore = DataStoreSource(
      context.createDataStore(name = DATA_STORE_NAME),
       GsonDataStoreEntitySerializer()
  )

  val session: Flow<Session?>
    get() = dataStore.getEntity(SESSION_REPOSITORY_KEY)

  suspend fun saveSession(session: Session?) =
      dataStore.putEntity(SESSION_REPOSITORY_KEY, session)
}
``` 

## Dependencies:
- [Preferences DataStore Dependency (`androidx.datastore:datastore-preferences`)](https://developer.android.com/jetpack/androidx/releases/navigation).
- [Core Module](../swissknife-core)

[DataStoreSource]: src/main/java/com/xmartlabs/swissknife/datastore/DataStoreSource.kt
[DataStoreSerializer]: src/main/java/com/xmartlabs/swissknife/datastore/DataStoreSerializer.kt
[DataStoreEntitySerializer]: src/main/java/com/xmartlabs/swissknife/datastore/DataStoreSerializer.kt
[Jetpack DataStore]: https://developer.android.com/topic/libraries/architecture/datastore
[Gson]: https://github.com/google/gson
[SessionRepository]: ../app/src/main/java/com/xmartlabs/swissknife/sample/data/SessionRepository.kt

