package com.xueqiu.design.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilehacker.lego.LegoAdapter
import com.smilehacker.lego.util.NoAlphaDefaultItemAnimator
import kotlinx.android.synthetic.main.activity_font.*
import kotlinx.android.synthetic.main.common_toolbar.*

class FontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font)

        toolbar.enableBackNav(View.OnClickListener { finish() })
        toolbar.title = "Font"


        val adapter = object : LegoAdapter() {
            init {
                register(FontComponent())
            }
        }
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.itemAnimator = NoAlphaDefaultItemAnimator()
        rv_list.adapter = adapter

        adapter.commitData(getAllFont())
    }

    private fun getAllFont(): MutableList<Any> {
        val list = ArrayList<Any>()
        list.add(FontComponent.Model("h2", "20sp"))
        list.add(FontComponent.Model("h3", "18sp"))
        list.add(FontComponent.Model("h4", "16sp"))
        list.add(FontComponent.Model("h5", "14sp"))
        list.add(FontComponent.Model("h6", "12sp"))
        list.add(FontComponent.Model("p2", "20sp"))
        list.add(FontComponent.Model("p3", "18sp"))
        list.add(FontComponent.Model("p4", "16sp"))
        list.add(FontComponent.Model("p5", "14sp"))
        list.add(FontComponent.Model("p6", "13sp"))
        list.add(FontComponent.Model("p7", "12sp"))
        return list
    }

}