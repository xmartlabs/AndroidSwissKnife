package com.xmartlabs.swissknife.reflection

import androidx.annotation.WorkerThread
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

/** Executes a private function using reflection */
@WorkerThread
@Suppress("SpreadOperator")
inline fun <reified T> T.callPrivateFuncByReflection(name: String, vararg args: Any?): Any? =
  T::class
    .declaredMemberFunctions
    .firstOrNull { it.name == name }
    ?.apply { isAccessible = true }
    ?.call(this, *args)

/** Returns the private property value using reflection. */
@WorkerThread
inline fun <reified T : Any, R> T.getPrivatePropertyByReflection(name: String): R? =
  T::class
    .memberProperties
    .firstOrNull { it.name == name }
    ?.apply { isAccessible = true }
    ?.get(this) as? R
