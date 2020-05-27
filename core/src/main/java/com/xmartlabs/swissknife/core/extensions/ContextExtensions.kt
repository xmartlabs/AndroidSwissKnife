@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresPermission

/**
 * Uses the [ConnectivityManager] class to determine whether the device is connected to the internet or not.
 * *
 * @return true if the device is connected to the internet
 */
@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.hasNetworkConnection(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

  return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
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
