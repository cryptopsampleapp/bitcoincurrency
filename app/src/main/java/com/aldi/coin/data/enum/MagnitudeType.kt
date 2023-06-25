package com.aldi.coin.data.enum

enum class MagnitudeType(val symbol: Char?) {
    NONE(null),
    THOUSAND('K'),
    MILLION('M'),
    BILLION('B');
}

fun getMagnitudeType(value: String): MagnitudeType {
    return when {
        value.length < 4 -> MagnitudeType.NONE
        value.length in 4..6 -> MagnitudeType.THOUSAND
        value.length in 7..9 -> MagnitudeType.MILLION
        value.length in 10..12 -> MagnitudeType.BILLION
        else -> MagnitudeType.NONE
    }
}
