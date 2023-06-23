package com.aldi.coin.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import java.util.Locale

@Composable
fun CustomAppBar(title: String) {
    val navController = rememberNavController()
    TopAppBar(
        title = {
            Text(
                text = (title).uppercase(Locale.getDefault()),
                color = CustomGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        },
        elevation = 0.dp,
        navigationIcon = navController.previousBackStackEntry?.let {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}