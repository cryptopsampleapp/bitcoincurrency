package com.aldi.coin.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aldi.coin.Constants.CRYPTO_DETAIL_ARG
import com.aldi.coin.Constants.CRYPTO_DETAIL_SCREEN
import com.aldi.coin.Constants.CRYPTO_LIST_SCREEN
import com.aldi.coin.ui.components.CustomAppBar
import com.aldi.coin.ui.cryptodetail.CryptoDetailScreen
import com.aldi.coin.ui.cryptolist.CryptoListScreen
import com.aldi.coin.ui.theme.AldiCoinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AldiCoinTheme {
                CustomAppBar()
            }
        }
    }
}