package com.xueqiu.design.app

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smilehacker.lego.LegoComponent
import com.smilehacker.lego.annotation.LegoField
import com.xueqiu.design.inflate

class FontComponent : LegoComponent<FontComponent.ViewHolder, FontComponent.Model>() {

    override fun onBindData(viewHolder: ViewHolder, model: Model) {
        viewHolder.tvName.text = model.name
        viewHolder.tvSize.text = model.size
    }

    override fun getViewHolder(container: ViewGroup): ViewHolder {
        return ViewHolder(container.context.inflate(R.layout.component_font, container, false))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvSize = itemView.findViewById<TextView>(R.id.tv_size)
    }

    data class Model(
        @LegoField @JvmField var name: String? = null,
        @LegoField @JvmField var size: String? = null
    )

}