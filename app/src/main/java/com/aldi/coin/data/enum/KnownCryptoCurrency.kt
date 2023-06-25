package com.aldi.coin.data.enum

import androidx.annotation.DrawableRes
import com.aldi.coin.R

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