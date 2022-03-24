package com.example.flowapicall.error_utils

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
