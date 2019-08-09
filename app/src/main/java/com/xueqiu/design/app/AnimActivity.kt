package com.xueqiu.design.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.design.anim.SnowAnimView
import kotlinx.android.synthetic.main.activity_anim.*
import kotlinx.android.synthetic.main.common_toolbar.*

class AnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

        toolbar.enableBackNav(View.OnClickListener {
            finish()
        })

        toolbar.title = "Anim"

        testAnimView()
    }

    private fun testAnimView() {

        val animView = findViewById<SnowAnimView>(R.id.view_anim)

        checkbox.isChecked = animView.isAutoPlay
        checkbox_loop.isChecked = animView.isLoop
        checkbox.setOnCheckedChangeListener { _, isChecked ->
            animView.isAutoPlay = isChecked
        }
        checkbox_loop.setOnCheckedChangeListener { _, isChecked ->
            animView.isLoop = isChecked
        }
        animView.setAnimFileRes(R.raw.loading)

        animView.listener = object : SnowAnimView.AnimListener {
            override fun onStateChanged(oldState: Int, newState: Int) {
                tv_state.text = when (newState) {
                    SnowAnimView.STATE_NONE -> "none"
                    SnowAnimView.STATE_PAUSE -> "pause"
                    SnowAnimView.STATE_PLAYING -> "playing"
                    SnowAnimView.STATE_READY -> {
                        val length = animView.getMaxFrame() - animView.getMinFrame()
                        "ready"
                    }
                    else -> "unknown"
                }
            }

            override fun onError(code: Int, msg: String) {
                Toast.makeText(this@AnimActivity, "Error", Toast.LENGTH_LONG).show()
            }

        }

        btn_start.setOnClickListener {
            animView.start()
        }

        btn_stop.setOnClickListener {
            animView.stop()
            animView.seekTo(0)
        }

        btn_pause.setOnClickListener {
            animView.pause()
        }

        btn_resume.setOnClickListener {
            animView.resume()
        }

        animView.setAnimFileRes(R.raw.loading)
    }
}