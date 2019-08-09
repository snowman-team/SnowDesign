package com.xueqiu.design.dialog

import android.content.Context
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.ContentLoadingProgressBar
import com.xueqiu.design.input.TextInputView
import com.xueqiu.design.R
import com.xueqiu.design.getAttrColor
import com.xueqiu.design.inflate
import com.xueqiu.design.setColorFilter
import com.xueqiu.design.isActivityActive
import java.lang.ref.WeakReference

class SnowInputDialog private constructor(val context: Context?, val builder: Builder) {

    private var mDialog: AlertDialog? = null

    private var mInputView: TextInputView? = null
    private var mTvPositive: TextView? = null
    private var mTvNegative: TextView? = null
    private var mProgress: ContentLoadingProgressBar? = null

    fun show() {
        if (null == context) return
        if (!context.isActivityActive()) {
            return
        }
        if (null != mDialog) {
            mDialog = null
        }
        val dialog = AlertDialog.Builder(context, builder.styleRes)

        val view = context.inflate(R.layout.design_dialog_input)
        mInputView = view.findViewById(R.id.view_input)
        mTvPositive = view.findViewById(R.id.tv_positive)
        mTvNegative = view.findViewById(R.id.tv_negative)
        mProgress = view.findViewById(R.id.progress)
        dialog.setView(view)

        mProgress?.indeterminateDrawable?.setColorFilter(context.getAttrColor(R.attr.colorPrimary))
        mProgress?.hide()
        mInputView?.isErrorEnable = true
        mInputView?.isEditable = true
        mInputView?.inputMode = builder.inputMode
        mInputView?.listener = builder.inputListener
        builder.maxCount?.let {
            mInputView?.maxLimit = it
        }
        builder.hint?.let {
            mInputView?.hint = it
        }
        builder.title?.let {
            dialog.setTitle(it)
        }
        builder.positiveText?.let {
            mTvPositive?.text = it
            mTvPositive?.setOnClickListener {
                builder.positiveAction(this, mInputView?.text ?: "")
            }
        }
        builder.negativeText?.let {
            mTvNegative?.text = it
            mTvNegative?.setOnClickListener {
                builder.negativeAction(this)
            }
        }
        builder.listener?.let { listener ->
            dialog.setOnCancelListener {
                listener.onCancel()
            }
            dialog.setOnDismissListener {
                listener.onFinish()
            }
        }
        mDialog = dialog.show()
        builder.listener?.onShow()
    }

    fun showError(error: String) {
        if (!context.isActivityActive()) {
            return
        }
        if (null == mDialog) {
            return
        }
        mInputView?.warning = error
    }

    fun showTip(tip: String) {
        if (!context.isActivityActive()) {
            return
        }
        if (null == mDialog) {
            return
        }
        mInputView?.tip = tip
    }

    fun showProgress(boolean: Boolean) {
        if (!context.isActivityActive()) {
            return
        }
        if (null == mDialog) {
            return
        }
        if (boolean) {
            mProgress?.show()
        } else {
            mProgress?.hide()
        }
    }

    fun dismiss() {
        if (!context.isActivityActive()) {
            return
        }
        mDialog?.dismiss()
        mDialog = null
    }

    class Builder(context: Context) {

        private var mContextWeak: WeakReference<Context>? = null

        var styleRes: Int = R.style.DesignSnowball_Dialog_Input
            private set

        var title: String? = null
            private set

        @TextInputView.Mode
        var inputMode: Int = TextInputView.TEXT_MODE
            private set

        var hint: String? = null
            private set

        var maxCount: Int? = null
            private set

        var positiveText: String? = null
            private set

        var negativeText: String? = null
            private set

        var positiveAction: (view: SnowInputDialog, inputText: String) -> Unit = { _: SnowInputDialog, _: String -> }
            private set

        var negativeAction: (view: SnowInputDialog) -> Unit = {}
            private set

        var inputListener: TextInputView.TextInputListener? = null
            private set

        var listener: DialogListener? = null
            private set

        init {
            mContextWeak = WeakReference(context)
        }

        fun withStyle(@StyleRes styleRes: Int): Builder {
            this.styleRes = styleRes
            return this
        }

        fun withTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun withInputMode(@TextInputView.Mode mode: Int): Builder {
            this.inputMode = mode
            return this
        }

        fun withHint(hint: String): Builder {
            this.hint = hint
            return this
        }

        fun withMaxCount(maxCount: Int): Builder {
            this.maxCount = maxCount
            return this
        }

        fun withPositiveButton(text: String, action: (view: SnowInputDialog, inputText: String) -> Unit): Builder {
            this.positiveText = text
            this.positiveAction = action
            return this
        }

        fun withNegativeButton(text: String, action: (view: SnowInputDialog) -> Unit): Builder {
            this.negativeText = text
            this.negativeAction = action
            return this
        }

        fun withInputListener(listener: TextInputView.TextInputListener): Builder {
            this.inputListener = listener
            return this
        }

        fun withListener(listener: DialogListener): Builder {
            this.listener = listener
            return this
        }

        fun build(): SnowInputDialog {
            return SnowInputDialog(mContextWeak?.get(), this)
        }

    }

}