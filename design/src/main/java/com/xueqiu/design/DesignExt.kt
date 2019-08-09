package com.xueqiu.design

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes

fun Context.inflate(@LayoutRes layoutID: Int, container: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(layoutID, container, attachToRoot)
}

fun Context.dpToPx(value: Float): Float {
    val scale = resources.displayMetrics.density
    return value * scale + 0.5f
}

fun Context.spToPx(value: Float): Float {
    val fontScale = resources.displayMetrics.scaledDensity
    return value * fontScale + 0.5f
}

fun Context.pxToDp(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}

fun Context.pxToSp(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, resources.displayMetrics)
}

fun Context.getAttrDrawable(attrId: Int): Drawable? {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrId, typedValue, true)
    val drawableRes = typedValue.resourceId
    return getDrawable(drawableRes)
}

fun Context.getColorInt(@ColorRes colorRes: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(colorRes, theme)
    } else {
        @Suppress("DEPRECATION")
        resources.getColor(colorRes)
    }
}

fun Context.getAttrColor(attrId: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrId, typedValue, true)
    val colorRes = typedValue.resourceId
    return getColorInt(colorRes)
}

fun Context.getSelectRipperDrawable(): Drawable? {
    val attrs = intArrayOf(android.R.attr.selectableItemBackground)
    val typedArray = obtainStyledAttributes(attrs)
    val ripperDrawable = typedArray.getDrawable(0)
    typedArray.recycle()
    return ripperDrawable
}

fun Context?.isActivityFinished(): Boolean {
    this ?: return false
    return if (this is Activity) {
        this.isActivityFinish()
    } else {
        true
    }
}

fun Context?.isActivityActive(): Boolean {
    this ?: return false
    return if (this is Activity) {
        !this.isActivityFinish()
    } else {
        false
    }
}

fun Activity.isActivityFinish(): Boolean = this.isFinishing || this.isDestroyed

fun View.setSelectRipperEffect() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        foreground = context.getSelectRipperDrawable()
    } else {
        background = context.getSelectRipperDrawable()
    }
}

fun View.setRadius(radius: Float) {
    background?.apply {
        if (this is GradientDrawable) {
            this.cornerRadius = radius
            background = this
        }
    }
}

fun Drawable.setRadius(radius: Float): Drawable {
    if (this is GradientDrawable) {
        cornerRadius = radius
    }
    return this
}

fun Drawable.setColorFilter(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
    } else {
        @Suppress("DEPRECATION")
        this.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
}

fun Bitmap.setGradient(@ColorInt startColor: Int, @ColorInt endColor: Int): Bitmap {
    val updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
    val canvas = Canvas(updatedBitmap)
    canvas.drawBitmap(this, 0f, 0f, null)
    val paint = Paint()
    val shader = LinearGradient(0f, 0f, 0f, height.toFloat(), startColor, endColor, Shader.TileMode.CLAMP)
    paint.shader = shader
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    return updatedBitmap
}