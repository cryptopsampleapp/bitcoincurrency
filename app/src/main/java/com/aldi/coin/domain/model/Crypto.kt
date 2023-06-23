package com.aldi.coin.domain.model

import com.aldi.coin.data.service.response.CryptoDTO

data class Crypto(
    val id: String,
    val name: String,
    val symbol: String,
    val supply: String,
    val price: String,
    val changePerc: String,
    val marketCap : String,
    val volume: String,
)

fun CryptoDTO.toCrypto() =
    Crypto(
        id = id,
        name = name,
        symbol = symbol,
        supply = supply,
        price = priceUsd,
        changePerc = changePercent24Hr,
        marketCap = marketCapUsd,
        volume = volumeUsd24Hr
    )