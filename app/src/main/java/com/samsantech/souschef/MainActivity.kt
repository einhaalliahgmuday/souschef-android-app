package com.samsantech.souschef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.samsantech.souschef.ui.GetStartedScreen
import com.samsantech.souschef.ui.Login
import com.samsantech.souschef.ui.OpeningScreen
import com.samsantech.souschef.ui.RegisterOrLoginScreen
import com.samsantech.souschef.ui.SelectCuisineScreen
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
//                Login()
//                SelectCuisineScreen()
            }
        }
    }
}