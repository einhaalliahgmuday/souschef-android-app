package com.samsantech.souschef.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.samsantech.souschef.ui.components.LogoWithText
import kotlinx.coroutines.delay

@Composable
fun OpeningScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(22, 166, 55, 255)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogoWithText()
    }

    LaunchedEffect(key1 = Unit) {
        delay(5000)
//        withContext(Dispatchers.Main) {
//            onNavigateToStart()
//        }
    }
}