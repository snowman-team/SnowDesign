package com.xueqiu.design.input

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.IntDef
import com.google.android.material.textfield.TextInputLayout
import com.xueqiu.design.R
import com.xueqiu.design.inflate


class TextInputView : LinearLayout {

    private var mTextInput: TextInputLayout
    private var mEdtInput: AutoCompleteInput

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        val view = context.inflate(R.layout.design_view_text_input, this, true)
        mTextInput = view.findViewById(R.id.layout_text_input) as TextInputLayout
        mEdtInput = view.findViewById(R.id.edt_input) as AutoCompleteInput

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TextInputView,
            0, R.style.DesignTheme
        ).apply {
            try {
                isErrorEnable = getBoolean(R.styleable.TextInputView_error_enable, true)
                maxLines = getInteger(R.styleable.TextInputView_max_lines, 0)
                maxLimit = getInt(R.styleable.TextInputView_max_limit, 0)
                hint = getString(R.styleable.TextInputView_hint)
                isEditable = getBoolean(R.styleable.TextInputView_editable, true)
            } finally {
                recycle()
            }
        }

        init()
    }

    companion object InputMode {
        const val TEXT_MODE = 1001
        const val TEXT_PASSWORD_MODE = 1002
        const val TEXT_NUMBER_MODE = 1003
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(TEXT_MODE, TEXT_PASSWORD_MODE, TEXT_NUMBER_MODE)
    annotation class Mode

    var isEditable = true
        set(value) {
            field = value
            mEdtInput.isFocusable = value
            mEdtInput.isFocusableInTouchMode = value
        }

    var isErrorEnable = true
        set(value) {
            field = value
            mTextInput.isErrorEnabled = value
        }

    @Mode
    var inputMode: Int = TEXT_MODE
        set(value) {
            field = value
            sketch()
        }

    var hint: String?
        get() = mTextInput.hint.toString()
        set(value) {
            mTextInput.hint = value
        }

    var text: String
        get() = mEdtInput.text.toString()
        set(value) {
            mEdtInput.setText(value)
            mEdtInput.setSelection(value.length)
        }

    var tip: String = ""
        get() = mTextInput.error.toString()
        set(value) {
            if (field == value) return
            field = value
            mTextInput.setErrorTextAppearance(R.style.DesignSnowball_TextInputTip)
            if (value.isEmpty()) {
                mTextInput.error = null
            } else {
                mTextInput.error = field
            }
        }

    var warning: String = ""
        get() = mTextInput.error.toString()
        set(value) {
            if (field == value) return
            field = value
            mTextInput.setErrorTextAppearance(R.style.DesignSnowball_TextInputError)
            if (value.isEmpty()) {
                mTextInput.error = null
            } else {
                mTextInput.error = field
            }
        }

    var listener: TextInputListener? = null

    var maxLimit: Int = -1
        set(value) {
            field = value
            mTextInput.counterMaxLength = field
            mTextInput.isCounterEnabled = value > 0
        }

    var maxLines: Int = -1
        set(value) {
            field = value
            if (field > 0) {
                mEdtInput.maxLines = field
            }
        }

    private fun init() {
        setListener()
        sketch()
    }

    private fun setListener() {
        mEdtInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val outOfRange = maxLimit != -1 && s?.length ?: 0 > maxLimit
                if (null == listener) {
                    warning = ""
                }
                listener?.onTextInput(this@TextInputView, s?.toString(), outOfRange)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun sketch() {
        when (inputMode) {
            TEXT_MODE -> sketchTextMode()
            TEXT_PASSWORD_MODE -> sketchPasswordMode()
            TEXT_NUMBER_MODE -> sketchNumberMode()
        }
    }

    private fun sketchTextMode() {
        mEdtInput.inputType = InputType.TYPE_CLASS_TEXT
    }

    private fun sketchPasswordMode() {
        mEdtInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        mTextInput.isPasswordVisibilityToggleEnabled = true
    }

    private fun sketchNumberMode() {
        mEdtInput.inputType = InputType.TYPE_CLASS_NUMBER
    }

    interface TextInputListener {
        fun onTextInput(view: TextInputView, text: String?, outOfRange: Boolean)
    }
}