package com.example.flowapicall.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.example.flowapicall.BaseActivity
import com.example.flowapicall.R
import com.example.flowapicall.dto.Response
import com.example.flowapicall.flow_utils.State
import com.example.flowapicall.utils.observe
import com.example.flowapicall.view_model.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.task.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener {
            loginViewModel.doLogin("01515296927","123456")
        }
    }

    override fun observeViewModel() {
        observe(loginViewModel.loginLiveData, ::handleLoginResult)
        observeSnackBarMessages(loginViewModel.showToast)
    }

    private fun observeSnackBarMessages(event: LiveData<Any>) {
        event.observe(this) {
            root.showSnackbar(it as String, Snackbar.LENGTH_LONG)
        }
    }

    private fun handleLoginResult(status: State<Response>) {
        when (status) {
            is State.Loading -> txt.text="Loading..."
            is State.Success -> status.data?.let {
                txt.text="Login Success"
            }
            is State.DataError -> {
                status.errorCode?.let {
                    txt.text="Login Error : $it"
                    loginViewModel.showToastMessage(it)
                }
            }
        }
    }

}