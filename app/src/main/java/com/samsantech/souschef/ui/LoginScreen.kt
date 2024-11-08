package com.samsantech.souschef.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.samsantech.souschef.firebase.FirebaseAuthManager
import com.samsantech.souschef.ui.components.FormOutlinedTextField
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.ProgressSpinner
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignUp: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var loginError by remember {
        mutableStateOf("")
    }
    var isLoggingIn by remember {
        mutableStateOf(false)
    }

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
                text = "Login",
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
                value = email,
                onValueChange = {
                                email = it
                },
                label = "Email",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null
                    )
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            var isPasswordVisualTransformation by remember {
                mutableStateOf(true)
            }
            FormOutlinedTextField(
                isPassword = true,
                value = password,
                onValueChange = {
                                password = it
                },
                label = "Password",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "lock icon"
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = if (!isPasswordVisualTransformation) { painterResource(id = R.drawable.visibility_vector) }
                            else { painterResource(id = R.drawable.visibility_off_vector) },
                        contentDescription = "email icon",
                        modifier = Modifier
                            .clickable {
                                isPasswordVisualTransformation = !isPasswordVisualTransformation
                            }
                    )
                },
                isPasswordVisualTransformation = isPasswordVisualTransformation
            )
            if (loginError.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = loginError, fontSize = 14.sp, color = Color.Red, modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(16.dp))
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//            ) {
//                Text(
//                    text = "Forgot Password?",
//                    color = Color.Red,
//                    fontSize = 12.sp,
//                    modifier = Modifier
//                        .clickable (
//                            onClick = onNavigateToForgotPassword
//                        )
//                        .align(Alignment.CenterEnd)
//                )
//            }
//            Spacer(modifier = Modifier.height(20.dp))
            ColoredButton(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty())
                    {
                        isLoggingIn = true
                        authViewModel.login(email, password) { isSuccess, err ->
                            isLoggingIn = false

                            if (isSuccess) {
                                onNavigateToHome()
                            } else {
                                loginError = err ?: "Unable to login"
                            }
                        }
                    }
                },
                text = "Login"
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Don't have an account yet?",
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(16.dp))
            ColoredButton(
                onClick = onNavigateToSignUp,
                containerColor = Color.White, contentColor = Green,
                text = "Sign Up",
                border = BorderStroke(1.dp, Color.Black)
            )
        }
    }

    if (isLoggingIn) {
        ProgressSpinner()
    }
}