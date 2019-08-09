package com.xueqiu.design.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.design.share.ShareAdapter
import com.xueqiu.design.share.ShareItem
import com.xueqiu.design.share.ShareUtils
import com.xueqiu.design.share.SnowShareKit
import kotlinx.android.synthetic.main.activity_share.*
import kotlinx.android.synthetic.main.common_toolbar.*

class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        toolbar.enableBackNav(View.OnClickListener {
            finish()
        })

        toolbar.title = "Share"

        edt_title.text = "这是标题"
        edt_content.text = "这是内容"

        val shareItems = ArrayList<ShareItem>()
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_WECHAT, getString(R.string.text_wechat)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_MOMENTS, getString(R.string.text_moments)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_WEIBO, getString(R.string.text_weibo)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_QQ, getString(R.string.text_qq)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_XUEQIU, getString(R.string.text_xueqiu)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_IM, getString(R.string.text_xueqiu_im)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_FACEBOOK, getString(R.string.text_facebook)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_WHATSAPP, getString(R.string.text_whatsapp)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_INS, getString(R.string.text_ins)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_TWITTER, getString(R.string.text_twitter)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_MESSENGER, getString(R.string.text_messenger)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_MESSAGE, getString(R.string.text_message)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_EMAIL, getString(R.string.text_email)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_COPY_LINK, getString(R.string.text_copy_link)))
        shareItems.add(ShareItem(ShareUtils.TYPE_SHARE_MORE, getString(R.string.text_more)))

        btn_share.setOnClickListener {
            val shareKit = SnowShareKit(this, shareItems)
            shareKit.shareKitCallback = object : ShareAdapter.Callback {
                override fun onShareItemClick(type: String) {
                    val title = edt_title.text
                    val content = edt_content.text
                    val result: Boolean = when (type) {
                        ShareUtils.TYPE_SHARE_WECHAT -> {
                            ShareUtils.shareToWeChat(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_MOMENTS -> {
                            Toast.makeText(this@ShareActivity, "朋友圈不能只分享文字", Toast.LENGTH_LONG).show()
                            true
                        }
                        ShareUtils.TYPE_SHARE_WEIBO -> {
                            ShareUtils.shareToWeibo(this@ShareActivity, title, content)
                        }
                        ShareUtils.TYPE_SHARE_QQ -> {
                            ShareUtils.shareToQQ(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_XUEQIU -> {
                            ShareUtils.shareToXueqiu(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_IM -> {
                            ShareUtils.shareToXueqiuIM(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_FACEBOOK -> {
                            Toast.makeText(this@ShareActivity, "暂未实现", Toast.LENGTH_LONG).show()
                            true
                        }
                        ShareUtils.TYPE_SHARE_WHATSAPP -> {
                            ShareUtils.shareToWhatsApp(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_INS -> {
                            ShareUtils.shareToXueqiuIM(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_TWITTER -> {
                            Toast.makeText(this@ShareActivity, "暂未实现", Toast.LENGTH_LONG).show()
                            true
                        }
                        ShareUtils.TYPE_SHARE_MESSENGER -> {
                            ShareUtils.shareToMessenger(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_MESSAGE -> {
                            ShareUtils.shareToSMS(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_EMAIL -> {
                            ShareUtils.shareToEmail(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_COPY_LINK -> {
                            ShareUtils.copyLink(this@ShareActivity, title + content)
                        }
                        ShareUtils.TYPE_SHARE_MORE -> {
                            ShareUtils.shareToOthers(this@ShareActivity, title + content)
                        }
                        else -> false
                    }
                }
            }
            shareKit.show()
        }
    }
}