package com.provinsi.app.model.request

data class BannerRequest (
    val file: String,

    // tambahi ini semua
    val SECRET: String,
    val imei: String,
    val device_id: String,
)