package com.xueqiu.design.app

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.design.button.SnowButton
import com.xueqiu.design.input.TextInputView
import com.xueqiu.design.interpolator.*
import kotlinx.android.synthetic.main.common_toolbar.*


class InterpolatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_interpolator)
        toolbar.enableBackNav(View.OnClickListener {
            finish()
        })

        toolbar.title = "Interpolator"
        val view = findViewById<View>(R.id.view)
        val defaultView = findViewById<View>(R.id.view_default)
        val spinner = findViewById<Spinner>(R.id.spinner_inter)
        val btnStart = findViewById<SnowButton>(R.id.btn_start)
        val duration = findViewById<TextInputView>(R.id.edt_duration)
        duration.inputMode = TextInputView.TEXT_NUMBER_MODE
        duration.text = "3000"

        val defaultAnim = ObjectAnimator.ofFloat(defaultView, "translationX", 0.0f, 800.0f)
        defaultAnim.duration = 3000
        defaultAnim.interpolator = LinearInterpolator()

        val animator = ObjectAnimator.ofFloat(view, "translationX", 0.0f, 800.0f)
        animator.duration = 3000
        animator.interpolator = EaseInInterpolator()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        animator.interpolator = EaseInInterpolator()
                    }
                    1 -> {
                        animator.interpolator = EaseOutInterpolator()
                    }
                    2 -> {
                        animator.interpolator = EaseInOutInterpolator()
                    }
                    3 -> {
                        animator.interpolator = EaseInterpolator()
                    }
                    4 -> {
                        animator.interpolator = ShowCurveGraphInterpolator()
                    }
                    5 -> {
                        animator.interpolator = ShowCurveHighInterpolator()
                    }
                    6 -> {
                        animator.interpolator = ShowCurveLowInterpolator()
                    }
                }
            }

        }

        btnStart.setOnClickListener {
            val time: Long = try {
                duration.text.toLong()
            } catch (e: Exception) {
                3000
            }
            animator.duration = time
            defaultAnim.duration = time
            animator.start()
            defaultAnim.start()
        }
    }
}