package com.aldi.coin.ui.cryptolist

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldi.coin.data.model.Crypto
import com.aldi.coin.data.repository.MockRepo
import com.aldi.coin.ui.components.Snackbar
import com.aldi.coin.ui.theme.AldiCoinTheme
import kotlinx.coroutines.runBlocking

@Composable
fun CryptoListScreen(
    onNavigateToDetails: ((cryptoId: String) -> Unit),
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when (val screenState = state) {
            CryptoListUiState.InProgress -> CircularProgressIndicator(
                modifier = Modifier.align(
                    Center
                )
            )

            is CryptoListUiState.Success -> CryptoListView(
                screenState.cryptoList,
                screenState.iconList,
                onNavigateToDetails
            )

            is CryptoListUiState.Error -> Snackbar(
                screenState.message
            ) { viewModel.handleEvent(CryptoListEvent.OnRefresh) }
        }
    }
}


@Composable
private fun CryptoListView(
    cryptoList: List<Crypto>, @DrawableRes iconList : List<Int>?=null, onNavigateToDetails: ((cryptoId: String) -> Unit?)? = null
) {
    LazyColumn {
        items(cryptoList) { crypto ->
            CryptoListItem(crypto) { onNavigateToDetails?.invoke(crypto.id) }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun CryptoListScreenPreview() {
    AldiCoinTheme {
        val cryptoList = runBlocking { MockRepo().getCryptoList(10) }
        CryptoListView(cryptoList)
    }
}