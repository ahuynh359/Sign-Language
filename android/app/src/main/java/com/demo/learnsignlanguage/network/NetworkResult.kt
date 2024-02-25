package com.demo.learnsignlanguage.network

sealed class NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>()
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Failure(val error: String) : NetworkResult<Nothing>()
}