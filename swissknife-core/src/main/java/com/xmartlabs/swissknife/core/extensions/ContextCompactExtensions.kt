@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

import android.content.Context
import androidx.core.content.ContextCompat
import java.io.File

/** @see <a href="http://developer.android.com/reference/android/support/v4/content/ContextCompat.html#getColor(android.content.Context,%20int)">getColor(Context, int)</a>  */
fun Context.getColorCompat(id: Int) = ContextCompat.getColor(this, id)

/** @see <a href="http://developer.android.com/reference/android/support/v4/content/ContextCompat.html#checkSelfPermission(android.content.Context,%20java.lang.String)">checkSelfPermission (Context, String)</a>  */
fun Context.checkSelfPermissionCompat(permission: String) = ContextCompat.checkSelfPermission(this, permission)

/** @see <a href="http://developer.android.com/reference/android/support/v4/content/ContextCompat.html#getCodeCacheDir(android.content.Context)">getCodeCacheDir (Context)</a> */
fun Context.getCodeCacheDirCompat(): File = ContextCompat.getCodeCacheDir(this)

/** @see <a href="http://developer.android.com/reference/android/support/v4/content/ContextCompat.html#getColorStateList(android.content.Context,%20int)">getColorStateList(Context, int)</a> */
fun Context.getColorStateListCompat(id: Int) = ContextCompat.getColorStateList(this, id)

/** @see <a href="http://developer.android.com/reference/android/support/v4/content/ContextCompat.html#getDrawable(android.content.Context,%20int)">getDrawable(Context, int)</a> */
fun Context.getDrawableCompat(id: Int) = ContextCompat.getDrawable(this, id)

/** @see <a href="http://developer.android.com/reference/android/support/v4/content/ContextCompat.html#getExternalCacheDirs(android.content.Context)>getExternalCacheDirs(Context)</a> */
fun Context.getExternalCacheDirsCompat(): Array<File> = ContextCompat.getExternalCacheDirs(this)

/** @see <a href="http://developer.android.com/reference/android/support/v4/content/ContextCompat.html#getExternalFilesDirs(android.content.Context,%20java.lang.String)">getExternalFilesDirs(Context, String)</a>  */
fun Context.getExternalFilesDirsCompat(type: String): Array<File> = ContextCompat.getExternalFilesDirs(this, type)

/** @see <a href="http://developer.android.com/reference/android/support/v4/content/ContextCompat.html#getObbDirs(android.content.Context)">getObbDirs(Context)</a>  */
fun Context.getObbDirsCompat(): Array<File> = ContextCompat.getObbDirs(this)
