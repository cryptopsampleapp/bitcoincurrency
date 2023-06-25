package com.aldi.coin.domain.interactor

import com.aldi.coin.data.enum.NumberValueType.CURRENCY
import com.aldi.coin.data.enum.NumberValueType.PERCENTAGE
import com.aldi.coin.data.enum.getMagnitudeType

object DecorateValueUseCase {
    operator fun invoke(value: String, isCurrency: Boolean = true): String {
        val numberType = if (isCurrency) CURRENCY else PERCENTAGE
        return if (numberType == CURRENCY) {
            val magnitudeSymbol = getMagnitudeType(
                value.substringBefore(
                    "."
                )
            ).symbol ?: ""
            numberType.symbol + AbbreviateDecimalUseCase(value) + magnitudeSymbol
        } else {
            AbbreviateDecimalUseCase(value) + numberType.symbol
        }
    }
}