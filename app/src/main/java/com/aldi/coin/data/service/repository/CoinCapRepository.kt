package com.aldi.coin.data.service.repository

import com.aldi.coin.data.service.response.CryptoDTO
import retrofit2.Response

interface CoinCapRepository {
    suspend fun getCryptos() : Response<List<CryptoDTO>>
    suspend fun getCryptoById(cryptoId : String) : Response<CryptoDTO>
}