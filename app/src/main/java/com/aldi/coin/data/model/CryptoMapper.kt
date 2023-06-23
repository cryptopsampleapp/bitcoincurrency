package com.aldi.coin.data.model

import com.aldi.coin.data.service.response.CryptoDTO

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