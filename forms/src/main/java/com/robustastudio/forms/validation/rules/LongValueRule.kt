package com.robustastudio.forms.validation.rules

/**
 * Created by Amr Elmasry on 08/08/17.
 */

import com.mobsandgeeks.saripaar.ContextualAnnotationRule
import com.mobsandgeeks.saripaar.ValidationContext
import com.robustastudio.forms.validation.annotations.LongValue

class LongValueRule(validationContext: ValidationContext, longValue: LongValue) :
        ContextualAnnotationRule<LongValue, String>(validationContext, longValue) {

    override fun isValid(value: String): Boolean {
        try {
            // just try to parse it
            value.toLong()
        } catch (e: NumberFormatException) {
            return false
        }
        return true
    }
}
