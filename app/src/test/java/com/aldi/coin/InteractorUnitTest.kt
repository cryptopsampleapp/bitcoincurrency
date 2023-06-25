package com.aldi.coin

import com.aldi.coin.data.repository.MockRepo
import com.aldi.coin.domain.interactor.AbbreviateDecimalUseCase
import com.aldi.coin.domain.interactor.DecorateValueUseCase
import com.aldi.coin.domain.interactor.MapChangePercentColorUseCase
import com.aldi.coin.domain.interactor.MapIconUseCase
import com.aldi.coin.ui.theme.CustomGreen
import com.aldi.coin.ui.theme.CustomRed
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class InteractorUnitTest {

    private val btc = runBlocking { MockRepo().getCryptoById("bitcoin") }
    private val xrp = runBlocking { MockRepo().getCryptoById("xrp") }
    private val tether = runBlocking { MockRepo().getCryptoById("tether") }
    private val doge = runBlocking { MockRepo().getCryptoById("dogecoin") }

    @Test
    fun abbreviateDecimalUseCaseTest() {
        assertEquals("6.01", AbbreviateDecimalUseCase("6.00837277236263464262"))
        assertEquals("56.49", AbbreviateDecimalUseCase("56.485837277236263464262"))
        assertEquals("986.23", AbbreviateDecimalUseCase("986.229837277236263464262"))
        assertEquals("1.41", AbbreviateDecimalUseCase("1411.835689356883568"))
        assertEquals("12.24", AbbreviateDecimalUseCase("12239.151361361246245136573"))
        assertEquals("560.15", AbbreviateDecimalUseCase("560154.95679356936554"))
        assertEquals("6.56", AbbreviateDecimalUseCase("6562423.995678252555555535252"))
        assertEquals("42.57", AbbreviateDecimalUseCase("42567122230.865467854"))
        assertEquals("813.56", AbbreviateDecimalUseCase("813564568712.865467854"))
    }

    @Test
    fun decorateValueUseCaseTest() {
        assertEquals("-0.28%", DecorateValueUseCase("-0.276128794950964", false))
        assertEquals("12.38%", DecorateValueUseCase("12.376128794950964", false))
        assertEquals("$143.23", DecorateValueUseCase("143.226734373"))
        assertEquals("$68.08K", DecorateValueUseCase("68079.00262462727"))
        assertEquals("$99.11M", DecorateValueUseCase("99109043.7354628"))
        assertEquals("$1.03B", DecorateValueUseCase("1029243589.226734373"))

    }

    @Test
    fun mapChangePercentColorUseCaseTest() {
        assertEquals(CustomRed, MapChangePercentColorUseCase(btc))
        assertEquals(CustomGreen, MapChangePercentColorUseCase(xrp))
        assertEquals(CustomRed, MapChangePercentColorUseCase(tether))
        assertEquals(CustomGreen, MapChangePercentColorUseCase(doge))

    }

    @Test
    fun mapIconUseCaseTest() {
        assertEquals(R.drawable.ic_btc, MapIconUseCase(btc))
        assertEquals(R.drawable.ic_xrp, MapIconUseCase(xrp))
        assertEquals(R.drawable.ic_usdt, MapIconUseCase(tether))
        assertEquals(R.drawable.ic_coin, MapIconUseCase(doge))
    }
}
