package com.xueqiu.design.app

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.design.dpToPx
import com.xueqiu.design.input.TextInputView
import kotlinx.android.synthetic.main.activity_icon.*
import kotlinx.android.synthetic.main.common_toolbar.*

class IconActivity : AppCompatActivity() {

    private var mColorStr = "#447bd7"
    private val mList by lazy { getAllIcon() }

    private var mCurrentPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icon)

        toolbar.enableBackNav(View.OnClickListener {
            finish()
        })
        toolbar.title = "Icon"

        iv_icon.setImageResource(mList[0].iconRes)
        tv_name.text = mList[0].name

        iv_icon.setColorFilter(Color.parseColor(mColorStr))

        tv_color.text = mColorStr
        tv_color.listener = object : TextInputView.TextInputListener {
            override fun onTextInput(view: TextInputView, text: String?, outOfRange: Boolean) {
                try {
                    iv_icon.setColorFilter(Color.parseColor(text))
                    mColorStr = text ?: "#447bd7"
                    tv_color.warning = ""
                } catch (e: Exception) {
                    tv_color.warning = "无效的十六进制颜色值"
                }
            }
        }

        seek_size.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val size = dpToPx((progress + 10).toFloat())
                changeSize(size)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        btn_prev.setOnClickListener {
            if (mCurrentPos == 0) mCurrentPos = mList.size - 1
            else mCurrentPos--

            iv_icon.setImageResource(mList[mCurrentPos].iconRes)
            tv_name.text = mList[mCurrentPos].name
        }

        btn_next.setOnClickListener {
            if (mCurrentPos == mList.size - 1) mCurrentPos = 0
            else mCurrentPos++

            iv_icon.setImageResource(mList[mCurrentPos].iconRes)
            tv_name.text = mList[mCurrentPos].name
        }

    }

    private fun changeSize(size: Float) {
        val layoutParams = iv_icon.layoutParams
        layoutParams.height = size.toInt()
        layoutParams.width = size.toInt()
        iv_icon.layoutParams = layoutParams
    }

    private fun getAllIcon(): MutableList<IconModel> {
        val list = ArrayList<IconModel>()
        list.add(IconModel(R.drawable.design_icon_xueqiu, "design_icon_xueqiu"))
        list.add(IconModel(R.drawable.design_icon_create, "design_icon_create"))
        list.add(IconModel(R.drawable.design_icon_create_circle, "design_icon_create_circle"))
        list.add(IconModel(R.drawable.design_icon_alert, "design_icon_alert"))
        list.add(IconModel(R.drawable.design_icon_arrow_down, "design_icon_arrow_down"))
        list.add(IconModel(R.drawable.design_icon_arrow_right, "design_icon_arrow_right"))
        list.add(IconModel(R.drawable.design_icon_ash_bin, "design_icon_ash_bin"))
        list.add(IconModel(R.drawable.design_icon_at_money, "design_icon_at_money"))
        list.add(IconModel(R.drawable.design_icon_back, "design_icon_back"))
        list.add(IconModel(R.drawable.design_icon_browser, "design_icon_browser"))
        list.add(IconModel(R.drawable.design_icon_camera, "design_icon_camera"))
        list.add(IconModel(R.drawable.design_icon_character, "design_icon_character"))
        list.add(IconModel(R.drawable.design_icon_character_bold, "design_icon_character_bold"))
        list.add(IconModel(R.drawable.design_icon_character_fontsize, "design_icon_character_fontsize"))
        list.add(IconModel(R.drawable.design_icon_character_head, "design_icon_character_head"))
        list.add(IconModel(R.drawable.design_icon_character_italic, "design_icon_character_italic"))
        list.add(IconModel(R.drawable.design_icon_check, "design_icon_check"))
        list.add(IconModel(R.drawable.design_icon_close, "design_icon_close"))
        list.add(IconModel(R.drawable.design_icon_star, "design_icon_collect"))
        list.add(IconModel(R.drawable.design_icon_comment, "design_icon_comment"))
        list.add(IconModel(R.drawable.design_icon_comment_prohibit, "design_icon_comment_prohibit"))
        list.add(IconModel(R.drawable.design_icon_content, "design_icon_content"))
        list.add(IconModel(R.drawable.design_icon_copy, "design_icon_copy"))
        list.add(IconModel(R.drawable.design_icon_create_cube, "design_icon_create_cube"))
        list.add(IconModel(R.drawable.design_icon_crown, "design_icon_crown"))
        list.add(IconModel(R.drawable.design_icon_detail, "design_icon_detail"))
        list.add(IconModel(R.drawable.design_icon_draft, "design_icon_draft"))
        list.add(IconModel(R.drawable.design_icon_edit, "design_icon_edit"))
        list.add(IconModel(R.drawable.design_icon_emoji, "design_icon_emoji"))
        list.add(IconModel(R.drawable.design_icon_look, "design_icon_look"))
        list.add(IconModel(R.drawable.design_icon_eye_open, "design_icon_eye_open"))
        list.add(IconModel(R.drawable.design_icon_eye_close, "design_icon_eye_close"))
        list.add(IconModel(R.drawable.design_icon_filter, "design_icon_filter"))
        list.add(IconModel(R.drawable.design_icon_forbid, "design_icon_forbid"))
        list.add(IconModel(R.drawable.design_icon_global, "design_icon_global"))
        list.add(IconModel(R.drawable.design_icon_group, "design_icon_group"))
        list.add(IconModel(R.drawable.design_icon_identity, "design_icon_identity"))
        list.add(IconModel(R.drawable.design_icon_image, "design_icon_image"))
        list.add(IconModel(R.drawable.design_icon_information, "design_icon_information"))
        list.add(IconModel(R.drawable.design_icon_like, "design_icon_like"))
        list.add(IconModel(R.drawable.design_icon_link, "design_icon_link"))
        list.add(IconModel(R.drawable.design_icon_market, "design_icon_market"))
        list.add(IconModel(R.drawable.design_icon_mention, "design_icon_mention"))
        list.add(IconModel(R.drawable.design_icon_message, "design_icon_message"))
        list.add(IconModel(R.drawable.design_icon_dollar, "design_icon_dollar"))
        list.add(IconModel(R.drawable.design_icon_dollar_circle, "design_icon_dollar_circle"))
        list.add(IconModel(R.drawable.design_icon_mutual_follow, "design_icon_mutual_follow"))
        list.add(IconModel(R.drawable.design_icon_orientation, "design_icon_orientation"))
        list.add(IconModel(R.drawable.design_icon_permission, "design_icon_permission"))
        list.add(IconModel(R.drawable.design_icon_portfolio, "design_icon_portfolio"))
        list.add(IconModel(R.drawable.design_icon_portfolio_add, "design_icon_portfolio_add"))
        list.add(IconModel(R.drawable.design_icon_portfolio_change, "design_icon_portfolio_change"))
        list.add(IconModel(R.drawable.design_icon_lock, "design_icon_lock"))
        list.add(IconModel(R.drawable.design_icon_question, "design_icon_question"))
        list.add(IconModel(R.drawable.design_icon_radio, "design_icon_fans_push"))
        list.add(IconModel(R.drawable.design_icon_refresh, "design_icon_refresh"))
        list.add(IconModel(R.drawable.design_icon_refresh_circle, "design_icon_refresh_circle"))
        list.add(IconModel(R.drawable.design_icon_regulator, "design_icon_regulator"))
        list.add(IconModel(R.drawable.design_icon_remark, "design_icon_remark"))
        list.add(IconModel(R.drawable.design_icon_remove, "design_icon_remove"))
        list.add(IconModel(R.drawable.design_icon_report, "design_icon_report"))
        list.add(IconModel(R.drawable.design_icon_reset, "design_icon_reset"))
        list.add(IconModel(R.drawable.design_icon_revise, "design_icon_revise"))
        list.add(IconModel(R.drawable.design_icon_reward, "design_icon_reward"))
        list.add(IconModel(R.drawable.design_icon_save, "design_icon_save"))
        list.add(IconModel(R.drawable.design_icon_scan, "design_icon_scan"))
        list.add(IconModel(R.drawable.design_icon_search, "design_icon_search"))
        list.add(IconModel(R.drawable.design_icon_sequence, "design_icon_sequence"))
        list.add(IconModel(R.drawable.design_icon_setting, "design_icon_setting"))
        list.add(IconModel(R.drawable.design_icon_share, "design_icon_share"))
        list.add(IconModel(R.drawable.design_icon_simulation, "design_icon_simulation"))
        list.add(IconModel(R.drawable.design_icon_statement, "design_icon_statement"))
        list.add(IconModel(R.drawable.design_icon_sun, "design_icon_sun"))
        list.add(IconModel(R.drawable.design_icon_topic, "design_icon_topic"))
        list.add(IconModel(R.drawable.design_icon_topping, "design_icon_topping"))
        list.add(IconModel(R.drawable.design_icon_trade, "design_icon_trade"))
        list.add(IconModel(R.drawable.design_icon_transmit, "design_icon_transmit"))
        list.add(IconModel(R.drawable.design_icon_warrants, "design_icon_warrants"))
        list.add(IconModel(R.drawable.design_icon_unfollow, "design_icon_unfollow"))
        list.add(IconModel(R.drawable.design_icon_upload_image, "design_icon_upload_image"))

        list.add(IconModel(R.drawable.design_icon_tips_attention, "design_icon_tips_attention"))
        list.add(IconModel(R.drawable.design_icon_tips_close, "design_icon_tips_close"))

        list.add(IconModel(R.drawable.design_icon_nav_close, "design_icon_nav_close"))
        list.add(IconModel(R.drawable.design_icon_nav_information, "design_icon_nav_information"))
        list.add(IconModel(R.drawable.design_icon_nav_more, "design_icon_nav_more"))
        return list
    }
}