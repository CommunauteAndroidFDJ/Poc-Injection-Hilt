package com.fdj.injection.base.data

sealed class BaseResponse<T> {
    class Success<T>(val obj: T) : BaseResponse<T>()
    class Error<T>(val serverException: Throwable) : BaseResponse<T>()
}