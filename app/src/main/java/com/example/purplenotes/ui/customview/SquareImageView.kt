package com.example.purplenotes.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Author: Jayden Nguyen
 * Created date: 8/4/2019
 */
class SquareImageView: ImageView {
    constructor(context: Context):super(context)
    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context,attributeSet, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }
}