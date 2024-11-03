package com.samsantech.souschef.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTextField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit,
                  label: @Composable() (() -> Unit)? = null,
                  textAlign: TextAlign = TextAlign.Start,
                  maxLines: Int = Int.MAX_VALUE) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        textStyle = LocalTextStyle.current.merge(
            textAlign = textAlign,
        ),
        maxLines = maxLines,
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        )
    )
}