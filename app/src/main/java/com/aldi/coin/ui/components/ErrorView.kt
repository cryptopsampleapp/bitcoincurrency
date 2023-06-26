package com.aldi.coin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.aldi.coin.R
import com.aldi.coin.ui.theme.CustomGray
import com.aldi.coin.ui.theme.font_size1
import com.aldi.coin.ui.theme.font_size20
import com.aldi.coin.ui.theme.perc40
import com.aldi.coin.ui.theme.size16
import com.aldi.coin.ui.theme.size20
import java.util.Locale

@Composable
fun ErrorView(message: String, action: (() -> Unit)? = null) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Center)) {
            Text(
                text = message, modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(size20),
                fontSize = font_size20,
                letterSpacing = font_size1,
                color = CustomGray,
                textAlign = TextAlign.Center
            )
            TextButton(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .background(
                        Color.White.copy(perc40),
                        RoundedCornerShape(corner = CornerSize(size16))
                    ),
                onClick = {
                    action?.invoke()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.retry).uppercase(Locale.ROOT),
                    fontSize = font_size20,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = font_size1,
                    color = CustomGray
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(message = "nini")
}