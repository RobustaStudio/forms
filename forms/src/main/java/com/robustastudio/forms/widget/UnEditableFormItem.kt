package com.robustastudio.forms.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent


/**
 * Created by Amr Elmasry on 08/08/17.
 */

class UnEditableFormItem @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
        FormItem(context, attrs) {

    var listener: OnFormItemClickedListener? = null

    init {
        isFocusable = false
        editText.isFocusable = false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            listener?.let {
                listener?.onFormItemClicked(this)
                return true
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    interface OnFormItemClickedListener {
        fun onFormItemClicked(unEditableFormItem: UnEditableFormItem)
    }

}