package com.provinsi.app.view.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.provinsi.app.api.ApiClient
import com.provinsi.app.api.ApiInterface
import com.provinsi.app.helper.LogOutTimerUtil
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity(){

    private var toast: Toast? = null

    lateinit var apiInterface: ApiInterface
    var disposable: CompositeDisposable? = null
    var TAG = "BaseActivity"

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        disposable = CompositeDisposable()
        apiInterface = ApiClient.client.create(ApiInterface::class.java)
    }

    fun toast(@StringRes message: Int) {
        toast(getString(message))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.clear()
    }


    fun toast(toastMessage: String?) {
        if (toastMessage != null && !toastMessage.isEmpty()) {
            if (toast != null) toast!!.cancel()
            toast = Toast.makeText(this.applicationContext, toastMessage, Toast.LENGTH_LONG)
            toast!!.show()

        }
    }

    override fun onStart() {
        super.onStart()

    }

}
