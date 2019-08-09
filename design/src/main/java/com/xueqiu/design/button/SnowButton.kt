package com.xueqiu.design.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatTextView
import com.xueqiu.design.R
import com.xueqiu.design.getColorInt
import com.xueqiu.design.setRadius
import com.xueqiu.design.setSelectRipperEffect

class SnowButton : AppCompatTextView {

    companion object {

        const val MODE_CUSTOM = 0
        const val MODE_HOLO = 1
        const val MODE_PRIMARY = 2
    }

    @Mode
    var mode: Int = MODE_CUSTOM

    var radius: Float = 0f

    var enableRipper = false

    private var mEnableDrawable: Drawable? = null

    private var mDisableDrawable: Drawable? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SnowButton,
            0, R.style.DesignTheme
        ).apply {
            try {
                mode = getInt(R.styleable.SnowButton_mode, MODE_CUSTOM)
                radius = getDimension(R.styleable.SnowButton_radius, 0f)
                enableRipper = getBoolean(R.styleable.SnowButton_ripper, false)
            } finally {
                recycle()
            }
        }

        sketch()
    }

    fun sketch() {
        when (mode) {
            MODE_HOLO -> {
                mEnableDrawable = context.getDrawable(R.drawable.design_background_button_holo)
                mDisableDrawable = context.getDrawable(R.drawable.design_background_button_holo_disable)
                setStateDrawable()
            }
            MODE_PRIMARY -> {
                mEnableDrawable = context.getDrawable(R.drawable.design_background_button_primary)
                mDisableDrawable = context.getDrawable(R.drawable.design_background_button_primary_disable)
                setStateDrawable()
                setTextColor(context.getColorInt(R.color.design_blk_255))
            }
            MODE_CUSTOM -> {
                // do nothing
            }
        }
        if (enableRipper) {
            setSelectRipperEffect()
        }
    }

    private fun setStateDrawable() {
        val drawable = StateListDrawable()
        mEnableDrawable?.let {
            drawable.addState(intArrayOf(android.R.attr.state_enabled), it.setRadius(radius))
        }
        mDisableDrawable?.let {
            drawable.addState(intArrayOf(-android.R.attr.state_enabled), it.setRadius(radius))
        }
        this.background = drawable
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(MODE_CUSTOM, MODE_HOLO, MODE_PRIMARY)
    annotation class Mode

}