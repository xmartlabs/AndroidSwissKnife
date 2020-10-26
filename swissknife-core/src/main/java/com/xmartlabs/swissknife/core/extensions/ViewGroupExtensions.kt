package com.xmartlabs.swissknife.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


/** Inflates a view using the [this] context. */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attach: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attach)

/** Returns the child at [index] position */
operator fun ViewGroup.get(index: Int): View? = getChildAt(index)

/** Removes the [child] view. */
operator fun ViewGroup.minusAssign(child: View) = removeView(child)

/** Adds the [child] view. */
operator fun ViewGroup.plusAssign(child: View) = addView(child)

/** Checks if [this] contains the [child]. */
fun ViewGroup.contains(child: View) = indexOfChild(child) != -1

/** Returns the first child. */
fun ViewGroup.first(): View? = this[0]

/** Performs the given [action] on each child. */
inline fun ViewGroup.forEach(action: (View) -> Unit) {
  for (i in 0 until childCount) {
    action(getChildAt(i))
  }
}

/** Performs the given [action] on each child, providing sequential index with the element. */
inline fun ViewGroup.forEachIndexed(action: (Int, View) -> Unit) {
  for (i in 0 until childCount) {
    action(i, getChildAt(i))
  }
}

/** Returns a children [Iterable] */
fun ViewGroup.children() = object : Iterable<View> {
  override fun iterator() = object : Iterator<View> {
    var index = 0
    override fun hasNext() = index < childCount
    override fun next() = getChildAt(index++)
  }
}
