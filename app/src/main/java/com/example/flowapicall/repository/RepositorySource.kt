package com.example.flowapicall.repository

import com.example.flowapicall.dto.LoginRequest
import com.example.flowapicall.dto.Response
import com.example.flowapicall.flow_utils.State
import kotlinx.coroutines.flow.Flow

interface RepositorySource {
    suspend fun doLogin(loginRequest: LoginRequest): Flow<State<Response>>
}