package com.samsantech.souschef.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SkipButton(onClick: () -> Unit) {
    Box (
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = onClick ,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(0.dp, 0.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Text(text = "Skip",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }

}