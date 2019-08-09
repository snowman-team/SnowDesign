package com.xueqiu.design.app

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.design.button.SnowButton
import com.xueqiu.design.input.TextInputView
import kotlinx.android.synthetic.main.activity_button.*
import kotlinx.android.synthetic.main.common_toolbar.*

class ButtonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        toolbar.enableBackNav(View.OnClickListener { finish() })
        toolbar.title = "Button"

        check_enable.setOnCheckedChangeListener { _, isChecked ->
            btn_large.isEnabled = isChecked
            btn_normal.isEnabled = isChecked
            btn_small.isEnabled = isChecked
        }
        edt_radius.inputMode = TextInputView.TEXT_NUMBER_MODE
        edt_radius.listener = object : TextInputView.TextInputListener {
            override fun onTextInput(view: TextInputView, text: String?, outOfRange: Boolean) {
                btn_large.radius = text?.toFloatOrNull() ?: 0f
                btn_normal.radius = text?.toFloatOrNull() ?: 0f
                btn_small.radius = text?.toFloatOrNull() ?: 0f
                sketch()
            }
        }
        spinner_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        btn_large.mode = SnowButton.MODE_PRIMARY
                        btn_normal.mode = SnowButton.MODE_PRIMARY
                        btn_small.mode = SnowButton.MODE_PRIMARY
                    }
                    1 -> {
                        btn_large.mode = SnowButton.MODE_HOLO
                        btn_normal.mode = SnowButton.MODE_HOLO
                        btn_small.mode = SnowButton.MODE_HOLO
                    }
                    2 -> {
                        btn_large.mode = SnowButton.MODE_CUSTOM
                        btn_normal.mode = SnowButton.MODE_CUSTOM
                        btn_small.mode = SnowButton.MODE_CUSTOM
                    }
                }
                sketch()
            }

        }
    }

    private fun sketch() {
        btn_large.sketch()
        btn_normal.sketch()
        btn_small.sketch()
    }
}