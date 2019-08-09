package com.xueqiu.design.share

import android.content.*
import android.net.Uri
import android.text.TextUtils
import androidx.annotation.StringDef
import com.xueqiu.design.R


class ShareUtils {

    companion object {
        const val TYPE_SHARE_WECHAT = "wechat"
        const val TYPE_SHARE_WECHAT_MINI = "wechat_mini"
        const val TYPE_SHARE_MOMENTS = "moments"
        const val TYPE_SHARE_WEIBO = "weibo"
        const val TYPE_SHARE_QQ = "qq"
        const val TYPE_SHARE_XUEQIU = "xueqiu"
        const val TYPE_SHARE_IM = "im"

        const val TYPE_SHARE_FACEBOOK = "facebook"
        const val TYPE_SHARE_WHATSAPP = "whatsapp"
        const val TYPE_SHARE_INS = "instagram"
        const val TYPE_SHARE_TWITTER = "twitter"
        const val TYPE_SHARE_MESSENGER = "messenger"

        const val TYPE_SHARE_MESSAGE = "message"
        const val TYPE_SHARE_EMAIL = "email"
        const val TYPE_SHARE_COPY_LINK = "copy_link"
        const val TYPE_SHARE_MORE = "more"

        @JvmStatic
        fun getDesignLogoResFromType(type: String): Int {
            return when (type) {
                TYPE_SHARE_WECHAT -> R.drawable.design_icon_share_wechat
                TYPE_SHARE_WECHAT_MINI -> R.drawable.design_icon_share_wechat
                TYPE_SHARE_MOMENTS -> R.drawable.design_icon_share_moments
                TYPE_SHARE_WEIBO -> R.drawable.design_icon_share_weibo
                TYPE_SHARE_QQ -> R.drawable.design_icon_share_qq
                TYPE_SHARE_XUEQIU -> R.drawable.design_icon_share_xueqiu
                TYPE_SHARE_IM -> R.drawable.design_icon_share_conversation

                TYPE_SHARE_FACEBOOK -> R.drawable.design_icon_share_facebook
                TYPE_SHARE_WHATSAPP -> R.drawable.design_icon_share_whatsapp
                TYPE_SHARE_INS -> R.drawable.design_icon_share_instagram
                TYPE_SHARE_TWITTER -> R.drawable.design_icon_share_twitter
                TYPE_SHARE_MESSENGER -> R.drawable.design_icon_share_messenger

                TYPE_SHARE_MESSAGE -> R.drawable.design_icon_share_message
                TYPE_SHARE_EMAIL -> R.drawable.design_icon_share_mail
                TYPE_SHARE_COPY_LINK -> R.drawable.design_icon_share_copy_link
                TYPE_SHARE_MORE -> R.drawable.design_icon_share_more

                else -> 0
            }
        }

        @JvmStatic
        fun shareToWeChat(context: Context, content: String? = null, imageUri: Uri? = null): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.component = ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI")
            intent.type = "text/plain"
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            return try {
                context.startActivity(intent)
                true
            } catch (e: Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToMoments(context: Context, content: String? = null, imageUri: Uri): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.component = ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI")
            intent.action = "android.intent.action.SEND"
            intent.type = "image/*"
            content?.let {
                intent.putExtra("Kdescription", it)
            }
            intent.putExtra(Intent.EXTRA_STREAM, imageUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            return try {
                context.startActivity(intent)
                true
            } catch (e: Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToWeibo(
                context: Context,
                title: String? = null,
                content: String? = null,
                imageUri: Uri? = null
        ): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.component = ComponentName("com.sina.weibo", "com.sina.weibo.ComposerDispatchActivity")
            title?.let {
                intent.putExtra(Intent.EXTRA_SUBJECT, title)
            }
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            return try {
                context.startActivity(intent)
                true
            } catch (e: Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToQQ(context: Context, content: String? = null, imageUri: Uri? = null): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.component = ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity")
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            return try {
                context.startActivity(intent)
                true
            } catch (e: Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToTIM(context: Context, content: String? = null, imageUri: Uri? = null): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.component = ComponentName("com.tencent.tim", "com.tencent.mobileqq.activity.JumpActivity")
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            return try {
                context.startActivity(intent)
                true
            } catch (e: Exception) {
                false
            }
        }

        fun shareToXueqiu(context: Context, content: String? = null, imageUri: Uri? = null): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.component =
                    ComponentName("com.xueqiu.android", "com.xueqiu.android.community.PostStatusActivity")
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            return try {
                context.startActivity(intent)
                true
            } catch (e: Exception) {
                false
            }
        }

        fun shareToXueqiuIM(context: Context, content: String? = null, imageUri: Uri? = null): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.component = ComponentName("com.xueqiu.android", "com.xueqiu.android.message.SelectTalkActivity")
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            return try {
                context.startActivity(intent)
                true
            } catch (e: Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToWhatsApp(context: Context, content: String? = null, imageUri: Uri? = null): Boolean {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "text/plain"
            intent.`package` = "com.whatsapp"
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            return try {
                context.startActivity(intent)
                true
            } catch (e: java.lang.Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToMessenger(context: Context, content: String? = null, imageUri: Uri? = null): Boolean {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "text/plain"
            intent.`package` = "com.facebook.orca"
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            return try {
                context.startActivity(intent)
                true
            } catch (e: java.lang.Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToEmail(
                context: Context,
                title: String? = null,
                content: String? = null,
                imageUri: Uri? = null
        ): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            title?.let {
                intent.putExtra(Intent.EXTRA_SUBJECT, title)
            }
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            val shareIntent = Intent.createChooser(intent, null)
            return try {
                context.startActivity(shareIntent)
                true
            } catch (e: java.lang.Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToSMS(context: Context, content: String? = null): Boolean {
            if (TextUtils.isEmpty(content)) {
                return false
            }
            val uri = Uri.parse("smsto:")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", content)
            return try {
                context.startActivity(intent)
                true
            } catch (e: java.lang.Exception) {
                false
            }
        }

        @JvmStatic
        fun shareToOthers(
                context: Context,
                title: String? = null,
                content: String? = null,
                imageUri: Uri? = null
        ): Boolean {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            title?.let {
                intent.putExtra(Intent.EXTRA_SUBJECT, title)
            }
            content?.let {
                intent.putExtra(Intent.EXTRA_TEXT, it)
            }
            imageUri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
                intent.type = "image/*"
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            val shareIntent = Intent.createChooser(intent, null)
            return try {
                context.startActivity(shareIntent)
                true
            } catch (e: Exception) {
                false
            }
        }

        @JvmStatic
        fun copyLink(context: Context, content: String? = null): Boolean {
            return try {
                val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, content))
                true
            } catch (e: Exception) {
                false
            }
        }

    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(
            TYPE_SHARE_WECHAT,
            TYPE_SHARE_MOMENTS,
            TYPE_SHARE_WEIBO,
            TYPE_SHARE_QQ,
            TYPE_SHARE_XUEQIU,
            TYPE_SHARE_IM,
            TYPE_SHARE_FACEBOOK,
            TYPE_SHARE_WHATSAPP,
            TYPE_SHARE_INS,
            TYPE_SHARE_TWITTER,
            TYPE_SHARE_MESSENGER,
            TYPE_SHARE_MESSAGE,
            TYPE_SHARE_EMAIL,
            TYPE_SHARE_COPY_LINK,
            TYPE_SHARE_MORE
    )
    annotation class ShareType
}