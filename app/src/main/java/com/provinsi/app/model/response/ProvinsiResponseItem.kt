package com.provinsi.app.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProvinsiResponseItem(
    val created_at: String?= "",
    val propinsi_id: String?= "",
    val propinsi_name: String?= "",
    val updated_at: String?= ""
): Parcelable