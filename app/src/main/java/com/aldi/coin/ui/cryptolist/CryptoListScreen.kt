package com.aldi.coin.ui.cryptolist

import androidx.compose.foundation.background
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
import com.aldi.coin.Constants.TOP_CRYPTO_LIST_SIZE
import com.aldi.coin.data.model.DecoratedCrypto
import com.aldi.coin.data.repository.MockRepo
import com.aldi.coin.toDecoratedCrypto
import com.aldi.coin.ui.components.ErrorView
import com.aldi.coin.ui.theme.AldiCoinTheme
import com.aldi.coin.ui.theme.CustomBlue
import com.aldi.coin.ui.theme.perc20
import kotlinx.coroutines.runBlocking

@Composable
fun CryptoListScreen(
    onNavigateToDetails: ((cryptoId: String) -> Unit),
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomBlue.copy(perc20))
    ) {
        when (val screenState = state) {
            CryptoListUiState.InProgress -> CircularProgressIndicator(
                modifier = Modifier.align(
                    Center
                )
            )

            is CryptoListUiState.Success -> CryptoListView(
                screenState.cryptoList,
                onNavigateToDetails
            )

            is CryptoListUiState.Error -> ErrorView(
                screenState.message
            ) { viewModel.handleEvent(CryptoListEvent.OnRefresh) }
        }
    }
}


@Composable
private fun CryptoListView(
    cryptoList: List<DecoratedCrypto>, onNavigateToDetails: ((cryptoId: String) -> Unit?)? = null
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
        val cryptoList =
            runBlocking { MockRepo().getCryptoList(TOP_CRYPTO_LIST_SIZE) }.map { it.toDecoratedCrypto() }
        CryptoListView(cryptoList)
    }
}