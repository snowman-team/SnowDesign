package com.xueqiu.design.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilehacker.lego.LegoAdapter
import com.smilehacker.lego.util.NoAlphaDefaultItemAnimator
import com.xueqiu.design.getColorInt
import kotlinx.android.synthetic.main.activity_color.*
import kotlinx.android.synthetic.main.common_toolbar.*

class ColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        toolbar.enableBackNav(View.OnClickListener { finish() })
        toolbar.title = "Color"

        val adapter = object : LegoAdapter() {
            init {
                register(ColorComponent())
            }
        }
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.itemAnimator = NoAlphaDefaultItemAnimator()
        rv_list.adapter = adapter
        adapter.commitData(getAllColor())

    }

    private fun getAllColor(): MutableList<Any> {
        val list = ArrayList<Any>()
        list.add(ColorComponent.Model("design_blk", getColorInt(R.color.design_blk)))
        list.add(ColorComponent.Model("design_blk_a70", getColorInt(R.color.design_blk_a70)))
        list.add(ColorComponent.Model("design_blk_a10", getColorInt(R.color.design_blk_a10)))
        list.add(ColorComponent.Model("design_blk_16", getColorInt(R.color.design_blk_16)))
        list.add(ColorComponent.Model("design_blk_51", getColorInt(R.color.design_blk_51)))
        list.add(ColorComponent.Model("design_blk_85", getColorInt(R.color.design_blk_85)))
        list.add(ColorComponent.Model("design_blk_93", getColorInt(R.color.design_blk_93)))
        list.add(ColorComponent.Model("design_blk_102", getColorInt(R.color.design_blk_102)))
        list.add(ColorComponent.Model("design_blk_121", getColorInt(R.color.design_blk_121)))
        list.add(ColorComponent.Model("design_blk_132", getColorInt(R.color.design_blk_132)))
        list.add(ColorComponent.Model("design_blk_136", getColorInt(R.color.design_blk_136)))
        list.add(ColorComponent.Model("design_blk_142", getColorInt(R.color.design_blk_142)))
        list.add(ColorComponent.Model("design_blk_159", getColorInt(R.color.design_blk_159)))
        list.add(ColorComponent.Model("design_blk_170", getColorInt(R.color.design_blk_170)))
        list.add(ColorComponent.Model("design_blk_193", getColorInt(R.color.design_blk_193)))
        list.add(ColorComponent.Model("design_blk_194", getColorInt(R.color.design_blk_194)))
        list.add(ColorComponent.Model("design_blk_221", getColorInt(R.color.design_blk_221)))
        list.add(ColorComponent.Model("design_blk_232", getColorInt(R.color.design_blk_232)))
        list.add(ColorComponent.Model("design_blk_240", getColorInt(R.color.design_blk_240)))
        list.add(ColorComponent.Model("design_blk_242", getColorInt(R.color.design_blk_242)))
        list.add(ColorComponent.Model("design_blk_244", getColorInt(R.color.design_blk_244)))
        list.add(ColorComponent.Model("design_blk_249", getColorInt(R.color.design_blk_249)))
        list.add(ColorComponent.Model("design_blk_255", getColorInt(R.color.design_blk_255)))
        list.add(ColorComponent.Model("design_blu_95", getColorInt(R.color.design_blu_95)))
        list.add(ColorComponent.Model("design_blu_97", getColorInt(R.color.design_blu_97)))
        list.add(ColorComponent.Model("design_blu_99", getColorInt(R.color.design_blu_99)))
        list.add(ColorComponent.Model("design_blu_117", getColorInt(R.color.design_blu_117)))
        list.add(ColorComponent.Model("design_blu_118", getColorInt(R.color.design_blu_118)))
        list.add(ColorComponent.Model("design_blu_119", getColorInt(R.color.design_blu_119)))
        list.add(ColorComponent.Model("design_blu_123", getColorInt(R.color.design_blu_123)))
        list.add(ColorComponent.Model("design_blu_124", getColorInt(R.color.design_blu_124)))
        list.add(ColorComponent.Model("design_blu_128", getColorInt(R.color.design_blu_128)))
        list.add(ColorComponent.Model("design_blu_138", getColorInt(R.color.design_blu_138)))
        list.add(ColorComponent.Model("design_blu_141", getColorInt(R.color.design_blu_141)))
        list.add(ColorComponent.Model("design_blu_164", getColorInt(R.color.design_blu_164)))
        list.add(ColorComponent.Model("design_org_137", getColorInt(R.color.design_org_137)))
        list.add(ColorComponent.Model("design_org_139", getColorInt(R.color.design_org_139)))
        list.add(ColorComponent.Model("design_org_147", getColorInt(R.color.design_org_147)))
        list.add(ColorComponent.Model("design_org_153", getColorInt(R.color.design_org_153)))
        list.add(ColorComponent.Model("design_org_161", getColorInt(R.color.design_org_161)))
        list.add(ColorComponent.Model("design_ylw_176", getColorInt(R.color.design_ylw_176)))
        list.add(ColorComponent.Model("design_ylw_180", getColorInt(R.color.design_ylw_180)))
        list.add(ColorComponent.Model("design_ylw_184", getColorInt(R.color.design_ylw_184)))
        list.add(ColorComponent.Model("design_ylw_236", getColorInt(R.color.design_ylw_236)))
        list.add(ColorComponent.Model("design_gld_179", getColorInt(R.color.design_gld_179)))
        list.add(ColorComponent.Model("design_gld_183", getColorInt(R.color.design_gld_183)))
        list.add(ColorComponent.Model("design_gld_187", getColorInt(R.color.design_gld_187)))
        list.add(ColorComponent.Model("design_gld_206", getColorInt(R.color.design_gld_206)))
        list.add(ColorComponent.Model("design_red_101", getColorInt(R.color.design_red_101)))
        list.add(ColorComponent.Model("design_red_106", getColorInt(R.color.design_red_106)))
        list.add(ColorComponent.Model("design_red_128", getColorInt(R.color.design_red_128)))
        list.add(ColorComponent.Model("design_grn_106", getColorInt(R.color.design_grn_106)))
        list.add(ColorComponent.Model("design_grn_108", getColorInt(R.color.design_grn_108)))
        list.add(ColorComponent.Model("design_grn_115", getColorInt(R.color.design_grn_115)))
        list.add(ColorComponent.Model("design_grn_136", getColorInt(R.color.design_grn_136)))
        return list
    }

}