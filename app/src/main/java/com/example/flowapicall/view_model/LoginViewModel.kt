package com.example.flowapicall.view_model

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flowapicall.*
import com.example.flowapicall.dto.LoginRequest
import com.example.flowapicall.dto.Response
import com.example.flowapicall.error_utils.CHECK_YOUR_FIELDS
import com.example.flowapicall.error_utils.PASS_WORD_ERROR
import com.example.flowapicall.error_utils.USER_NAME_ERROR
import com.example.flowapicall.flow_utils.State
import com.example.flowapicall.repository.Repository
import com.example.flowapicall.utils.RegexUtils.isEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor (private val dataRepository: Repository) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val loginLiveDataPrivate = MutableLiveData<State<Response>>()
    val loginLiveData: LiveData<State<Response>> get() = loginLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<Any>()
    val showToast: LiveData<Any> get() = showToastPrivate


    fun doLogin(userName: String, passWord: String) {
        val isUsernameValid = !isEmpty(userName)
        val isPassWordValid = passWord.trim().length > 4
        if (isUsernameValid && !isPassWordValid) {
            loginLiveDataPrivate.value = State.DataError(PASS_WORD_ERROR)
        } else if (!isUsernameValid && isPassWordValid) {
            loginLiveDataPrivate.value = State.DataError(USER_NAME_ERROR)
        } else if (!isUsernameValid && !isPassWordValid) {
            loginLiveDataPrivate.value = State.DataError(CHECK_YOUR_FIELDS)
        } else {
            viewModelScope.launch {
                loginLiveDataPrivate.value = State.Loading()
                    dataRepository.doLogin(loginRequest = LoginRequest(userName, passWord)).collect {
                        loginLiveDataPrivate.value = it
                    }
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = error.description
    }

}
