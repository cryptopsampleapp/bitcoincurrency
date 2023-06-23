package com.aldi.coin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aldi.coin.Constants.CRYPTO_DETAIL_SCREEN
import com.aldi.coin.Constants.CRYPTO_LIST_SCREEN
import com.aldi.coin.data.service.repository.MockData
import com.aldi.coin.ui.theme.AldiCoinTheme
import com.aldi.coin.ui.theme.CryptoDetail
import com.aldi.coin.ui.theme.CryptoListItem
import com.aldi.coin.ui.theme.CustomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavHost()
        }
    }
}

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = CRYPTO_LIST_SCREEN
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(CRYPTO_LIST_SCREEN) { CryptoList { navController.navigate(CRYPTO_DETAIL_SCREEN) } }
        composable(
            route = "$CRYPTO_DETAIL_SCREEN/{cryptoId}"
        ) { CryptoDetail(MockData.cryptoList.first()) }
    }
}

@Composable
fun CryptoList(onNavigateToDetails: ((cryptoId: String) -> Unit?)? = null) {
    val cryptos = remember { MockData.cryptoList }
    Column {
        CustomAppBar(title = "coin")
        LazyColumn {
            items(cryptos) { crypto ->
                CryptoListItem(crypto) { onNavigateToDetails?.invoke(crypto.id) }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    AldiCoinTheme {
        CryptoList()
    }
}