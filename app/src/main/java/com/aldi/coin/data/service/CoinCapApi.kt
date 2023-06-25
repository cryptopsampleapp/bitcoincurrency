package com.aldi.coin.data.service

import com.aldi.coin.data.model.CryptoDTO
import com.aldi.coin.data.service.response.BaseResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinCapApi {

    @GET("/v2/assets")
    suspend fun getCryptoCurrencyList(@Query("limit") limit : Int): BaseResponse<List<CryptoDTO>>

    @GET("/v2/assets/{id}")
    suspend fun getCryptoById(@Path("id") cryptoId: String): BaseResponse<CryptoDTO>

    object Factory {
        fun createService(retrofit: Retrofit): CoinCapApi {
            return retrofit.create(CoinCapApi::class.java)
        }
    }
}