package com.aldi.coin.ui.cryptolist

import com.aldi.coin.ui.theme.Poppins
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.aldi.coin.data.model.DecoratedCrypto
import com.aldi.coin.data.repository.MockRepo
import com.aldi.coin.toDecoratedCrypto
import com.aldi.coin.ui.theme.AldiCoinTheme
import com.aldi.coin.ui.theme.font_size1
import com.aldi.coin.ui.theme.font_size16
import com.aldi.coin.ui.theme.font_size20
import com.aldi.coin.ui.theme.perc40
import com.aldi.coin.ui.theme.size16
import com.aldi.coin.ui.theme.size24
import com.aldi.coin.ui.theme.size56
import com.aldi.coin.ui.theme.size88
import kotlinx.coroutines.runBlocking

@Composable
fun CryptoListItem(crypto: DecoratedCrypto, onNavigateToDetails: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = size24, vertical = size16)
            .height(size88)
            .clickable { onNavigateToDetails() }
            .background(Color.White.copy(perc40), RoundedCornerShape(corner = CornerSize(size16)))
    ) {
        val (logo, name, symbol, price, changePerc) = createRefs()
        Image(painter = painterResource(id = crypto.icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(size56)
                .width(size56)
                .constrainAs(logo) {
                    start.linkTo(parent.start, margin = size16)
                    centerVerticallyTo(parent)
                })

        Text(text = crypto.name, fontFamily = Poppins, style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false,
            ), fontSize = font_size20, fontWeight = FontWeight.Bold
        ), modifier = Modifier.constrainAs(name) {
            top.linkTo(logo.top)
            start.linkTo(logo.end, margin = size16)
        })

        Text(text = crypto.symbol, style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false,
            ), fontSize = font_size16
        ), modifier = Modifier.constrainAs(symbol) {
            start.linkTo(logo.end, margin = size16)
            bottom.linkTo(logo.bottom)
        })

        Text(
            text = crypto.price,
            fontSize = font_size16,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier.constrainAs(price) {
                top.linkTo(logo.top)
                end.linkTo(parent.end, margin = size16)
            }, letterSpacing = font_size1
        )

        Text(
            text = crypto.changePerc,
            style = typography.caption,
            textAlign = TextAlign.End,
            color = crypto.changePercentType,
            fontSize = font_size16,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(changePerc) {
                bottom.linkTo(logo.bottom)
                end.linkTo(parent.end, margin = size16)
            }, letterSpacing = font_size1
        )
    }
}

@Preview
@Composable
private fun PreviewCryptoListItem() {
    AldiCoinTheme {
        val crypto = runBlocking { MockRepo().getCryptoById("bitcoin") }.toDecoratedCrypto()
        CryptoListItem(crypto = crypto) {}
    }
}
