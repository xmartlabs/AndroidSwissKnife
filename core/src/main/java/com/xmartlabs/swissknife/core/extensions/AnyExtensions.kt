@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

/** Executes the `block` on `this` and, if an exception is thrown, returns null instead */
fun <T, R> T.ignoreException(block: T.() -> R?) = try {
  block(this)
} catch (e: Exception) {
  null
}

/** Executes the `block` on `this` and, if an exception is thrown, it's handled by `handler` */
fun <T, R> T.ifException(block  : (T) -> R, handler: (Exception) -> Unit) =
  try {
    block(this)
  } catch (e: Exception) {
    handler(e); null
  }

/** Executes `block` and, if an exception is thrown, it's handled by `handler` */
fun <T> handleException(handler: (Exception) -> Unit, block: () -> T?) =
  try {
    block()
  } catch (e: Exception) {
    handler(e); null
  }

/** Executes the `block` if and only if the `condition` is false */
fun unless(condition: Boolean, block: () -> Unit) = if (!condition) block() else Unit

/** Used in `when` sentences to check that all cases are covered*/
val <T> T.exhaustive: T
  get() = this


/** If `this` is null, then performs the `action` and returns that value */
inline fun <T> T?.orDo(action: () -> T) = this ?: action()

/** Returns `true` if this has a value and that values matches the `predicate` */
inline fun <T> T?.andMatches(predicate: (T) -> Boolean) = this != null && predicate(this)

