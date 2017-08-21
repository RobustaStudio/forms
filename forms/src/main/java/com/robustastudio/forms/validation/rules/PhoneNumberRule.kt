package com.robustastudio.forms.validation.rules

/**
 * Created by Amr Elmasry on 08/08/17.
 */

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.mobsandgeeks.saripaar.ContextualAnnotationRule
import com.mobsandgeeks.saripaar.ValidationContext
import com.robustastudio.forms.validation.annotations.PhoneNumber

class PhoneNumberRule(validationContext: ValidationContext, phoneNumber: PhoneNumber) :
        ContextualAnnotationRule<PhoneNumber, String>(validationContext, phoneNumber) {

    override fun isValid(phoneNumberStr: String): Boolean {
        try {
            val phoneNumber =
                    PhoneNumberUtil.getInstance().parse(phoneNumberStr.trim(),
                            PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY)
            return PhoneNumberUtil.getInstance().isValidNumber(phoneNumber)
        } catch (e: NumberParseException) {
            e.printStackTrace()
            return false
        }
    }
}
