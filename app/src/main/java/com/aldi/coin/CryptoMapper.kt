package com.aldi.coin

import com.aldi.coin.data.model.Crypto
import com.aldi.coin.data.model.CryptoDTO
import com.aldi.coin.data.model.DecoratedCrypto
import com.aldi.coin.domain.interactor.DecorateValueUseCase
import com.aldi.coin.domain.interactor.MapChangePercentColorUseCase
import com.aldi.coin.domain.interactor.MapIconUseCase

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

fun Crypto.toDecoratedCrypto() =
    DecoratedCrypto(
        icon = MapIconUseCase(this),
        changePercentType = MapChangePercentColorUseCase(this),
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        supply = DecorateValueUseCase(this.supply),
        price = DecorateValueUseCase(this.price),
        changePerc = DecorateValueUseCase(this.changePerc, isCurrency = false),
        marketCap = DecorateValueUseCase(this.marketCap),
        volume = DecorateValueUseCase(this.volume)
    )
