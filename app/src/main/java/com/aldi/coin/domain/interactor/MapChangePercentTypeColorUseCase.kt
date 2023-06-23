package com.aldi.coin.domain.interactor

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import com.aldi.coin.data.model.Crypto
import com.aldi.coin.ui.theme.CustomGreen
import com.aldi.coin.ui.theme.CustomRed

object MapChangePercentTypeColorUseCase {

    enum class ChangePercentType(@ColorRes val color: Color) {
        INCREASE(CustomGreen), DECREASE(CustomRed)
    }

    operator fun invoke(crypto: Crypto): Color {
        return if (crypto.changePerc.contains("-")) {
            ChangePercentType.DECREASE.color
        } else {
            ChangePercentType.INCREASE.color
        }
    }
}