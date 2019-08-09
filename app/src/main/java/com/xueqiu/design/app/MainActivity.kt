package com.xueqiu.design.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.common_toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.title = "Design Demo"

        btn_web.setOnClickListener {
            startActivity(Intent(this, WebActivity::class.java))
        }

        btn_dialog.setOnClickListener {
            startActivity(Intent(this, DialogActivity::class.java))
        }

        btn_anim.setOnClickListener {
            startActivity(Intent(this, AnimActivity::class.java))
        }

        btn_button.setOnClickListener {
            startActivity(Intent(this, ButtonActivity::class.java))
        }

        btn_icon.setOnClickListener {
            startActivity(Intent(this, IconActivity::class.java))
        }

        btn_font.setOnClickListener {
            startActivity(Intent(this, FontActivity::class.java))
        }

        btn_color.setOnClickListener {
            startActivity(Intent(this, ColorActivity::class.java))
        }

        btn_theme.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }

        btn_interpolator.setOnClickListener {
            startActivity(Intent(this, InterpolatorActivity::class.java))
        }

        btn_share.setOnClickListener {
            startActivity(Intent(this, ShareActivity::class.java))
        }

    }

}
