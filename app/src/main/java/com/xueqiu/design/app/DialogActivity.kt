package com.xueqiu.design.app

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.design.dialog.*
import com.xueqiu.design.input.TextInputView
import kotlinx.android.synthetic.main.activity_dialog.*
import kotlinx.android.synthetic.main.common_toolbar.*

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        toolbar.enableBackNav(View.OnClickListener {
            finish()
        })

        toolbar.title = "Dialog"

        val tvTitle = findViewById<TextInputView>(R.id.tv_title)
        val tvContent = findViewById<TextInputView>(R.id.tv_content)
        val tvHint = findViewById<TextInputView>(R.id.tv_hint)
        val checkIcon = findViewById<CheckBox>(R.id.checkbox_icon)

        tvTitle.text = "这是标题"
        tvContent.text = "这是内容"
        tvHint.text = "这是提示语"

        btn_alert.setOnClickListener {
            val builder = SnowAlertDialog.Builder(this)
                .withTitle(tvTitle.text)
                .withContent(tvContent.text)
                .withPositiveButton("ok") { view ->
                    view.dismiss()
                }
            if (checkIcon.isChecked) {
                builder.withIcon(R.drawable.design_image_logo)
            }
            builder.build().show()
        }

        btn_confirm.setOnClickListener {
            val builder = SnowConfirmDialog.Builder(this)
                .withTitle(tvTitle.text)
                .withContent(tvContent.text)
                .withPositiveButton("ok") { view ->
                    Toast.makeText(this, "确认成功", Toast.LENGTH_LONG).show()
                    view.dismiss()
                }
                .withNegativeButton("cancel") { view -> view.dismiss() }
            if (checkIcon.isChecked) {
                builder.withIcon(R.drawable.design_image_logo)
            }
            builder.build().show()
        }

        btn_tip.setOnClickListener {
            val dialog = SnowTipDialog.Builder(this)
                .withContent(tvContent.text)
                .withPositiveButton("ok") { view -> view.dismiss() }
                .build()
            dialog.show()
        }

        btn_input.setOnClickListener {
            val dialog = SnowInputDialog.Builder(this)
                .withHint(tvHint.text)
                .withTitle(tvTitle.text)
                .withMaxCount(10)
                .withPositiveButton("确认") { view, text ->
                    if (text.isNotEmpty() && text.length <= 10) {
                        view.showProgress(true)
                        Handler().postDelayed({
                            Toast.makeText(this, "成功", Toast.LENGTH_LONG).show()
                            view.showProgress(false)
                            view.dismiss()
                        }, 3000)
                    }
                }
                .withNegativeButton("取消") { view ->
                    view.dismiss()
                }
                .withInputListener(object : TextInputView.TextInputListener {
                    override fun onTextInput(view: TextInputView, text: String?, outOfRange: Boolean) {
                        if (outOfRange) {
                            view.warning = "已超出字数限制"
                        } else {
                            view.warning = ""
                        }
                    }
                })
                .build()
            dialog.show()
        }

        btn_custom.setOnClickListener {
            val builder = SnowDialog.Builder(this, R.layout.view_custom_dialog)
            builder.build().show()
        }

        btn_bottom.setOnClickListener {
            ExampleBottomSheet(this).show()
        }

        btn_progress.setOnClickListener {
            val progress = SnowProgressDialog.Builder(this).build()
            progress.show()

            Handler().postDelayed({
                progress.dismiss()
            }, 3000)
        }
    }
}