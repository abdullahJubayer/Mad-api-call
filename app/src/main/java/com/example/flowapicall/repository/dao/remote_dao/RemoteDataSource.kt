package com.example.flowapicall.repository.dao.remote_dao

import com.example.flowapicall.dto.LoginRequest
import com.example.flowapicall.dto.Response
import com.example.flowapicall.error_utils.NETWORK_ERROR
import com.example.flowapicall.flow_utils.State
import com.example.flowapicall.retrofit.Api
import com.example.flowapicall.retrofit.ApiGenerator
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val serviceGenerator: ApiGenerator) :
    RemoteSource {
    override suspend fun doLogin(loginRequest: LoginRequest): State<Response> {
        val recipesService = serviceGenerator.createService(Api::class.java)
        val map=HashMap<String,String>().apply {
            put("phone",loginRequest.phone)
            put("password",loginRequest.password)
        }
        return when (val response = processCall(map, recipesService::doLogin)) {
            is Response -> State.Success(data = response as Response)
            else -> State.DataError(errorCode = response as Int)
        }
    }

    private suspend fun <T> processCall(
        loginRequest: T,
        responseCall: suspend (loginRequest: T) -> retrofit2.Response<*>
    ): Any? {
        return try {
            val response = responseCall.invoke(loginRequest)
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

}