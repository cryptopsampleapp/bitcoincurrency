package com.aldi.coin

import com.aldi.coin.domain.interactor.AbbreviateDecimalUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun abbreviateDecimalUseCaseTest() {
        assertEquals("122.23K", AbbreviateDecimalUseCase("122230.151361361246245136573"))
        assertEquals("1.41K", AbbreviateDecimalUseCase("1411.835689356883568"))
        assertEquals("560.16K", AbbreviateDecimalUseCase("560162.95679356936554"))
        assertEquals("6.56M", AbbreviateDecimalUseCase("6562423.995678252555555535252"))
        assertEquals("4.57B", AbbreviateDecimalUseCase("4567122230.865467854"))
        assertEquals("813.56B", AbbreviateDecimalUseCase("813564568712.865467854"))
        assertEquals("986.23", AbbreviateDecimalUseCase("986.229837277236263464262"))
    }
}
