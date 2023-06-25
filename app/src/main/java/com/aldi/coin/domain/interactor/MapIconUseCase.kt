package com.aldi.coin.domain.interactor

import com.aldi.coin.data.enum.KnownCryptoCurrency
import com.aldi.coin.data.model.Crypto

object MapIconUseCase {
    operator fun invoke(crypto: Crypto): Int {
        return KnownCryptoCurrency.values().firstOrNull { knownCryptoCurrency ->
            knownCryptoCurrency.name.equals(crypto.symbol, ignoreCase = true)
        }?.icon ?: KnownCryptoCurrency.PLACE_HOLDER.icon
    }
}