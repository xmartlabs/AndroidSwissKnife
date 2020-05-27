@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

import android.graphics.Point
import android.graphics.Rect
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

/** Sets the view's visibility to [GONE](View.GONE) */
fun View.gone() = run { if (!isGone) visibility = View.GONE }

/** Sets the view's visibility to [VISIBLE](View.VISIBLE) */
fun View.visible() = run { if (!isVisible) visibility = View.VISIBLE }

/** Sets the view's visibility to [INVISIBLE](View.INVISIBLE) */
fun View.invisible() = run { if (!isInvisible) visibility = View.INVISIBLE }

/**
 * Toggle's view's visibility. If View is [VISIBLE](View.VISIBLE), then sets to [GONE](View.GONE).
 * Else sets [VISIBLE](View.VISIBLE)
 */
fun View.toggle() = run { visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE }

/** Set the enabled state of this view in `true` */
fun View.enable() = run { isEnabled = true }

/** Set the enabled state of this view in `false` */
fun View.disable() = run { isEnabled = false }

/** Adds padding to the view */
fun View.applyPadding(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) = setPadding(left, top, right, bottom)

/** Check if a view is overlapped with another view */
fun View.isVisibleIn(view: View): Boolean {
  val scrollBounds = Rect()
  view.getHitRect(scrollBounds)
  return getLocalVisibleRect(scrollBounds)
}

/** Extension method to return the view location on screen as a [Point]. */
fun View.locationOnScreen(): Point {
  val location = IntArray(2)
  getLocationOnScreen(location)
  return Point(location[0], location[1])
}

/** Extension method to return the view location in window as a [Point]. */
fun View.locationInWindow(): Point {
  val location = IntArray(2)
  getLocationInWindow(location)
  return Point(location[0], location[1])
}

/** Extension method to provide show keyboard for View. */
fun View.showKeyboard() = context.showKeyboard(this)
