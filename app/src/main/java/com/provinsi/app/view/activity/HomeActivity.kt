package com.provinsi.app.view.activity

import android.content.Intent
import android.os.Bundle
import com.provinsi.app.adapter.ProvinsiAdapter
import com.provinsi.app.databinding.ActivityHomeBinding
import com.provinsi.app.helper.viewBinding
import com.provinsi.app.model.response.ProvinsiResponseItem
import com.provinsi.app.view.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeActivity : BaseActivity(), ProvinsiAdapter.CellClickListener {
    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getProvinsi()
        setListener()
    }

    private fun setListener() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddProvinsiActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getProvinsi(){
        disposable?.add(
            apiInterface.getProvinsi()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.isSuccessful) {
                            val data = it.body()

                            if(data!!.isNotEmpty()){
                                binding.rvProvinsi.adapter = ProvinsiAdapter(this, data, this)

                                binding.countProvinsi.text = data.size.toString()
                            }
                        }
                    },
                    {
                        toast("Gagal memproses data, terjadi gangguan server")
                    })
        )
    }

    override fun onBackPressed() {
//        dialogLogout()
    }

    override fun selectProvinsi(data: ProvinsiResponseItem) {
        val intent = Intent(this, AddProvinsiActivity::class.java)
        intent.putExtra("dataProvinsi", data)
        startActivity(intent)
    }

}