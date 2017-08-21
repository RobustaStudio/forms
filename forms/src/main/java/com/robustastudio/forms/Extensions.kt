package com.robustastudio.forms

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

/**
 * Created by Amr Elmasry on 08/08/17.
 */

fun Context.obtainStyledAttributesSafely(set: AttributeSet?, attrs: IntArray, action: (TypedArray) -> Unit) {
    val a = obtainStyledAttributes(set, attrs)
    action(a)
    a.recycle()
}