package com.aldi.coin.data.repository

import com.aldi.coin.data.model.Crypto

class MockRepo : CoinCapRepository {
    private val cryptoList = listOf(
        Crypto(
            "tether",
            "Tether",
            "USDT",
            "83212018333.0169000000000000",
            "1.0011008633147628",
            "-0.0789211687969257",
            "83303623391.3470878677364319",
            "7707160473.8680673251107991"
        ), Crypto(
            "binance-coin",
            "BNB",
            "BNB",
            "166801148.0000000000000000",
            "237.7590520268067878",
            "0.4216836797080667",
            "39658482825.4630989792323944",
            "205043014.0322300073241390"
        ), Crypto(
            "bitcoin",
            "Bitcoin",
            "btc",
            "17193925.0000000000000000",
            "6929.8217756835584756",
            "-0.8101417214350335",
            "119150835874.4699281625807300",
            "2927959461.1750323310959460"
        ), Crypto(
            "xrp",
            "XRP",
            "XRP",
            "45404028640.0000000000000000",
            "0.4893968310577878",
            "0.2910586257368433",
            "22220587733.6730387662425920",
            "226890968.5975320195339910"
        ), Crypto(
            "dogecoin",
            "Dogecoin",
            "DOGE",
            "139915466383.7052300000000000",
            "0.0669607745782652",
            "0.2328225981188918",
            "9368848004.5321283401146016",
            "87974476.1278521324853258",
        )
    )

    override suspend fun getCryptoList(size: Int) = cryptoList.take(size)

    override suspend fun getCryptoById(cryptoId: String) =
        cryptoList.first { crypto -> crypto.id == cryptoId }
}



