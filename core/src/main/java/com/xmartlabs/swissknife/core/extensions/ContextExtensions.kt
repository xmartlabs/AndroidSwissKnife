@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresPermission

/** Provides the [LayoutInflater] from [Context]. */
val Context.layoutInflater
  get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

/** Provides the [WindowManager] from [Context].*/
val Context.windowManager
  get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

/** Provides the [InputMethodManager] from [Context].*/
val Context.inputMethodManager
  get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

/**
 * Uses the [ConnectivityManager] class to determine whether the device is connected to the internet or not.
 * *
 * @return true if the device is connected to the internet
 */
@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.hasNetworkConnection(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

  return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    val actNw = connectivityManager.activeNetwork
        ?.let { connectivityManager.getNetworkCapabilities(it) } ?: return false
    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)
  } else {
    @Suppress("DEPRECATION")
    val nwInfo = connectivityManager.activeNetworkInfo ?: return false
    @Suppress("DEPRECATION")
    nwInfo.isConnected
  }
}

/** Shows the keyboard */
fun Context.showKeyboard(focusView: View) {
  focusView.requestFocus()
  inputMethodManager.showSoftInput(focusView, 0)
}

/**
 * Copies the `text` to the clipboard by means of the [ClipboardManager].
 * *
 * @param text the text to be copied
 */
@SuppressLint("ObsoleteSdkInt")
fun Context.copyTextToClipboard(text: String) {
  if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
    @Suppress("DEPRECATION")
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
    @Suppress("DEPRECATION")
    clipboard.text = text
  } else {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = android.content.ClipData.newPlainText("Copied Text", text)
    clipboard.setPrimaryClip(clip)
  }
}

/** Returns the navigation bar height */
fun Context.getNavigationBarHeight(): Int {
  val resId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
  return if (resId > 0) this.resources.getDimensionPixelSize(resId) else 0
}

/** Returns the status bar height */
fun Context.getStatusBarHeight(): Int {
  val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
  return if (resId > 0) this.resources.getDimensionPixelSize(resId) else 0
}

/** Method used to easily retrieve display size from [Context]. */
fun Context.getDisplaySize() = Point().apply {
  windowManager.defaultDisplay.getSize(this)
}

/** Extension method to display Width for Context. */
fun Context.displayWidth(): Int = getDisplaySize().x
