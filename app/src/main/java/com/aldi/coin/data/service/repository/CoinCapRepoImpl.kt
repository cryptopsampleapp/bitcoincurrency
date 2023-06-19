package com.aldi.coin.data.service.repository

import com.aldi.coin.data.service.CoinCapApi
import com.aldi.coin.data.service.request.CryptoListRequest
import javax.inject.Inject

class CoinCapRepoImpl @Inject constructor(private val api: CoinCapApi) : CoinCapRepository {
    override suspend fun getCryptos() =
        api.getCryptoCurrencyList(CryptoListRequest(10))


    override suspend fun getCryptoById(cryptoId: String) =
        api.getCryptoById(cryptoId)
}