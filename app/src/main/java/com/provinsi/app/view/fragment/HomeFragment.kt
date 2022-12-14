package com.provinsi.app.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.provinsi.app.R
import com.provinsi.app.databinding.FragmentHomeBinding
import com.provinsi.app.helper.GlobalVar
import com.provinsi.app.helper.viewBinding
import com.provinsi.app.model.request.BannerRequest
import com.provinsi.app.view.base.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       
    }

    private fun initSlider() {
        disposable?.add(
            apiInterface.banner(
                BannerRequest(
                    "40_list_negara",
                    GlobalVar.API_SECRET,
                    "MyApplication.imei",
                    "MyApplication.device_id"
                )
            )
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.isSuccessful) {
                            
                        }
                    },
                    {
                        //toast(it.stackTraceToString())
                        Log.d("sliderr", "initSlider Off: " + it.stackTraceToString())
                        toast("Gagal memproses data, terjadi gangguan server")
                    })
        )
    }

}