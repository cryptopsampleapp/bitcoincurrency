package com.aldi.coin.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aldi.coin.Constants
import com.aldi.coin.Constants.CRYPTO_LIST_TITLE
import com.aldi.coin.ui.cryptodetail.CryptoDetailScreen
import com.aldi.coin.ui.cryptolist.CryptoListScreen
import com.aldi.coin.ui.theme.CustomGray
import java.util.Locale

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomAppBar() {
    val navController = rememberNavController()
    var canPop by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf(CRYPTO_LIST_TITLE) }

    val navigationIcon: (@Composable () -> Unit)? = if (canPop) {
        {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = CustomGray
                )
            }
        }
    } else {
        null
    }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, _, _ ->
            canPop = controller.previousBackStackEntry != null
            if (!canPop) {
                title = CRYPTO_LIST_TITLE
            }
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title.uppercase(Locale.getDefault()),
                        color = CustomGray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(20.dp, 0.dp)
                            .offset(x = if (canPop) (-40).dp else 0.dp)
                    )
                },
                navigationIcon = navigationIcon,
                elevation = 0.dp,
            )
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Constants.CRYPTO_LIST_SCREEN
            ) {
                composable(Constants.CRYPTO_LIST_SCREEN) {
                    CryptoListScreen(onNavigateToDetails = { cryptoId ->
                        title = cryptoId
                        navController.navigate(
                            "${Constants.CRYPTO_DETAIL_SCREEN}/$cryptoId"
                        )
                    })
                }
                composable(
                    route = "${Constants.CRYPTO_DETAIL_SCREEN}/{${Constants.CRYPTO_DETAIL_ARG}}"
                ) { CryptoDetailScreen() }
            }
        }
    )
}