package com.robustastudio.forms.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.InputFilter
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.EditText
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.robustastudio.forms.obtainStyledAttributesSafely
import com.robustasutdio.forms.R


/**
 * Created by Amr Elmasry on 08/08/17.
 */

open class FormItem @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyleAttr: Int = 0)
    : TextInputLayout(context, attrs, defStyleAttr) {

    init {
        val editText = TextInputEditText(context)
        addView(editText)

        // init values
        editText.setHintTextColor(Color.parseColor("#9b9b9b"))
        isHintAnimationEnabled = true

        // get attributes
        context.obtainStyledAttributesSafely(attrs, R.styleable.FormItem) {
            val hint = it.getString(R.styleable.FormItem_android_hint)
            val lines = it.getInt(R.styleable.FormItem_android_lines, 1)
            val maxLines = it.getInt(R.styleable.FormItem_android_maxLength, -1)
            val textSize = it.getFloat(R.styleable.FormItem_android_textSize, 12f)
            val textFontPath: String? = it.getString(R.styleable.FormItem_fontPath)
            val drawableRes = it.getResourceId(R.styleable.FormItem_drawable, -1)
            val inputType = it.getInteger(R.styleable.FormItem_inputType, /* text input type */ 0x1)

            this.hint = hint
            editText.setLines(lines)
            editText.textSize = textSize
            editText.inputType = inputType
            if (isPasswordInputType(inputType)) {
                isPasswordVisibilityToggleEnabled = true
            }
            textFontPath?.let {
                val loadedTypeface = Typeface.createFromAsset(getContext().assets, textFontPath)
                setTypeface(loadedTypeface)
            }
            if (drawableRes != -1) {
                setCompoundDrawablesWithIntrinsicBounds(null, drawableRes)
            }
            if (maxLines != -1) {
                editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLines))
                this.isCounterEnabled = true
                this.counterMaxLength = maxLines
            }
        }
    }

    private fun isPasswordInputType(inputType: Int) = inputType == 0x81

    fun setText(text: String) = editText.setText(text)

    override fun getEditText(): EditText = super.getEditText()!!

    fun setCompoundDrawablesWithIntrinsicBounds(start: Drawable? = null, end: Drawable? = null) {
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(start, null, end, null)
    }

    fun setCompoundDrawablesWithIntrinsicBounds(start: Int? = null, end: Int? = null) {
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(start ?: 0, 0, end ?: 0, 0)
    }

    fun getContentAsString(): String = editText.text.toString().trim()

    fun getContentAsPhoneNumber(): Phonenumber.PhoneNumber {
        try {
            return PhoneNumberUtil.getInstance()
                    .parse(editText.text.toString().trim(), "US")
        } catch (e: NumberParseException) {
            throw IllegalStateException("This is invalid number, please use @PhoneNumber " +
                    "for the FormItem to verify its content")
        }
    }

    fun isEmpty(): Boolean = TextUtils.isEmpty(getContentAsString())

    fun getContentAsLong(): Long {
        try {
            return java.lang.Long.parseLong(getContentAsString())
        } catch (e: NumberFormatException) {
            throw IllegalStateException("This must be long value, please use @LongValue " +
                    "for the FormItem to verify its content")
        }
    }
}