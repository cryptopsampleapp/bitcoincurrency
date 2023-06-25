package com.aldi.coin.data.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class DecoratedCrypto(
    @DrawableRes val icon: Int,
    @ColorRes val changePercentType: Color,
    val id: String,
    val name: String,
    val symbol: String,
    val supply: String,
    val price: String,
    val changePerc: String,
    val marketCap: String,
    val volume: String,
)