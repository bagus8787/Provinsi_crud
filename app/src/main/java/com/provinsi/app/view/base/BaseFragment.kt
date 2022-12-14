package com.provinsi.app.view.base

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.provinsi.app.api.ApiClient
import com.provinsi.app.api.ApiInterface
import com.provinsi.app.helper.DialogHelper
import com.provinsi.app.helper.Loading
import com.provinsi.app.helper.SessionManager
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment(fragment: Int) : Fragment(fragment) {

    lateinit var session: SessionManager
    lateinit var apiInterface: ApiInterface
    var disposable: CompositeDisposable? = null

    private var toast: Toast? = null
    lateinit var pLoading: Loading
    lateinit var dialogHelper: DialogHelper
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pLoading = Loading(requireContext())
        disposable = CompositeDisposable()
        session = SessionManager(requireContext())
        apiInterface = ApiClient.client.create(ApiInterface::class.java)
        dialogHelper = DialogHelper(requireContext(), apiInterface, session)

        progressBar = ProgressBar(requireContext())
    }

    fun toast(@StringRes message: Int) {
        toast(getString(message))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.clear()
    }

    fun toast(toastMessage: String?) {
        if (toastMessage != null && toastMessage.isNotEmpty()) {
            if (toast != null) toast!!.cancel()
            toast = Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG)
            toast!!.show()

        }
    }

}