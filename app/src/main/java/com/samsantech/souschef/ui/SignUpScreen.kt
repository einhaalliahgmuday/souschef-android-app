package com.samsantech.souschef.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.R
import com.samsantech.souschef.ui.components.FormOutlinedTextField
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 70.dp, start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create an account",
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
                value = "Hello",
                onValueChange = {},
                label = "Username",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "lock icon"
                    )
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            FormOutlinedTextField(
                value = "oka",
                onValueChange = {},
                label = "Email",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "email icon"
                    )
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            FormOutlinedTextField(
                isPassword = true,
                value = "AHAHHAHA",
                onValueChange = {},
                label = "Password",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "lock icon"
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.visibility_vector),
                        contentDescription = "email icon"
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ColoredButton(
                onClick = {  },
                text = "Continue"
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Already have an account?",
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(16.dp))
            ColoredButton(
                onClick = {  },
                containerColor = Color.White, contentColor = Green,
                text = "Login",
                border = BorderStroke(1.dp, Color.Black)
            )
        }
    }
}