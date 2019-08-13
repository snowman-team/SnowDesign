package com.xueqiu.design.anim

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.IntDef
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.RenderMode

class SnowAnimView : FrameLayout {

    private val mAnimView: LottieAnimationView

    companion object {

        const val STATE_NONE = -1
        const val STATE_READY = 0
        const val STATE_PLAYING = 1
        const val STATE_PAUSE = 2

        const val ERROR_ILLEGAL_ACTION = 1000
        const val ERROR_NOT_READY = 1001
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(STATE_NONE,
            STATE_READY,
            STATE_PLAYING,
            STATE_PAUSE)
    annotation class State

    @State
    var animState: Int = STATE_NONE
        private set(newValue) {
            val oldVal = field
            field = newValue
            listener?.onStateChanged(oldVal, newValue)
        }

    var assetsFolder: String = ""
        set(value) {
            field = value
            mAnimView.imageAssetsFolder = value
        }

    var animFileRes: Int = 0
        private set

    var isLoop: Boolean = false
        set(value) {
            field = value
            if (field) {
                mAnimView.repeatCount = LottieDrawable.INFINITE
            } else {
                mAnimView.repeatCount = 0
            }
        }

    var listener: AnimListener? = null

    var isAutoPlay: Boolean = false

    private var mAutoPause = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        mAnimView = LottieAnimationView(context)
        mAnimView.layoutParams = ViewGroup.LayoutParams(context, attrs)
        mAnimView.setRenderMode(RenderMode.SOFTWARE)
        addView(mAnimView)

        mAnimView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                if (animState != STATE_PLAYING) {
                    animState = STATE_PLAYING
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (animState == STATE_PLAYING) {
                    animState = STATE_READY
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
                if (animState == STATE_PLAYING || animState == STATE_PAUSE) {
                    animState = STATE_READY
                }
            }

        })

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isAutoPlay) {
            start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        when (visibility) {
            View.GONE, View.INVISIBLE -> {
                if (animState == STATE_PLAYING) {
                    pause()
                    mAutoPause = true
                }
            }
            View.VISIBLE -> {
                if (animState == STATE_PAUSE && mAutoPause) {
                    resume()
                }
            }
        }
    }

    fun getMinFrame() = mAnimView.minFrame

    fun getMaxFrame() = mAnimView.maxFrame

    fun getCurrentFrame() = mAnimView.frame

    fun setAnimFileRes(animFileRes: Int) {
        mAnimView.setAnimation(animFileRes)
        animState = if (animFileRes != 0) {
            STATE_READY
        } else {
            STATE_NONE
        }
        if (isAutoPlay) {
            start()
        }
    }


    fun seekTo(frame: Int) {
        when (animState) {
            STATE_READY, STATE_PAUSE, STATE_PLAYING -> mAnimView.frame = frame
            else -> listener?.onError(
                    ERROR_ILLEGAL_ACTION,
                    "Illegal action, please check your current state is correct"
            )
        }

    }

    fun start() {
        when (animState) {
            STATE_NONE -> listener?.onError(ERROR_NOT_READY, "Please set anim file before start animation")
            STATE_READY -> {
                mAnimView.playAnimation()
                animState = STATE_PLAYING
            }
            STATE_PLAYING -> {
                // do nothing
            }
            else -> listener?.onError(
                    ERROR_ILLEGAL_ACTION,
                    "Illegal action, please check your current state is correct before start"
            )
        }
    }

    fun pause() {
        when (animState) {
            STATE_PLAYING -> {
                mAnimView.pauseAnimation()
                animState = STATE_PAUSE
            }
            STATE_PAUSE -> {
                // do nothing
            }
            else -> listener?.onError(
                    ERROR_ILLEGAL_ACTION,
                    "Illegal action, please check your current state is correct before pause"
            )
        }
    }

    fun resume() {
        when (animState) {
            STATE_PLAYING -> {
                // do nothing
            }
            STATE_PAUSE -> {
                mAnimView.resumeAnimation()
                animState = STATE_PLAYING
            }
            else -> listener?.onError(
                    ERROR_ILLEGAL_ACTION,
                    "Illegal action, please check your current state is correct before resume"
            )
        }
    }

    fun stop() {
        when (animState) {
            STATE_PLAYING, STATE_PAUSE -> {
                mAnimView.cancelAnimation()
                animState = STATE_READY
            }
            STATE_READY -> {
                // do nothing
            }
            else -> listener?.onError(
                    ERROR_ILLEGAL_ACTION,
                    "Illegal action, please check your current state is correct before stop"
            )
        }
    }

    interface AnimListener {
        fun onStateChanged(oldState: Int, newState: Int)
        fun onError(code: Int, msg: String)
    }

}