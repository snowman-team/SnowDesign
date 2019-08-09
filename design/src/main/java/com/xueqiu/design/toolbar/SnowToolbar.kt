package com.xueqiu.design.toolbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import com.xueqiu.design.*

class SnowToolbar : Toolbar {

    private val mTvTitle: TextView

    private var mIsCenterTitle: Boolean = true

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SnowToolbar,
            0, R.style.DesignTheme
        ).apply {
            try {
                mIsCenterTitle = getBoolean(R.styleable.SnowToolbar_center_title, true)
            } finally {
                recycle()
            }
        }

        val space = context.dpToPx(16f).toInt()
        setContentInsetsRelative(space, space)
        val layout = if (mIsCenterTitle) R.layout.design_view_center_text else R.layout.design_view_start_text
        mTvTitle = context.inflate(layout, this, false) as TextView
        addView(mTvTitle)
    }

    fun enableBackNav(listener: OnClickListener) {
        setNavIcon(R.drawable.design_icon_back)
        setNavigationOnClickListener(listener)
    }

    fun setNavIcon(
        @DrawableRes iconRes: Int,
        @ColorInt color: Int = context.getAttrColor(R.attr.colorPrimary)
    ) {
        val icon = context.getDrawable(iconRes)
        icon?.setColorFilter(color)
        navigationIcon = icon
    }

    fun setNavIconColor(@ColorInt color: Int) {
        navigationIcon?.setColorFilter(color)
    }

    fun hideNavIcon() {
        navigationIcon = null
    }

    fun setMenu(@MenuRes resId: Int, listener: OnMenuItemClickListener? = null) {
        inflateMenu(resId)
        setOnMenuItemClickListener(listener)
    }

    override fun setTitle(resId: Int) {
        title = context.getString(resId)
    }

    override fun setTitle(title: CharSequence?) {
        mTvTitle.text = title
    }

    override fun getTitle(): CharSequence = mTvTitle.text ?: ""

    override fun setLogo(@DrawableRes logoID: Int) {
        mTvTitle.setCompoundDrawablesWithIntrinsicBounds(logoID, 0, 0, 0)
    }

    override fun setLogo(drawable: Drawable?) {
        mTvTitle.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
    }

}