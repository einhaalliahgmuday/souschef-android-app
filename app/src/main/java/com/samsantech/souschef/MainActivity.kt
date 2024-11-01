package com.samsantech.souschef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.samsantech.souschef.ui.ForgotPasswordScreen
import com.samsantech.souschef.ui.LoginScreen
import com.samsantech.souschef.ui.ResetPasswordScreen
import com.samsantech.souschef.ui.HomeScreen
import com.samsantech.souschef.ui.ProfileScreen
import com.samsantech.souschef.ui.components.Header
import com.samsantech.souschef.ui.theme.SousChefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SousChefTheme {
//                OpeningScreen();
//                GetStartedScreen();
//                RegisterOrLoginScreen()
//                SignUpScreen()
//                SelectCuisineScreen()
//                SelectDislikesScreen()
//                SelectSkillLevelScreen()
//                LoginScreen()
//                ForgotPasswordScreen()
//                ResetPasswordScreen()
//                HomeScreen()
                ProfileScreen()
//                Header()
            }
        }
    }
}