@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable

/** Transforms a [Bitmap] into a [BitmapDrawable] */
fun Bitmap.toBitmapDrawable() = BitmapDrawable(Resources.getSystem(), this)

