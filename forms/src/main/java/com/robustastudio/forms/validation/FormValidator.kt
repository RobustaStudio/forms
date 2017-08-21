package com.robustastudio.forms.validation

import android.support.design.widget.TextInputLayout
import android.widget.EditText
import android.widget.Toast

import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.adapter.ViewDataAdapter
import com.mobsandgeeks.saripaar.exception.ConversionException
import com.robustastudio.forms.validation.annotations.LongValue
import com.robustastudio.forms.validation.annotations.PhoneNumber
import com.robustastudio.forms.widget.FormItem
import com.robustastudio.forms.widget.UnEditableFormItem

/**
 * Created by Amr Elmasry on 08/08/17.
 */

/**
 * Util class to support [TextInputLayout], [FormItem] and [UnEditableFormItem] class
 */
object FormValidator {

    /**
     * get new Validator Instance that supports [TextInputLayout], [FormItem]
     * and [UnEditableFormItem]

     * @return new Validator instance
     */
    fun newInstance(controller: Any,
                    defaultFormValidator: DefaultFormValidator): Validator {
        val validator = Validator(controller)
        validator.setValidationListener(defaultFormValidator)
        // register adapters
        validator.registerAdapter(TextInputLayout::class.java, SupportViewDataAdapter<TextInputLayout>())
        validator.registerAdapter(FormItem::class.java, SupportViewDataAdapter<FormItem>())
        validator.registerAdapter(UnEditableFormItem::class.java, SupportViewDataAdapter<UnEditableFormItem>())
        // register annotations
        Validator.registerAnnotation(PhoneNumber::class.java)
        Validator.registerAnnotation(LongValue::class.java)
        return validator
    }

    abstract class DefaultFormValidator : Validator.ValidationListener {

        override fun onValidationFailed(errors: MutableList<ValidationError>) {
            for (error in errors) {
                val view = error.view
                val message = error.getCollatedErrorMessage(view.context)

                // Display error messages ;)
                if (view is EditText) {
                    view.error = message
                } else if (view is TextInputLayout) {
                    view.error = message
                } else {
                    Toast.makeText(view.context, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    class SupportViewDataAdapter<T : TextInputLayout> : ViewDataAdapter<T, String> {

        @Throws(ConversionException::class)
        override fun getData(view: T): String {
            val editText = view.editText ?: throw IllegalStateException("Can't add validation to TextInputLayout that" + " doesn't wrap an editText view")
            return editText.text.toString()
        }
    }
}
