package com.robustastudio.forms.validation.annotations

import com.mobsandgeeks.saripaar.annotation.ValidateUsing
import com.robustastudio.forms.validation.rules.PhoneNumberRule

/**
 * Created by Amr Elmasry on 08/08/17.
 */


import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@ValidateUsing(PhoneNumberRule::class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
annotation class PhoneNumber(val sequence: Int = -1,
                             val message: String = "Invalid phone number",
                             val messageResId: Int = -1)