package com.samsantech.souschef.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.samsantech.souschef.ui.theme.Green

@Composable
fun ImageButton(modifier: Modifier = Modifier, onClick: () -> Unit, containerColor: Color = Green, contentColor: Color = Color.White,
                imageVector: ImageVector, border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
                contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Button(
        modifier = modifier
            .border(border, RoundedCornerShape(20)),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(20),
        contentPadding = contentPadding,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier
                .size(22.dp)
        )
    }
}