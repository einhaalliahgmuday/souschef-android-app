package com.samsantech.souschef.ui

import android.util.Patterns
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.samsantech.souschef.ui.components.FormOutlinedTextField
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.ProgressSpinner
import com.samsantech.souschef.ui.components.SuccessDialog
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    onNavigateToSelectCuisines: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val signUpInformation by authViewModel.signUpInformation.collectAsState()
    var signUpStatus by remember {
        mutableStateOf("")
    }
    var errorDisplayName by remember {
        mutableStateOf("")
    }
    var errorUsername by remember {
        mutableStateOf("")
    }
    var errorEmail by remember {
        mutableStateOf("")
    }
    var errorPassword by remember {
        mutableStateOf("")
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
                text = "Create an account",
                color = Color(0xFF16A637),
                fontSize = 25.sp,
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
                value = signUpInformation.displayName,
                onValueChange = { valueChange ->
                    errorDisplayName = ""
                    authViewModel.setSignUpInformation(displayName = valueChange)
                },
                label = "Name",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "person icon"
                    )
                },
            )
            if (errorDisplayName.isNotBlank()) {
                Text(text = errorDisplayName, fontSize = 14.sp, color = Color.Red, modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(8.dp))
            FormOutlinedTextField(
                value = signUpInformation.username,
                onValueChange = { valueChange ->
                    errorUsername = ""
                    authViewModel.setSignUpInformation(username = valueChange)

                    if (valueChange.isNotBlank()) {
                        authViewModel.isUsernameExists(valueChange) {
                            if (it) {
                                errorUsername = "Username is already taken."
                            }
                        }
                    }
                },
                label = "Username",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "person icon"
                    )
                },
            )
            if (errorUsername.isNotBlank()) {
                Text(text = errorUsername, fontSize = 14.sp, color = Color.Red, modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(8.dp))
            FormOutlinedTextField(
                value = signUpInformation.email,
                onValueChange = { valueChange ->
                    errorEmail = ""
                    authViewModel.setSignUpInformation(email = valueChange)

                    if (valueChange.isNotBlank()) {
                        authViewModel.isEmailExists(valueChange) {
                            if (it) {
                                errorUsername = "Email is already taken."
                            }
                        }
                    }
                },
                label = "Email",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "email icon"
                    )
                },
            )
            if (errorEmail.isNotBlank()) {
                Text(text = errorEmail, fontSize = 14.sp, color = Color.Red, modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(8.dp))
            var isPasswordVisualTransformation by remember {
                mutableStateOf(true)
            }
            FormOutlinedTextField(
                isPassword = true,
                value = signUpInformation.password,
                onValueChange = {
                    errorPassword = ""
                    authViewModel.setSignUpInformation(password = it)
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
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                isPasswordVisualTransformation = !isPasswordVisualTransformation
                            }
                    )
                },
                isPasswordVisualTransformation = isPasswordVisualTransformation
            )
            if (errorPassword.isNotBlank()) {
                Text(text = errorPassword, fontSize = 14.sp, color = Color.Red, modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(16.dp))
            ColoredButton(
                onClick = {
                    val displayName = signUpInformation.displayName
                    val username = signUpInformation.username
                    val email = signUpInformation.email
                    val password = signUpInformation.password

                    if (displayName.isEmpty()) {
                        errorDisplayName = "Name is required."
                    }
                    if (username.isEmpty()) {
                        errorUsername = "Username is required."
                    } else {
                        authViewModel.isUsernameExists(username) {
                            if (it) {
                                errorUsername = "Username is already taken."
                            }
                        }
                    }
                    if (email.isNotEmpty()) {
                        authViewModel.isEmailExists(email) {
                            if (it) {
                                errorEmail = "Email is already taken."
                            }
                        }
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        errorEmail = "Email must be valid format."
                    }
                    if (password.length < 8 || !password.contains(Regex("[0-9]"))
                        || !password.contains(Regex("[A-Z]")) || !password.contains(Regex("[^A-Za-z0-9]"))) {
                        errorPassword = "Password must be at least 8 characters long, contain a number, an uppercase letter, and a special character."
                    }

                    if (errorUsername.isEmpty() && errorEmail.isEmpty() && errorPassword.isEmpty()) {
                        signUpStatus = "processing"
                        authViewModel.signUp {
                            signUpStatus = "success"
                        }
                    }
                },
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
                onClick = onNavigateToLogin,
                containerColor = Color.White, contentColor = Green,
                text = "Login",
                border = BorderStroke(1.dp, Color.Black)
            )
        }
    }

    if (signUpStatus == "processing") {
        ProgressSpinner()
    } else if (signUpStatus == "success") {
        SuccessDialog(
            message = "Sign up successful!",
            subMessage = "Your account has been created.",
            buttonName = "Continue",
            onClick = onNavigateToSelectCuisines
        )
    }
}