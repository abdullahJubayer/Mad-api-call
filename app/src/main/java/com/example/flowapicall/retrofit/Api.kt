package com.example.flowapicall.retrofit

import retrofit2.Response
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("yes-login")
    suspend fun doLogin(@FieldMap inputData:Map<String,String>): Response<com.example.flowapicall.dto.Response>
}