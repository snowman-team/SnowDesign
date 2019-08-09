package com.xueqiu.design.share

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smilehacker.lego.LegoComponent
import com.smilehacker.lego.annotation.LegoField
import com.smilehacker.lego.annotation.LegoIndex
import com.xueqiu.design.R
import com.xueqiu.design.inflate
import com.xueqiu.design.setSelectRipperEffect

class ShareItemComponent : LegoComponent<ShareItemComponent.ViewHolder, ShareItemComponent.Model>() {

    var listener: ShareItemListener? = null

    override fun onBindData(viewHolder: ViewHolder, model: Model) {
        viewHolder.ivImage.setImageResource(model.iconRes)
        viewHolder.tvName.text = model.name
        viewHolder.itemView.setOnClickListener {
            listener?.onShareItemClick(model.type)
        }
    }

    override fun getViewHolder(container: ViewGroup): ViewHolder {
        return ViewHolder(container.context.inflate(R.layout.design_component_share, container, false))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.iv_image)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)

        init {
            itemView.setSelectRipperEffect()
        }
    }

    data class Model(@LegoIndex @JvmField var type: String,
                     @LegoField @JvmField var name: String,
                     @LegoField @JvmField var iconRes: Int)

    interface ShareItemListener {
        fun onShareItemClick(type: String)
    }

}