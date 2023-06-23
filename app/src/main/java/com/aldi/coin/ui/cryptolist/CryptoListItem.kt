package com.aldi.coin.ui.cryptolist

import Poppins
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aldi.coin.R
import com.aldi.coin.data.model.Crypto
import com.aldi.coin.data.repository.MockRepo
import com.aldi.coin.ui.theme.AldiCoinTheme
import kotlinx.coroutines.runBlocking

@Composable
fun CryptoListItem(crypto: Crypto, onNavigateToDetails: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .height(88.dp)
            .clickable { onNavigateToDetails() },
        backgroundColor = Color.White.copy(0.4F),
        elevation = 1.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (logo, name, symbol, price, changePerc) = createRefs()
            Image(painter = painterResource(id = R.drawable.ic_btc),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(56.dp)
                    .width(56.dp)
                    .constrainAs(logo) {
                        start.linkTo(parent.start, margin = 16.dp)
                        centerVerticallyTo(parent)
                    })

            Text(text = crypto.name, fontFamily = Poppins, style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false,
                ), fontSize = 20.sp, fontWeight = FontWeight.Bold
            ), modifier = Modifier.constrainAs(name) {
                top.linkTo(logo.top)
                start.linkTo(logo.end, margin = 16.dp)
            })

            Text(text = crypto.symbol, style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false,
                ), fontSize = 16.sp
            ), modifier = Modifier.constrainAs(symbol) {
                start.linkTo(logo.end, margin = 16.dp)
                bottom.linkTo(logo.bottom)
            })

            Text(
                text = crypto.price,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier.constrainAs(price) {
                    top.linkTo(logo.top)
                    end.linkTo(parent.end, margin = 16.dp)
                }, letterSpacing = 1.sp
            )

            Text(
                text = crypto.changePerc,
                style = typography.caption,
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(changePerc) {
                    bottom.linkTo(logo.bottom)
                    end.linkTo(parent.end, margin = 16.dp)
                }, letterSpacing = 1.sp
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCryptoListItem() {
    AldiCoinTheme {
        val crypto = runBlocking { MockRepo().getCryptoById("bitcoin")}
        CryptoListItem(crypto = crypto) {}
    }
}
