package com.example.flowapicall.flow_utils

sealed class State<T>(
    val data: T? = null,
    val errorCode: Int? = null
) {
    class Success<T>(data: T) : State<T>(data)
    class Loading<T>(data: T? = null) : State<T>(data)
    class DataError<T>(errorCode: Int) : State<T>(null, errorCode)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorCode]"
            is Loading<T> -> "Loading"
        }
    }
}