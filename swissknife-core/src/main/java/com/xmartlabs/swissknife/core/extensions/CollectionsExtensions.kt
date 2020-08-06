@file:Suppress("unused")

package com.xmartlabs.swissknife.core.extensions

/** Converts any `List` to an `Array` */
inline fun <reified T> List<T>.toArray() = Array(this.count(), this::get)

/** Replaces all elements in a [MutableSet] */
fun <T> MutableSet<T>.replaceAllElements(elements: Iterable<T>?) {
  clear()
  if (elements != null) addAll(elements)
}

/** Replaces all elements in a [MutableList] */
fun <T> MutableList<T>.replaceAllElements(elements: Iterable<T>?) {
  clear()
  elements?.run { addAll(this) }
}

/** Concat the current list with `elements` */
fun <T> List<T>.concat(elements: Iterable<T>?): List<T> = toMutableList().apply {
  if (elements != null) addAll(elements)
}

/** Replaces the element at the `index` position */
fun <E> MutableList<E>.replaceAt(index: Int, element: E) {
  removeAt(index)
  add(index, element)
}
