package com.fdj.injection.base.data

import okhttp3.ResponseBody
import retrofit2.Response

open class BaseRepository {

    @Suppress("UNCHECKED_CAST")
    protected suspend fun <T, R> request(
        call: suspend () -> Response<T>,
        transform: (T) -> R
    ): BaseResponse<R> {
        return try {
            val response = call()

            if (response.isSuccessful) {
                response.body()?.let {
                    BaseResponse.Success(transform(it))
                } ?: run {
                    BaseResponse.Success(transform(ResponseBody as T))
                }
            } else {
                BaseResponse.Error(Throwable())
            }
        } catch (e: Throwable) {
            BaseResponse.Error(e)
        }
    }
}