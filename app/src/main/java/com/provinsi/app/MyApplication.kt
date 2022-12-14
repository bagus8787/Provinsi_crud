package com.provinsi.app

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.provinsi.app.helper.GlideImageLoadingService
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import ss.com.bannerslider.Slider

class MyApplication : MultiDexApplication() {

    companion object {
        var last_opened_tab = 0

        private var instance: MyApplication? = null

        fun getInstance(): MyApplication? {
            return instance
        }

        fun getContext(): Context? {
            return instance
        }
    }

    override fun onCreate() {
        instance = this

        super.onCreate()
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
        Slider.init(GlideImageLoadingService(this))
    }

}