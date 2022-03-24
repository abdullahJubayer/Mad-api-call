package com.example.flowapicall.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(val phone: String, val password: String)

data class Response(
    @SerializedName("Success")
    var success: Boolean?,

    @SerializedName("message")
    var message: String?,

    @SerializedName("data")
    var data: Data?
)

data class Data(
    @SerializedName("access_token")
    var accessToken: String?,

    @SerializedName("token_type")
    var tokenType: String?,

    @SerializedName("remember_me_expires_at")
    private var rememberMeExpiresAt: String?,


    @SerializedName("verified")
    var verified: Boolean?,

    @SerializedName("phone")
    var phone: String?
)