package com.robustastudio.forms.utils

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * Created by Amr on 21/08/17.
 */
class TextDrawable(val text: String) : Drawable() {

    val textPaint = Paint()

    init {
        textPaint.color = Color.parseColor("#405056")
        textPaint.textSize = 22f
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL
        textPaint.textAlign = Paint.Align.CENTER
    }

    override fun draw(canvas: Canvas) {
        canvas.drawText(text, 0f, 0f, textPaint)
    }

    override fun setAlpha(alpha: Int) {
        textPaint.alpha = alpha
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
        textPaint.colorFilter = colorFilter
    }
}