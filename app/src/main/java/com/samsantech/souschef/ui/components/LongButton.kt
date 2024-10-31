package com.samsantech.souschef.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LongButton(onClick: () -> Unit, containerColorName: String = "green", text: String,
               border: BorderStroke = BorderStroke(0.dp, Color.White)
) {
    // default container color is green
    var containerColor: Long = 0xFF16A637
    var contentColor: Long = 0xFFFFFFFF

    if (containerColorName == "white")
    {
        containerColor = 0xFFFFFFFF
        contentColor = 0xFF16A637
    }
    else if (containerColorName == "yellow")
    {
        containerColor = 0xFFFFD600
        contentColor = 0xFF16A637
    }

    Button(
        onClick = {} ,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(containerColor),
            contentColor = Color(contentColor)
        ),
        shape = RoundedCornerShape(20),
        contentPadding = PaddingValues(50.dp, 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(border, RoundedCornerShape(20))
    ) {
        Text(text = text,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}