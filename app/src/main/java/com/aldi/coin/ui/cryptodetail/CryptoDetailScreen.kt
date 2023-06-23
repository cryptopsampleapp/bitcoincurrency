package com.aldi.coin.ui.cryptodetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldi.coin.data.model.Crypto
import com.aldi.coin.data.repository.MockRepo
import com.aldi.coin.ui.components.CustomAppBar
import com.aldi.coin.ui.components.Snackbar
import com.aldi.coin.ui.theme.AldiCoinTheme
import com.aldi.coin.ui.theme.CustomBlue
import com.aldi.coin.ui.theme.CustomGray
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


    Box(modifier =
        Modifier
            .pullRefresh(pullRefreshState)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        when (val screenState = state) {
            CryptoDetailUiState.InProgress -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
            is CryptoDetailUiState.Success -> CryptoDetailView(screenState.crypto)
            is CryptoDetailUiState.Error -> Snackbar(screenState.message) {
                viewModel.handleEvent(
                    CryptoDetailEvent.OnRefresh)
            }
        }


    }
}

@Composable
fun CryptoDetailView(crypto: Crypto) {
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxSize(),
        backgroundColor = Color.White.copy(0.4F),
        shape = RoundedCornerShape(
            corner = CornerSize(16.dp)
        ),
    ) {
        Column {
            Row(Modifier.padding(24.dp, 24.dp, 24.dp, 12.dp)) {
                Text(
                    textAlign = TextAlign.Start,
                    text = "Price",
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = crypto.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(Modifier.padding(24.dp, 12.dp)) {
                Text(
                    textAlign = TextAlign.Start,
                    text = "Change (24hr)",
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = crypto.changePerc,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
            }

            Divider(
                color = CustomBlue.copy(0.2F),
                thickness = 1.dp,
                modifier = Modifier.padding(24.dp, 20.dp)
            )

            Row(Modifier.padding(24.dp, 12.dp)) {
                Text(
                    textAlign = TextAlign.Start,
                    text = "Market cap",
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = crypto.marketCap,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(Modifier.padding(24.dp, 12.dp)) {
                Text(
                    textAlign = TextAlign.Start,
                    text = "Volume (24hr)",
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = crypto.volume,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(Modifier.padding(24.dp, 12.dp)) {
                Text(
                    textAlign = TextAlign.Start,
                    text = "Supply",
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    textAlign = TextAlign.End,
                    text = crypto.supply,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = CustomGray,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CryptoDetailPreview() {
    AldiCoinTheme {
        val crypto = runBlocking { MockRepo().getCryptoById("bitcoin") }
        CryptoDetailView(crypto = crypto)
    }
}