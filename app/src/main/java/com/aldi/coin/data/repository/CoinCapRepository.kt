package com.aldi.coin.data.repository

import com.aldi.coin.data.model.Crypto

interface CoinCapRepository {
    suspend fun getCryptoList(size : Int) : List<Crypto>?
    suspend fun getCryptoById(cryptoId : String) : Crypto?
}