package com.xueqiu.design.app

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smilehacker.lego.LegoComponent
import com.smilehacker.lego.annotation.LegoIndex
import com.xueqiu.design.inflate

class TextComponent : LegoComponent<TextComponent.ViewHolder, TextComponent.Model>() {

    override fun onBindData(viewHolder: ViewHolder, model: Model) {
        viewHolder.text.text = model.content
    }

    override fun getViewHolder(container: ViewGroup): ViewHolder {
        return ViewHolder(container.context.inflate(R.layout.component_text, container, false))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.text)
    }

    data class Model(
            @LegoIndex
            @JvmField
            var content: String? = null
    )


}