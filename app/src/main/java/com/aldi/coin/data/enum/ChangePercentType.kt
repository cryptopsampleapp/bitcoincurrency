package com.aldi.coin.data.enum

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import com.aldi.coin.ui.theme.CustomGreen
import com.aldi.coin.ui.theme.CustomRed

enum class ChangePercentType(@ColorRes val color: Color) {
    INCREASE(CustomGreen), DECREASE(CustomRed)
}