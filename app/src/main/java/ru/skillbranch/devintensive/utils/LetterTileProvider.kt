package ru.skillbranch.devintensive.utils

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.*
import android.text.TextPaint
import android.util.TypedValue
import ru.skillbranch.devintensive.R



class LetterTileProvider
    (private val context: Context) {

    private val mPaint = TextPaint()
    private val mBounds = Rect()
    private val mCanvas = Canvas()

    //    private final TypedArray mColors;
    private val mTileLetterFontSize: Int

    init {
        val res = context.resources
        mPaint.typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)
        mPaint.color = Color.WHITE
        mPaint.textAlign = Paint.Align.CENTER
        mPaint.isAntiAlias = true

        mTileLetterFontSize = res.getDimensionPixelSize(R.dimen.tile_letter_font_size)
    }

    fun getLetterTile(initials: String, width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val c = mCanvas
        c.setBitmap(bitmap)
//        c.drawColor(context.resources.getColor(R.color.color_accent, context.theme))
        c.drawColor(fetchAccentColor())

        mPaint.textSize = mTileLetterFontSize.toFloat()
        mPaint.getTextBounds(initials.toCharArray(), 0, 1, mBounds)
        c.drawText(
            initials.toCharArray(), 0, 2, (0 + width / 2).toFloat(), (0 + height / 2
                    + (mBounds.bottom - mBounds.top) / 2).toFloat(), mPaint
        )
        return bitmap
    }

    private fun fetchAccentColor(): Int {
        val typedValue = TypedValue()

        val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
        val color = a.getColor(0, 0)

        a.recycle()

        return color
    }
}