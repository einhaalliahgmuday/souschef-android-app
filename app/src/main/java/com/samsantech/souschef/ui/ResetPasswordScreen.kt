package com.samsantech.souschef.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.R
import com.samsantech.souschef.ui.components.FormOutlinedTextField
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher

@Composable
fun ResetPasswordScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 70.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reset Password",
            color = Color(0xFF16A637),
            fontSize = 32.sp,
            fontFamily = Konkhmer_Sleokcher,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        )

        FormOutlinedTextField(
            isPassword = true,
            value = "",
            onValueChange = {},
            label = "Enter your new password",
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.visibility_vector),
                    contentDescription = "email icon"
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        FormOutlinedTextField(
            isPassword = true,
            value = "",
            onValueChange = {},
            label = "Confirm your password",
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.visibility_vector),
                    contentDescription = "email icon"
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        ColoredButton(
            onClick = {  },
            text = "Confirm"
        )
    }
}