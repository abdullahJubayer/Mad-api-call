package com.example.flowapicall.error_utils

interface ErrorMapperSource {
    fun getErrorString(errorId: Int): String
    val errorsMap: Map<Int, String>
}