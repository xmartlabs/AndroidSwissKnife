@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager


/** Hides the keyboard, if visible.  */
fun Activity.hideKeyboard() {
  val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  currentFocus?.let { inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0) }
}

/**
 * Opens the intent from the activity context.
 *
 * @param activity the to use for opening the intent
 */
fun Intent.startFrom(activity: Activity) = activity.startActivity(this)

/**
 * Return whether Keyboard is currently visible on screen or not.
 *
 * @return true if keyboard is visible.
 */
fun Activity.isKeyboardVisible(): Boolean {
  val rect = Rect()
  window.decorView.getWindowVisibleDisplayFrame(rect)
  val height = getDisplaySize().y
  val diff = height - rect.bottom
  // If the difference is not 0 we assume that the keyboard is currently visible.
  return diff != 0
}
