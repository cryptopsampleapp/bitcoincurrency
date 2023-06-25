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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aldi.coin.R
import com.aldi.coin.ui.theme.CustomGray
import java.util.Locale

@Composable
fun ErrorView(message: String, action: (() -> Unit)? = null) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Center)) {
            Text(
                text = message, modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(20.dp),
                fontSize = 20.sp,
                letterSpacing = 1.sp,
                color = CustomGray,
                textAlign = TextAlign.Center
            )
            TextButton(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .background(
                        Color.White.copy(0.4F),
                        RoundedCornerShape(corner = CornerSize(16.dp))
                    ),
                onClick = {
                    action?.invoke()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.retry).uppercase(Locale.ROOT),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
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