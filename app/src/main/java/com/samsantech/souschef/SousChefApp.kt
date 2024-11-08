package com.samsantech.souschef

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseUser
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
import com.samsantech.souschef.ui.ChangePasswordScreen
import com.samsantech.souschef.ui.SelectCategoryScreen
import com.samsantech.souschef.ui.SelectCuisinesScreen
import com.samsantech.souschef.ui.SelectDislikesScreen
import com.samsantech.souschef.ui.SelectSkillLevelScreen
import com.samsantech.souschef.ui.SignUpOrLoginScreen
import com.samsantech.souschef.ui.SignUpScreen
import com.samsantech.souschef.viewmodel.AuthViewModel
import com.samsantech.souschef.viewmodel.UserViewModel
import kotlinx.serialization.Serializable

@Composable
fun SousChefApp(
    user: FirebaseUser?,
    activity: ComponentActivity,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel
) {
    Box {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = ChangePassword) {
            composable<Opening> {
                OpeningScreen(
                    onNavigateToGetStarted = { navController.navigate(route = GetStarted) }
                )
            }
            composable<GetStarted> {
                GetStartedScreen(
                    activity,
                    onNavigateToSignUpOrLogin = { navController.navigate(route = SignUpOrLogin) }
                )
            }
            composable<SignUpOrLogin> {
                SignUpOrLoginScreen(
                    onNavigateToLogin = { navController.navigate(route = Login) },
                    onNavigateToSignUp = { navController.navigate(route = SignUp) }
                )
            }
            composable<Login> {
                LoginScreen(
                    authViewModel,
                    onNavigateToSignUp = { navController.navigate(route = SignUp) },
                    onNavigateToForgotPassword = { navController.navigate(route = ForgotPassword) },
                    onNavigateToHome = { navController.navigate(route = Home) }
                )
            }
            composable<ForgotPassword> {
                ForgotPasswordScreen(
                    onNavigateToLogin = { navController.navigate(route = Login) }
                )
            }
            composable<ChangePassword> {
                ChangePasswordScreen(
                    authViewModel,
                    onNavigateToProfile = { navController.navigate(route = Profile) }
                )
            }
            composable<SignUp> {
                SignUpScreen(
                    authViewModel = authViewModel,
                    onNavigateToSelectCuisines = { navController.navigate(route = SelectCuisines) },
                    onNavigateToLogin = { navController.navigate(route = Login) },
                )
            }
            composable<SelectCuisines> {
                SelectCuisinesScreen(
                    activity,
                    authViewModel = authViewModel,
                    onNavigateToSelectDislikes = { navController.navigate(route = SelectDislikes) },
                )
            }
            composable<SelectDislikes> {
                SelectDislikesScreen(
                    authViewModel = authViewModel,
                    onNavigateToSelectCuisines = { navController.navigate(route = SelectCuisines) },
                    onNavigateToSelectSkillLevel = { navController.navigate(route = SelectSkillLevel) },
                )
            }
            composable<SelectSkillLevel> {
                SelectSkillLevelScreen(
                    authViewModel = authViewModel,
                    onNavigateToSelectDislikes = { navController.navigate(route = SelectDislikes) },
                    onNavigateToHome = { navController.navigate(route = Home) },
                )
            }
            composable<EditProfile> {
                EditProfileScreen()
            }
            composable<Home> {
                HomeScreen()
            }
            composable<Profile> {
                if (user != null) {
                    ProfileScreen(userViewModel, user)
                } else {
                    navController.navigate(route = Login)
                }
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
object SelectCuisines

@Serializable
object SelectDislikes

@Serializable
object SelectSkillLevel

@Serializable
object Login

@Serializable
object ForgotPassword

@Serializable
object ChangePassword

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