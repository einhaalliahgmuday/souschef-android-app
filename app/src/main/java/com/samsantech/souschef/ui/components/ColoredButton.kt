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
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Yellow

@Composable
fun ColoredButton(modifier: Modifier = Modifier, onClick: () -> Unit, containerColorName: String = "green", text: String,
                  border: BorderStroke = BorderStroke(0.dp, Color.White), contentPadding: PaddingValues = PaddingValues(12.dp, 12.dp),
) {
    // default container color is green
    var containerColor: Color = Green
    var contentColor: Color = Color.White

    if (containerColorName == "white-green")
    {
        containerColor = Yellow
        contentColor = Green
    }
    else if (containerColorName == "yellow-green")
    {
        containerColor = Yellow
        contentColor = Green
    } else if (containerColorName == "white-black")
    {
        containerColor = Color.White
        contentColor = Color.Black
    }

    Button(
        modifier = modifier
            .fillMaxWidth()
            .border(border, RoundedCornerShape(20)),
        onClick = {} ,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(20),
        contentPadding = contentPadding,
    ) {
        Text(text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}