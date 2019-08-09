package com.xueqiu.design.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smilehacker.lego.LegoAdapter
import com.smilehacker.lego.util.NoAlphaDefaultItemAnimator
import com.xueqiu.design.getAttrColor
import kotlinx.android.synthetic.main.activity_theme.*
import kotlinx.android.synthetic.main.common_toolbar.*

class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)

        toolbar.enableBackNav(View.OnClickListener { finish() })
        toolbar.title = "Theme"

        val adapter = object : LegoAdapter() {
            init {
                register(ColorComponent())
                register(FontComponent())
            }
        }
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.itemAnimator = NoAlphaDefaultItemAnimator()
        rv_list.adapter = adapter

        adapter.commitData(getAllAttr())
    }

    private fun getAllAttr(): MutableList<Any> {
        val list = ArrayList<Any>()
        list.add(ColorComponent.Model("colorPrimary", getAttrColor(R.attr.colorPrimary)))
        list.add(ColorComponent.Model("colorPrimaryDark", getAttrColor(R.attr.colorPrimaryDark)))
        list.add(ColorComponent.Model("colorAccent", getAttrColor(R.attr.colorAccent)))
        list.add(
            ColorComponent.Model(
                "windowBackground",
                getAttrColor(android.R.attr.windowBackground)
            )
        )
        list.add(
            ColorComponent.Model(
                "designWindowBackgroundColor",
                getAttrColor(R.attr.designWindowBackgroundColor)
            )
        )
        list.add(
            ColorComponent.Model(
                "designCardBackgroundColor",
                getAttrColor(R.attr.designCardBackgroundColor)
            )
        )
        list.add(FontComponent.Model("designTextTitleSizePrimary", "h2"))
        list.add(FontComponent.Model("designTextTitleSizeSecondary", "h3"))
        list.add(FontComponent.Model("designTextTitleSizeTertiary", "h4"))
        list.add(
            ColorComponent.Model(
                "designTextTitleColorPrimary",
                getAttrColor(R.attr.designTextTitleColorPrimary)
            )
        )
        list.add(FontComponent.Model("designTextTipSizePrimary", "p4"))
        list.add(FontComponent.Model("designTextTipSizeSecondary", "p7"))
        list.add(
            ColorComponent.Model(
                "designTextTipColorPrimary",
                getAttrColor(R.attr.designTextTipColorPrimary)
            )
        )
        list.add(FontComponent.Model("designTextHintSizePrimary", "p4"))
        list.add(
            ColorComponent.Model(
                "designTextHintColorPrimary",
                getAttrColor(R.attr.designTextHintColorPrimary)
            )
        )
        list.add(FontComponent.Model("designTextErrorSizePrimary", "p7"))
        list.add(
            ColorComponent.Model(
                "designTextErrorColorPrimary",
                getAttrColor(R.attr.designTextErrorColorPrimary)
            )
        )
        list.add(FontComponent.Model("designTextContentSizePrimary", "p2"))
        list.add(FontComponent.Model("designTextContentSizeSecondary", "p3"))
        list.add(FontComponent.Model("designTextContentSizeTertiary", "p4"))
        list.add(
            ColorComponent.Model(
                "designTextContentColorPrimary",
                getAttrColor(R.attr.designTextContentColorPrimary)
            )
        )
        list.add(FontComponent.Model("designTextToastSize", "p4"))
        list.add(
            ColorComponent.Model(
                "designTextToastColor",
                getAttrColor(R.attr.designTextToastColor)
            )
        )
        list.add(
            ColorComponent.Model(
                "designBackgroundToast",
                getAttrColor(R.attr.designBackgroundToast)
            )
        )
        list.add(ColorComponent.Model("designDividerColor", getAttrColor(R.attr.designDividerColor)))
        list.add(FontComponent.Model("designPageStartEndPadding", "16dp"))
        list.add(
            ColorComponent.Model(
                "designColorPositive",
                getAttrColor(R.attr.designColorPositive)
            )
        )
        list.add(
            ColorComponent.Model(
                "designColorNegative",
                getAttrColor(R.attr.designColorNegative)
            )
        )
        list.add(ColorComponent.Model("designColorWarning", getAttrColor(R.attr.designColorWarning)))
        return list
    }

}