package com.aldi.coin.data.repository

import com.aldi.coin.data.service.CoinCapApi
import com.aldi.coin.toCrypto
import javax.inject.Inject

class CoinCapRepoImpl @Inject constructor(private val api: CoinCapApi) : CoinCapRepository {
    override suspend fun getCryptoList(size : Int) =
        api.getCryptoCurrencyList(size).data?.map { dto -> dto.toCrypto() }


    override suspend fun getCryptoById(cryptoId: String) =
        api.getCryptoById(cryptoId).data?.toCrypto()
}