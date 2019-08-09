package com.xueqiu.design.share

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShareItem(
        @JvmField @ShareUtils.ShareType var type: String,
        @JvmField var name: String,
        @JvmField var imageRes: Int = ShareUtils.getDesignLogoResFromType(type)
) : Parcelable