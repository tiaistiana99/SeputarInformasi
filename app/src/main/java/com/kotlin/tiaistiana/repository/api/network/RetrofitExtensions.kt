package com.kotlin.tiaistiana.repository.api.network

import retrofit2.Response
import retrofit2.Retrofit


inline fun <reified T> Retrofit.create(): T = create(T::class.java)

/**
 * Mengonversi Retrofit [Response] ke [Sumber Daya] yang menyediakan status
 * dan data ke UI.
 */

fun <ResultType> Response<ResultType>.toResource(): Resource<ResultType> {
    val error = errorBody()?.toString() ?: message()
    return when {
        isSuccessful -> {
            val body = body()
            when {
                body != null -> Resource.success(body, this.code())
                else -> Resource.error(error, this.code())
            }
        }
        else -> Resource.error(error, this.code())
    }
}