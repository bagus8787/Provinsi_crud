package com.provinsi.app.helper

import android.content.Context
import com.provinsi.app.api.ApiInterface

class DialogHelper(
    private val ctx: Context,
    val apiInterface: ApiInterface,
    val session: SessionManager
) {

    private val TAG = "DialogHelper"

    interface ClickListener {
        fun addSubMember(idSiswa: String)
    }


}