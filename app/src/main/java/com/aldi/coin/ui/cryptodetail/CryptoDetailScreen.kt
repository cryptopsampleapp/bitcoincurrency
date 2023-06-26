package com.aldi.coin.ui.cryptodetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldi.coin.R
import com.aldi.coin.data.model.DecoratedCrypto
import com.aldi.coin.data.repository.MockRepo
import com.aldi.coin.toDecoratedCrypto
import com.aldi.coin.ui.components.ErrorView
import com.aldi.coin.ui.theme.AldiCoinTheme
import com.aldi.coin.ui.theme.CustomBlue
import com.aldi.coin.ui.theme.CustomGray
import com.aldi.coin.ui.theme.font_size1
import com.aldi.coin.ui.theme.font_size16
import com.aldi.coin.ui.theme.perc100
import com.aldi.coin.ui.theme.perc20
import com.aldi.coin.ui.theme.perc40
import com.aldi.coin.ui.theme.size1
import com.aldi.coin.ui.theme.size12
import com.aldi.coin.ui.theme.size16
import com.aldi.coin.ui.theme.size20
import com.aldi.coin.ui.theme.size24
import kotlinx.coroutines.runBlocking


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CryptoDetailScreen(
    viewModel: CryptoDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        isRefreshing,
        { viewModel.handleEvent(CryptoDetailEvent.OnRefresh) })
    BoxWithConstraints {
        val pageSize = this.maxHeight
        Box(
            modifier =
            Modifier
                .pullRefresh(pullRefreshState)
                .verticalScroll(rememberScrollState())
                .background(CustomBlue.copy(perc20))
                .height(pageSize)
                .fillMaxSize()
        ) {
            PullRefreshIndicator(
                isRefreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
            when (val screenState = state) {
                CryptoDetailUiState.InProgress -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )

                is CryptoDetailUiState.Success -> CryptoDetailView(screenState.crypto)
                is CryptoDetailUiState.Error -> ErrorView(screenState.message) {
                    viewModel.handleEvent(
                        CryptoDetailEvent.OnRefresh
                    )
                }
            }


        }
    }
}

@Composable
fun CryptoDetailView(crypto: DecoratedCrypto) {
    Column(
        modifier = Modifier
            .padding(horizontal = size24, vertical = size16)
            .fillMaxSize()
            .background(Color.White.copy(perc40), RoundedCornerShape(corner = CornerSize(size16)))
    ) {
        Row(Modifier.padding(size24, size24, size24, size12)) {
            Text(
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.crypto_price),
                fontSize = font_size16,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
            Text(
                textAlign = TextAlign.End,
                text = crypto.price,
                fontSize = font_size16,
                fontWeight = FontWeight.Bold,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
        }

        Row(Modifier.padding(size24, size12)) {
            Text(
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.crypto_change_perc),
                fontSize = font_size16,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
            Text(
                textAlign = TextAlign.End,
                text = crypto.changePerc,
                color = crypto.changePercentType,
                fontSize = font_size16,
                fontWeight = FontWeight.Bold,
                letterSpacing = font_size1,
                modifier = Modifier.weight(perc100)
            )
        }

        Divider(
            color = CustomBlue.copy(perc20),
            thickness = size1,
            modifier = Modifier.padding(size24, size20)
        )

        Row(Modifier.padding(size24, size12)) {
            Text(
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.crypto_market_cap),
                fontSize = font_size16,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
            Text(
                textAlign = TextAlign.End,
                text = crypto.marketCap,
                fontSize = font_size16,
                fontWeight = FontWeight.Bold,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
        }

        Row(Modifier.padding(size24, size12)) {
            Text(
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.crypto_volume),
                fontSize = font_size16,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
            Text(
                textAlign = TextAlign.End,
                text = crypto.volume,
                fontSize = font_size16,
                fontWeight = FontWeight.Bold,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
        }

        Row(Modifier.padding(size24, size12)) {
            Text(
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.crypto_supply),
                fontSize = font_size16,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
            Text(
                textAlign = TextAlign.End,
                text = crypto.supply,
                fontSize = font_size16,
                fontWeight = FontWeight.Bold,
                letterSpacing = font_size1,
                color = CustomGray,
                modifier = Modifier.weight(perc100)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CryptoDetailPreview() {
    AldiCoinTheme {
        val crypto = runBlocking { MockRepo().getCryptoById("bitcoin") }.toDecoratedCrypto()
        CryptoDetailView(crypto = crypto)
    }
}