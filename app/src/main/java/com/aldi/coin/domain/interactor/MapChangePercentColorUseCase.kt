package com.aldi.coin.domain.interactor

import androidx.compose.ui.graphics.Color
import com.aldi.coin.data.enum.ChangePercentType
import com.aldi.coin.data.model.Crypto


object MapChangePercentColorUseCase {
    operator fun invoke(crypto: Crypto): Color {
        return if (crypto.changePerc.contains("-")) {
            ChangePercentType.DECREASE.color
        } else {
            ChangePercentType.INCREASE.color
        }
    }
}