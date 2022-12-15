package com.provinsi.app.view.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.provinsi.app.databinding.ActivityAddProvinsiBinding
import com.provinsi.app.helper.viewBinding
import com.provinsi.app.model.request.EditProvinsiRequest
import com.provinsi.app.model.response.ProvinsiResponseItem
import com.provinsi.app.view.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddProvinsiActivity : BaseActivity() {
    private val binding by viewBinding(ActivityAddProvinsiBinding::inflate)

    private var dataProvinsi : ProvinsiResponseItem?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dataProvinsi = intent.getParcelableExtra("dataProvinsi")

        setVIew()
        setListener()
    }

    private fun setVIew(){
        if (dataProvinsi != null){
            title = "Edit provinsi"

            binding.btnAddProvinsi.text = "Edit provinsi"
            binding.btnDeleteProvinsi.visibility = View.VISIBLE

            binding.edtName.setText(dataProvinsi!!.propinsi_name)
        } else {
            title = "Tambah provinsi"

            binding.btnAddProvinsi.text = "Tambah provinsi"
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun setListener(){
        binding.btnAddProvinsi.setOnClickListener {
            val nameProvinsi = binding.edtName.text.toString()

            if (nameProvinsi.isEmpty()){
                toast("Nama provinsi harus diisi!")
                return@setOnClickListener
            }

            if (dataProvinsi != null) {
                apiInterface.editProvinsi(
                    dataProvinsi!!.propinsi_id.toString(),
                    EditProvinsiRequest(
                        nameProvinsi
                    )
                )
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                if (it.isSuccessful) {
                                    val data = it.body()

                                    toast(data?.messages?.success.toString())
                                }

                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                            },
                            {
                                toast("Gagal memproses data, terjadi gangguan server")
                            })

            } else {
                disposable?.add(
                    apiInterface.addProvinsi(
                        nameProvinsi
                    )
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                if (it.isSuccessful) {
                                    val data = it.body()

                                    toast(data?.messages?.success.toString())
                                }
                            },
                            {
                                toast("Gagal memproses data, terjadi gangguan server")
                            })
                )
            }

        }

        binding.btnDeleteProvinsi.setOnClickListener {
            AlertDialog.Builder(this)
//                .setTitle("Judul")
                .setMessage("Yakin ingin hapus provinsi ?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
                    disposable?.add(
                        apiInterface.deleteProvinsi(
                            dataProvinsi!!.propinsi_id.toString()
                        )
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                {
                                    if (it.isSuccessful) {
                                        val data = it.body()

                                        toast(data?.messages?.success.toString())
                                    }

                                    val intent = Intent(this, HomeActivity::class.java)
                                    startActivity(intent)
                                },
                                {
                                    toast("Gagal memproses data, terjadi gangguan server")
                                }
                            )
                    )

                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
//                    Toast.makeText(this, "Jalankan perintah ketika user memilih tombol No", Toast.LENGTH_LONG).show()
                })
                .show()
        }
    }
}