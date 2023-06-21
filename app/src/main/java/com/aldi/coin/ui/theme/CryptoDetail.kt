package com.aldi.coin.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun CryptoDetail() {
    Surface(color = Color.White) {
        Surface(color = CustomBlue.copy(0.2F)) {
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
                    Row(Modifier.padding(24.dp,24.dp,24.dp,12.dp)) {
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
                            text = "\$28.61K",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            color = CustomGray,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(Modifier.padding(24.dp,12.dp)) {
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
                            text = "\$28.61K",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            color = CustomGray,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Divider(color = CustomBlue.copy(0.2F), thickness = 1.dp, modifier = Modifier.padding(24.dp, 20.dp))


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
                            text = "\$28.61K",
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
                            text = "\$28.61K",
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
                            text = "\$28.61K",
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
    }
}