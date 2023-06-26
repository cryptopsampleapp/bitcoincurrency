package com.aldi.coin.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aldi.coin.ui.components.CustomAppBar
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