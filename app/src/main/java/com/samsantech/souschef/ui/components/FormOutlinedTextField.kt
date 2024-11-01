package com.samsantech.souschef.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormOutlinedTextField(isPassword: Boolean = false,
                          value: String, onValueChange: (String) -> Unit,
                          label: String? = null,
                          leadingIcon:  @Composable() (() -> Unit)? = null,
                          trailingIcon:  @Composable() (() -> Unit)? = null) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            if (label != null) {
                Text(text = label)
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (isPassword) { PasswordVisualTransformation() } else { VisualTransformation.None } ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        ),
    )
}