package com.xmartlabs.swissknife.navigationdebug

import android.content.Context
import android.util.Log
import com.xmartlabs.swissknife.core.Logger

/** The default implementation of [Logger] which log the message in the Android logcat. */
internal class DebugAndroidLogger(private val tag: String) : Logger {
  override fun log(message: String) {
    Log.d(tag, message)
  }
}
