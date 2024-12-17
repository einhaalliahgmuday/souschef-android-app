package com.samsantech.souschef

import android.content.Context
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
import com.samsantech.souschef.ui.ResetPasswordScreen
import com.samsantech.souschef.ui.GetStartedScreen
import com.samsantech.souschef.ui.HomeScreen
import com.samsantech.souschef.ui.LoginScreen
import com.samsantech.souschef.ui.OpeningScreen
import com.samsantech.souschef.ui.ProfileScreen
import com.samsantech.souschef.ui.RecipeBrowserScreen
import com.samsantech.souschef.ui.RecipeScreen
import com.samsantech.souschef.ui.ChangePasswordScreen
import com.samsantech.souschef.ui.CreateRecipeScreenFour
import com.samsantech.souschef.ui.SelectCategoryScreen
import com.samsantech.souschef.ui.SelectCuisinesScreen
import com.samsantech.souschef.ui.SelectDislikesScreen
import com.samsantech.souschef.ui.SelectSkillLevelScreen
import com.samsantech.souschef.ui.SignUpOrLoginScreen
import com.samsantech.souschef.ui.SignUpScreen
import com.samsantech.souschef.ui.UpdateEmailScreen
import com.samsantech.souschef.ui.VerifyEmailScreen
import com.samsantech.souschef.ui.components.ContentBottomNavigationWrapper
import com.samsantech.souschef.viewmodel.AuthViewModel
import com.samsantech.souschef.viewmodel.OwnRecipeViewModel
import com.samsantech.souschef.viewmodel.UserViewModel
import kotlinx.serialization.Serializable

@Composable
fun SousChefApp(
    user: FirebaseUser?,
    activity: ComponentActivity,
    context: Context,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
    ownRecipeViewModel: OwnRecipeViewModel
) {
    Box {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = CreateRecipeOne) {
            composable<Opening> {
                var afterOpening: Any = GetStarted
                if (user != null) {
                    afterOpening = Profile
                }

                OpeningScreen(
                    onNavigateTo = { navController.navigate(route = afterOpening) }
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
                    userViewModel,
                    onNavigateToSignUp = { navController.navigate(route = SignUp) },
                    onNavigateToVerifyEmail = { navController.navigate(route = VerifyEmail) },
                    onNavigateToForgotPassword = { navController.navigate(route = ResetPassword) },
                    onNavigateToHome = {
                        navController.navigate(route = Profile) {
                            popUpTo(Profile) { inclusive = true }
                        }
                    },
                    onNavigateToSelectCuisines = {
                        navController.navigate(route = SelectCuisines) {
                            popUpTo(SelectCuisines) { inclusive = true }
                        }
                    }
                )
            }
            composable<VerifyEmail> {
                VerifyEmailScreen(
                    userViewModel,
                    authViewModel,
                    onNavigateToLogin = { navController.navigate(route = Login) }
                )
            }
            composable<ResetPassword> {
                ResetPasswordScreen(
                    authViewModel,
                    onNavigateToLogin = { navController.navigate(route = Login) }
                )
            }
            composable<ChangePassword> {
                ChangePasswordScreen(
                    authViewModel,
                    onNavigateToEditProfile = {
                        navController.navigate(route = EditProfile) {
                            popUpTo(EditProfile) { inclusive = true }
                        }
                    }
                )
            }
            composable<SignUp> {
                SignUpScreen(
                    authViewModel = authViewModel,
                    userViewModel,
                    onNavigateToVerifyEmail = {
                        navController.navigate(route = VerifyEmail) {
                            popUpTo(VerifyEmail) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = { navController.navigate(route = Login) },
                )
            }
            composable<SelectCuisines> {
                SelectCuisinesScreen(
                    activity,
                    userViewModel = userViewModel,
                    onNavigateToSelectDislikes = { navController.navigate(route = SelectDislikes) },
                )
            }
            composable<SelectDislikes> {
                SelectDislikesScreen(
                    userViewModel = userViewModel,
                    onNavigateToSelectCuisines = { navController.navigate(route = SelectCuisines) },
                    onNavigateToSelectSkillLevel = { navController.navigate(route = SelectSkillLevel) },
                )
            }
            composable<SelectSkillLevel> {
                SelectSkillLevelScreen(
                    userViewModel = userViewModel,
                    onNavigateToSelectDislikes = { navController.navigate(route = SelectDislikes) },
                    onNavigateToHome = {
                        navController.navigate(route = Profile) {
                            popUpTo(Profile) { inclusive = true }
                        }
                    },
                )
            }
            composable<EditProfile> {
                EditProfileScreen(
                    context,
                    authViewModel = authViewModel,
                    userViewModel = userViewModel,
                    onNavigateToProfile = {
                        navController.navigate(route = Profile) {
                            popUpTo(Profile) { inclusive = true }
                        }
                    },
                    onNavigateToUpdateEmail = { navController.navigate(route = UpdateEmail)},
                    onNavigateToChangePassword = { navController.navigate(route = ChangePassword) },
                    onNavigateToLogin = {
                        navController.navigate(route = Login) {
                            popUpTo(Login) { inclusive = true }
                        }
                    }
                )
            }
            composable<UpdateEmail> {
                UpdateEmailScreen(
                    authViewModel,
                    userViewModel,
                    onNavigateToLogin = {
                        navController.navigate(route = Login) {
                            popUpTo(Login) { inclusive = true }
                        }
                    }
                )
            }
            composable<Home> {
                ContentBottomNavigationWrapper(
                    name = "Home",
                    onNavigateToHome = {
                        navController.navigate(route = Home)
                    },
                    onNavigateToCreateRecipe = {
                        navController.navigate(route = CreateRecipeOne)
                    },
                    onNavigateToSearch = {
                        navController.navigate(route = Profile)
                    },
                    onNavigateToProfile = {
                        navController.navigate(route = Profile) {
                            popUpTo(Profile) { inclusive = true }
                        }
                    },
                ) { paddingValues ->
                    HomeScreen(paddingValues)
                }
            }
            composable<Profile> {
                ContentBottomNavigationWrapper(
                    name = "Profile",
                    onNavigateToHome = {
                        navController.navigate(route = Home)
                    },
                    onNavigateToCreateRecipe = {
                        navController.navigate(route = CreateRecipeOne)
                    },
                    onNavigateToSearch = {
                        navController.navigate(route = Profile)
                    },
                    onNavigateToProfile = {
                        navController.navigate(route = Profile) {
                            popUpTo(Profile) { inclusive = true }
                        }
                    },
                ) { paddingValues ->
                    ProfileScreen(
                        context,
                        userViewModel,
                        onNavigateToEditProfile = { navController.navigate(route = EditProfile) }
                    )
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
                CreateRecipeScreenOne(
                    context,
                    ownRecipeViewModel
                )
            }
            composable<CreateRecipeTwo> {
                CreateRecipeScreenTwo()
            }
            composable<CreateRecipeThree> {
                CreateRecipeScreenThree()
            }
            composable<CreateRecipeFour> {
                CreateRecipeScreenFour()
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
object VerifyEmail

@Serializable
object ResetPassword

@Serializable
object ChangePassword

@Serializable
object Profile

@Serializable
object EditProfile

@Serializable
object UpdateEmail

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

@Serializable
object CreateRecipeFour