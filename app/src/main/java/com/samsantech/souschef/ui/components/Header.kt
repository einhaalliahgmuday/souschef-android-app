package com.samsantech.souschef.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .background(Color(22, 166, 55, 255))
            .fillMaxWidth()
            .height(110.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "SOUSCHEF",
            fontSize = 28.sp,
            fontWeight = FontWeight(700),
            color = Color(255, 207, 81, 255)
        )
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
        )
    }
}