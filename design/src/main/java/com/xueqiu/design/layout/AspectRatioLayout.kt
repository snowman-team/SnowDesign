package com.xueqiu.design.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.xueqiu.design.R

open class AspectRatioLayout : RelativeLayout {

    private var mAspectRadio: Float = 0.toFloat()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.AspectRatioLayout,
                0, R.style.DesignTheme
        ).apply {
            try {
                mAspectRadio = getFloat(R.styleable.AspectRatioLayout_fixed_aspectRadio, 1f)
            } finally {
                recycle()
            }
        }
    }

    fun setAspectRadio(aspectRadio: Float) {
        mAspectRadio = aspectRadio
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val originHeight = MeasureSpec.getSize(heightMeasureSpec)
        val originWidth = MeasureSpec.getSize(widthMeasureSpec)

        val calculateHeight = (originWidth / mAspectRadio).toInt()
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(calculateHeight, MeasureSpec.EXACTLY))

    }
}
