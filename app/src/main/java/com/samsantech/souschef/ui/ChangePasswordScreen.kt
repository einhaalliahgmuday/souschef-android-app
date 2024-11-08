package com.samsantech.souschef.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.PasswordOutlinedTextField
import com.samsantech.souschef.ui.components.ProgressSpinner
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.viewmodel.AuthViewModel

@Composable
fun ChangePasswordScreen(
    authViewModel: AuthViewModel,
    onNavigateToProfile: () -> Unit
) {
    var oldPassword by remember {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var error by remember {
        mutableStateOf("")
    }
    var isProcessing by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 70.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Change Password",
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

        PasswordOutlinedTextField(
            value = oldPassword,
            onValueChange = {
                error = ""
                oldPassword = it
            },
            label = "Old Password", withLeadingIcon = false
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordOutlinedTextField(
            value = newPassword,
            onValueChange = {
                error = ""
                newPassword = it
            },
            label = "New Password", withLeadingIcon = false
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordOutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                error = ""
                confirmPassword = it
            },
            label = "Confirm New Password", withLeadingIcon = false
        )
        if (error.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = error, fontSize = 14.sp, color = Color.Red, modifier = Modifier.fillMaxWidth())
        }
        Spacer(modifier = Modifier.height(20.dp))
        ColoredButton(
            onClick = {
                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    error = "All fields are required."
                } else if (newPassword.length < 8 || !newPassword.contains(Regex("[0-9]"))
                    || !newPassword.contains(Regex("[A-Z]")) || !newPassword.contains(Regex("[^A-Za-z0-9]"))) {
                    error = "Password must be at least 8 characters long, contain a number, an uppercase letter, and a special character."
                } else if (newPassword != confirmPassword) {
                    error = "Password confirmation do not match."
                }
                if (error.isBlank()) {
                    isProcessing = true
                    authViewModel.changePassword(oldPassword, newPassword) { isSuccess, errorMessage ->
                        isProcessing = false
                        if (isSuccess) {
                            onNavigateToProfile()
                        } else {
                            error = errorMessage ?: "An error occurred. Please try again."
                        }
                    }
                }
            },
            text = "Confirm"
        )
    }

    if (isProcessing) {
        ProgressSpinner()
    }
}