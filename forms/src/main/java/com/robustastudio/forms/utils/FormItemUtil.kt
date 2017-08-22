package com.robustastudio.forms.utils

import com.robustastudio.forms.widget.FormItem

object FormItemUtil {

    @JvmStatic
    fun removeErrorHintIfFound(formItem: FormItem) {
        if (formItem.error != null) {
            formItem.error = null
            formItem.isErrorEnabled = false
        }
    }

    @JvmStatic
    fun removeErrorHintIfFound(vararg formItems: FormItem) {
        for (formItem in formItems) {
            removeErrorHintIfFound(formItem)
        }
    }
}
