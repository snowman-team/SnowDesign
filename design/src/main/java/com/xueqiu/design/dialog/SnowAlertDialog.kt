package com.xueqiu.design.dialog

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import com.xueqiu.design.R
import com.xueqiu.design.isActivityActive
import java.lang.ref.WeakReference

class SnowAlertDialog private constructor(val context: Context?, val builder: Builder) {

    private var mDialog: AlertDialog? = null

    fun show() {
        if (null == context) return
        if (!context.isActivityActive()) {
            return
        }
        if (null != mDialog) {
            mDialog = null
        }
        val dialog = AlertDialog.Builder(context, builder.styleRes)
        builder.positiveText?.let {
            dialog.setPositiveButton(it) { _, _ ->
                builder.positiveAction(this)
            }
        }
        builder.iconRes?.let {
            dialog.setIcon(it)
        }
        builder.title?.let {
            dialog.setTitle(it)
        }
        builder.content?.let {
            dialog.setMessage(it)
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

    fun dismiss() {
        if (!context.isActivityActive()) {
            return
        }
        mDialog?.dismiss()
        mDialog = null
    }

    class Builder(context: Context) {

        private var mContextWeak: WeakReference<Context>? = null

        var positiveText: String? = null
            private set

        var positiveAction: (view: SnowAlertDialog) -> Unit = {}
            private set

        var title: String? = null
            private set

        var content: String? = null
            private set

        @DrawableRes
        var iconRes: Int? = null
            private set

        var styleRes: Int = R.style.DesignSnowball_Dialog_Alert
            private set

        var listener: DialogListener? = null
            private set

        init {
            mContextWeak = WeakReference(context)
        }

        fun withPositiveButton(text: String, action: (view: SnowAlertDialog) -> Unit): Builder {
            this.positiveText = text
            this.positiveAction = action
            return this
        }

        fun withTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun withContent(content: String): Builder {
            this.content = content
            return this
        }

        fun withIcon(@DrawableRes iconRes: Int): Builder {
            this.iconRes = iconRes
            return this
        }

        fun withStyle(@StyleRes styleRes: Int): Builder {
            this.styleRes = styleRes
            return this
        }

        fun withListener(listener: DialogListener): Builder {
            this.listener = listener
            return this
        }

        fun build(): SnowAlertDialog {
            return SnowAlertDialog(mContextWeak?.get(), this)
        }

    }

}