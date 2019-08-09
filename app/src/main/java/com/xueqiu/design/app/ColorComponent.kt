package com.xueqiu.design.app

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smilehacker.lego.LegoComponent
import com.smilehacker.lego.annotation.LegoField
import com.xueqiu.design.inflate

class ColorComponent : LegoComponent<ColorComponent.ViewHolder, ColorComponent.Model>() {

    override fun onBindData(viewHolder: ViewHolder, model: Model) {
        viewHolder.tvName.text = model.name
        model.color?.let {
            viewHolder.viewColor.setBackgroundColor(it)
        }
    }

    override fun getViewHolder(container: ViewGroup): ViewHolder {
        return ViewHolder(container.context.inflate(R.layout.component_color, container, false))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val viewColor = itemView.findViewById<View>(R.id.view_color)
    }

    data class Model(
        @LegoField @JvmField var name: String? = null,
        @LegoField @JvmField var color: Int? = null
    )

}