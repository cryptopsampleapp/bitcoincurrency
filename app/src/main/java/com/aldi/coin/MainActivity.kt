package com.aldi.coin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aldi.coin.data.service.repository.MockData
import com.aldi.coin.ui.theme.AldiCoinTheme
import com.aldi.coin.ui.theme.CryptoListItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AldiCoinTheme {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AldiCoinTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val cryptos = remember { MockData.cryptoList }
            LazyColumn {
                items(
                    items = cryptos,
                    itemContent = {
                        CryptoListItem(crypto = it)
                    })
            }
        }
    }
}