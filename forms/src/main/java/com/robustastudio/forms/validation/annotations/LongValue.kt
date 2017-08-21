package com.robustastudio.forms.validation.annotations

/**
 * Created by Amr Elmasry on 08/08/17.
 */

import com.mobsandgeeks.saripaar.annotation.ValidateUsing
import com.robustastudio.forms.validation.rules.LongValueRule
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@ValidateUsing(LongValueRule::class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
annotation class LongValue(val sequence: Int = -1,
                           val message: String = "This field should contains only numbers",
                           val messageResId: Int = -1)
