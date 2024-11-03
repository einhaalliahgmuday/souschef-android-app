package com.samsantech.souschef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.samsantech.souschef.ui.CreateRecipeScreenOne
import com.samsantech.souschef.ui.CreateRecipeScreenThree
import com.samsantech.souschef.ui.CreateRecipeScreenTwo
import com.samsantech.souschef.ui.OpeningScreen
import com.samsantech.souschef.ui.theme.SousChefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SousChefTheme {
                OpeningScreen();
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
//                ProfileScreen()
//                CategoriesScreen()
//                CreateRecipeScreenOne()
//                CreateRecipeScreenTwo()
//                CreateRecipeScreenThree()
            }
        }
    }
}