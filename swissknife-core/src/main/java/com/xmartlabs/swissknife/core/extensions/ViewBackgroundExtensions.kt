@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

import android.util.TypedValue
import android.view.View

/** Sets the view's background as a ripple rectangle */
fun View.setRectangleRippleBackground() = with(TypedValue()) {
  context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
  setBackgroundResource(resourceId)
}

/** Sets the view's background as a ripple circle */
fun View.setCircleRippleBackground() = with(TypedValue()) {
  context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
  setBackgroundResource(resourceId)
}
