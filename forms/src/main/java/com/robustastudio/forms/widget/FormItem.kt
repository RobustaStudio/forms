package com.robustastudio.forms.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.text.InputFilter
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.EditText
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.robustastudio.forms.obtainStyledAttributesSafely
import com.robustastudio.forms.utils.TextDrawable
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
            val lines = it.getInt(R.styleable.FormItem_android_lines, 1)
            val maxLength = it.getInt(R.styleable.FormItem_android_maxLength, -1)
            val textFontPath: String? = it.getString(R.styleable.FormItem_fontPath)
            val drawableRes = it.getResourceId(R.styleable.FormItem_drawable, -1)
            val inputType = it.getInteger(R.styleable.FormItem_inputType, /* text input type */ 0x1)
            val textAsDrawable = it.getString(R.styleable.FormItem_textAsDrawable)
            val textColor = it.getColor(R.styleable.FormItem_android_textColor, Color.BLACK)
            val textAppearanceRes = it.getResourceId(R.styleable.FormItem_android_textAppearance, -1)

            editText.setLines(lines)
            editText.inputType = inputType
            if (isPasswordInputType(inputType)) {
                isPasswordVisibilityToggleEnabled = true
            }
            textFontPath?.let {
                val loadedTypeface = Typeface.createFromAsset(getContext().assets, textFontPath)
                setTypeface(loadedTypeface)
            }
            textAsDrawable?.let {
                val txtDrawable = TextDrawable(textAsDrawable)
                setCompoundDrawablesRelativeWithIntrinsicBounds(txtDrawable)
            }
            if (drawableRes != -1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(drawableRes)
            }
            if (maxLength != -1) {
                editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
                this.isCounterEnabled = true
                this.counterMaxLength = maxLength
            }
            if (textAppearanceRes != -1) {
                editText.setTextAppearance(context, textAppearanceRes)
            }
            editText.setTextColor(textColor)

        }
    }

    private fun isPasswordInputType(inputType: Int) = inputType == 0x81

    fun setText(text: String) = editText.setText(text)

    override fun getEditText(): EditText = super.getEditText()!!

    fun setCompoundDrawablesRelativeWithIntrinsicBounds(end: Drawable? = null) {
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, end, null)
    }

    fun setCompoundDrawablesRelativeWithIntrinsicBounds(end: Int? = null) {
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, end ?: 0, 0)
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