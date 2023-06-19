package com.aldi.coin.data.service

import com.aldi.coin.data.service.request.CryptoListRequest
import com.aldi.coin.data.service.response.CryptoDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinCapApi {

    @GET("/v2/assets")
    suspend fun getCryptoCurrencyList(@Body request: CryptoListRequest) : Response<List<CryptoDTO>>

    @GET("v2/assets/{id}")
    suspend fun getCryptoById(@Path ("id") cryptoId : String) : Response<CryptoDTO>

    object Factory {
        fun createService(retrofit: Retrofit): CoinCapApi {
            return retrofit.create(CoinCapApi::class.java)
        }
    }
}