package com.samsantech.souschef

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samsantech.souschef.ui.CreateRecipeScreenOne
import com.samsantech.souschef.ui.CreateRecipeScreenThree
import com.samsantech.souschef.ui.CreateRecipeScreenTwo
import com.samsantech.souschef.ui.EditProfileScreen
import com.samsantech.souschef.ui.ForgotPasswordScreen
import com.samsantech.souschef.ui.GetStartedScreen
import com.samsantech.souschef.ui.HomeScreen
import com.samsantech.souschef.ui.LoginScreen
import com.samsantech.souschef.ui.OpeningScreen
import com.samsantech.souschef.ui.ProfileScreen
import com.samsantech.souschef.ui.RecipeBrowserScreen
import com.samsantech.souschef.ui.RecipeScreen
import com.samsantech.souschef.ui.ResetPasswordScreen
import com.samsantech.souschef.ui.SelectCategoryScreen
import com.samsantech.souschef.ui.SelectCuisineScreen
import com.samsantech.souschef.ui.SelectDislikesScreen
import com.samsantech.souschef.ui.SelectSkillLevelScreen
import com.samsantech.souschef.ui.SignUpOrLoginScreen
import com.samsantech.souschef.ui.SignUpScreen
import kotlinx.serialization.Serializable

@Composable
fun SousChefApp() {
    Box {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Opening) {
            composable<Opening> {
                OpeningScreen()
            }
            composable<GetStarted> {
                GetStartedScreen()
            }
            composable<SignUpOrLogin> {
                SignUpOrLoginScreen()
            }
            composable<SignUp> {
                SignUpScreen()
            }
            composable<SelectCuisine> {
                SelectCuisineScreen()
            }
            composable<SelectDislikes> {
                SelectDislikesScreen()
            }
            composable<SelectSkillLevel> {
                SelectSkillLevelScreen()
            }
            composable<Login> {
                LoginScreen()
            }
            composable<ForgotPassword> {
                ForgotPasswordScreen()
            }
            composable<ResetPassword> {
                ResetPasswordScreen()
            }
            composable<EditProfile> {
                EditProfileScreen()
            }
            composable<Home> {
                HomeScreen()
            }
            composable<Profile> {
                ProfileScreen()
            }
            composable<SelectCategory> {
                SelectCategoryScreen()
            }
            composable<RecipeBrowser> {
                RecipeBrowserScreen()
            }
            composable<Recipe> {
                RecipeScreen()
            }
            composable<CreateRecipeOne> {
                CreateRecipeScreenOne()
            }
            composable<CreateRecipeTwo> {
                CreateRecipeScreenTwo()
            }
            composable<CreateRecipeThree> {
                CreateRecipeScreenThree()
            }
        }
    }
}

@Serializable
object Opening

@Serializable
object GetStarted

@Serializable
object SignUpOrLogin

@Serializable
object SignUp

@Serializable
object SelectCuisine

@Serializable
object SelectDislikes

@Serializable
object SelectSkillLevel

@Serializable
object Login

@Serializable
object ForgotPassword

@Serializable
object ResetPassword

@Serializable
object Profile

@Serializable
object EditProfile

@Serializable
object Home

@Serializable
object SelectCategory

@Serializable
object RecipeBrowser

@Serializable
object Recipe

@Serializable
object CreateRecipeOne

@Serializable
object CreateRecipeTwo

@Serializable
object CreateRecipeThree