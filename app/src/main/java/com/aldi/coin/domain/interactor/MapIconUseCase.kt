package com.aldi.coin.domain.interactor

import androidx.annotation.DrawableRes
import com.aldi.coin.R
import com.aldi.coin.data.model.Crypto

object MapIconUseCase {

    enum class KnownCryptoCurrency(@DrawableRes val icon: Int) {
        ADA(R.drawable.ic_ada),
        AVAX(R.drawable.ic_avax),
        BNB(R.drawable.ic_bnb),
        BTC(R.drawable.ic_btc),
        ETH(R.drawable.ic_eth),
        MATIC(R.drawable.ic_matic),
        USDT(R.drawable.ic_usdt),
        XRP(R.drawable.ic_xrp),
        PLACE_HOLDER(R.drawable.ic_coin)
    }

    operator fun invoke(crypto: Crypto): Int {
        return KnownCryptoCurrency.values().firstOrNull { knownCryptoCurrency ->
            knownCryptoCurrency.name.equals(crypto.id, ignoreCase = true)
        }?.icon ?: KnownCryptoCurrency.PLACE_HOLDER.icon
    }
}