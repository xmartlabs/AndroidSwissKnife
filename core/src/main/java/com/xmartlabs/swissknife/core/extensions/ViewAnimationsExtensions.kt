@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

import android.content.res.Resources
import android.view.View
import android.view.ViewPropertyAnimator

/** Fades in the View */
fun View.fadeIn(duration: Long = 400): ViewPropertyAnimator? {
  return animate()
      .alpha(1.0f)
      .setDuration(duration)
}

/** Fades out the View */
fun View.fadeOut(duration: Long = 400): ViewPropertyAnimator {
  return animate()
      .alpha(0.0f)
      .setDuration(duration)
}

/** Fades to a specific alpha between 0 to 1 */
fun View.fadeTo(alpha: Float, duration: Long = 400): ViewPropertyAnimator {
  return animate()
      .alpha(alpha)
      .setDuration(duration)
}

/** Animation: Enter from left */
fun View.enterFromLeft(duration: Long = 400): ViewPropertyAnimator? {
  val initialX = x
  x = -width.toFloat()

  return animate()
      .x(initialX)
      .setDuration(duration)
}

/** Animation: Enter from right */
fun View.enterFromRight(duration: Long = 400): ViewPropertyAnimator? {
  val widthPixels = Resources.getSystem().displayMetrics.widthPixels
  val initialX = x
  x = widthPixels.toFloat()

  return animate()
      .x(initialX)
      .setDuration(duration)
}

/** Animation: Enter from top */
fun View.enterFromTop(duration: Long = 400): ViewPropertyAnimator? {
  val initialY = y
  y = -height.toFloat()

  return animate()
      .y(initialY)
      .setDuration(duration)
}

/** Animation: Enter from bottom */
fun View.enterFromBottom(duration: Long = 400): ViewPropertyAnimator {
  val heightPixels = Resources.getSystem().displayMetrics.heightPixels

  val initialY = y
  this.y = heightPixels.toFloat()

  return animate()
      .y(initialY)
      .setDuration(duration)
}

/** Animation: Exit to left */
fun View.exitToLeft(duration: Long = 400): ViewPropertyAnimator = animate()
    .x(-this.width.toFloat())
    .setDuration(duration)

/** Animation: Exit to right */
fun View.exitToRight(duration: Long = 400): ViewPropertyAnimator {
  val widthPixels = Resources.getSystem().displayMetrics.widthPixels

  return animate()
      .x(widthPixels.toFloat())
      .setDuration(duration)
}

/** Animation: Exit to top */
fun View.exitToTop(duration: Long = 400): ViewPropertyAnimator = animate()
    .y(-height.toFloat())
    .setDuration(duration)

/** Animation: Exit to bottom */
fun View.exitToBottom(duration: Long = 400): ViewPropertyAnimator {
  val heightPixels = Resources.getSystem().displayMetrics.heightPixels

  return animate()
      .y(heightPixels.toFloat())
      .setDuration(duration)
}
