package com.xueqiu.design.share

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smilehacker.lego.util.NoAlphaDefaultItemAnimator
import com.xueqiu.design.R
import com.xueqiu.design.button.SnowButton
import com.xueqiu.design.dialog.SnowBottomSheet
import com.xueqiu.design.inflate

class SnowShareKit(context: Context, private val shareItems: MutableList<ShareItem>, private val extraView: View? = null) : SnowBottomSheet(context) {

    var shareKitCallback: ShareAdapter.Callback? = null

    private var mRvShare: RecyclerView? = null
    private var mBtnCancel: SnowButton? = null
    private var mExtraContainer: FrameLayout? = null

    private val mAdapter by lazy { ShareAdapter() }

    override fun getStuffView(): View {
        val view = context.inflate(R.layout.design_view_share_kit)
        mRvShare = view.findViewById(R.id.rv_share)
        mBtnCancel = view.findViewById(R.id.btn_cancel)
        mExtraContainer = view.findViewById(R.id.layout_extra_container)
        extraView?.let {
            mExtraContainer?.addView(it)
        }
        return view
    }

    override fun onViewAttached() {
        mRvShare?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mRvShare?.itemAnimator = NoAlphaDefaultItemAnimator()
        mRvShare?.adapter = mAdapter

        mBtnCancel?.setOnClickListener {
            dismiss()
        }
        mAdapter.callback = shareKitCallback
        val models = ArrayList<Any>()
        shareItems.forEach {
            models.add(ShareItemComponent.Model(it.type, it.name, it.imageRes))
        }
        mAdapter.commitData(models)
    }

}