package com.example.flowapicall.repository.dao.local_dao

import com.example.flowapicall.dto.Data
import com.example.flowapicall.dto.LoginRequest
import com.example.flowapicall.error_utils.PASS_WORD_ERROR
import com.example.flowapicall.dto.Response
import com.example.flowapicall.flow_utils.State
import javax.inject.Inject

class LocalDataSource @Inject constructor(){
    fun doLogin(loginRequest: LoginRequest): State<Response> {
        if (loginRequest == LoginRequest("01515296927", "123456")) {
            return State.Success(
                Response(
                    true,"Success", Data("token","brearer","",true,"01777")
                )
            )
        }
        return State.DataError(PASS_WORD_ERROR)
    }
}