package com.samsantech.souschef.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ErrorText(text: String, textAlign: TextAlign =TextAlign.Start) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.Red,
        modifier = Modifier.fillMaxWidth(),
        textAlign = textAlign
    )
}