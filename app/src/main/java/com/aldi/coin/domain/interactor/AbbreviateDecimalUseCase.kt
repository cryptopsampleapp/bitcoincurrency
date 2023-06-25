package com.aldi.coin.domain.interactor

import com.aldi.coin.data.enum.MagnitudeType
import com.aldi.coin.data.enum.getMagnitudeType
import java.math.BigInteger
import java.math.RoundingMode
import java.security.InvalidParameterException

object AbbreviateDecimalUseCase {

    operator fun invoke(value: String): String {
        val bigDecimal = value.toBigDecimal()
        val bigInteger = bigDecimal.toBigInteger()
        val integerMagnitude = getMagnitudeType(bigInteger.toString())
        return if (integerMagnitude == MagnitudeType.NONE) {
            bigDecimal.setScale(2, RoundingMode.HALF_DOWN).toString()
        } else {
            formByMagnitudeType(bigInteger, integerMagnitude)
        }
    }

    private fun formByMagnitudeType(bigInteger: BigInteger, type: MagnitudeType): String {
        val abbreviatedDecimal = when (type) {
            MagnitudeType.THOUSAND -> bigInteger.toBigDecimal(3)
            MagnitudeType.MILLION -> bigInteger.toBigDecimal(6)
            MagnitudeType.BILLION -> bigInteger.toBigDecimal(9)
            else -> throw InvalidParameterException()
        }
        return abbreviatedDecimal.setScale(2, RoundingMode.HALF_DOWN).toString()
    }
}