package com.aldi.coin.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(
        private val exception: Throwable,
        val localizedMessage: String = exception.localizedMessage ?: exception.toString()
    ) : ApiResponse<Nothing>()

    object Loading : ApiResponse<Nothing>()
}


suspend inline fun <T> safeApiCall(
    context: CoroutineContext,
    crossinline body: suspend () -> T,
): Flow<ApiResponse<T>> = flow {
    emit(ApiResponse.Loading)
    try {
        val result = withContext(context) { body() }
        emit(ApiResponse.Success(result))
    } catch (ex: Exception) {
        emit(ApiResponse.Error(ex))
    }
}
