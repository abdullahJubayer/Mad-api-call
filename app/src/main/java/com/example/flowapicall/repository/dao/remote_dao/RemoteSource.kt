package com.example.flowapicall.repository.dao.remote_dao

import com.example.flowapicall.dto.LoginRequest
import com.example.flowapicall.dto.Response
import com.example.flowapicall.flow_utils.State

internal interface RemoteSource {
    suspend fun doLogin(loginRequest: LoginRequest): State<Response>
}