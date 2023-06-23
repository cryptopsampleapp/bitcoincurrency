package com.aldi.coin.domain.interactor

import java.math.RoundingMode

object AbbreviateDecimalUseCase {

    enum class DecimalAbbreviationType(val symbol: Char) {
        THOUSAND('K'),
        MILLION('M'),
        BILLION('B')
    }

    operator fun invoke(value: String): String {
        val bigDecimal = value.toBigDecimal()
        val bigInteger = bigDecimal.toBigInteger()
        val integerLength = bigInteger.toString().length
        return  when {
            integerLength < 4 -> bigDecimal.setScale(2, RoundingMode.HALF_DOWN).toString()
            integerLength in 4..6 -> bigInteger.toBigDecimal(3).setScale(2, RoundingMode.HALF_DOWN).toString() + DecimalAbbreviationType.THOUSAND.symbol
            integerLength in 7..9 -> bigInteger.toBigDecimal(6).setScale(2, RoundingMode.HALF_DOWN).toString() + DecimalAbbreviationType.MILLION.symbol
            else -> bigInteger.toBigDecimal(9).setScale(2, RoundingMode.HALF_DOWN).toString() + DecimalAbbreviationType.BILLION.symbol
        }

    }
}