package com.samsantech.souschef.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormBasicTextField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit,
                       minLines: Int = 1, maxLines: Int = Int.MAX_VALUE,
                       placeholder: String? = null, textAlign: TextAlign = TextAlign.Start) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        minLines = minLines,
        maxLines = maxLines,
        textStyle = TextStyle(fontSize = 16.sp, textAlign = textAlign),
        modifier = modifier
            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(12.dp)
            .fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (placeholder != null) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray.copy(alpha = 0.5f)
                        )
                    }
                }

                innerTextField()
            }
    )
}