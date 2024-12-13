package com.samsantech.souschef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import com.samsantech.souschef.firebase.FirebaseAuthManager
import com.samsantech.souschef.firebase.FirebaseRecipeManager
import com.samsantech.souschef.firebase.FirebaseUserManager
import com.samsantech.souschef.ui.theme.SousChefTheme
import com.samsantech.souschef.viewmodel.AuthViewModel
import com.samsantech.souschef.viewmodel.OwnRecipesViewModel
import com.samsantech.souschef.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SousChefTheme {
                val auth = Firebase.auth
                val db = Firebase.firestore
                val storage = Firebase.storage

                val firebaseUserManager = FirebaseUserManager(auth, db, storage)
                val firebaseAuthManager = FirebaseAuthManager(auth, db, firebaseUserManager)
                val firebaseRecipeManager = FirebaseRecipeManager(auth, db, storage)

                val user = auth.currentUser

                val authViewModel = AuthViewModel(firebaseAuthManager)
                val userViewModel = UserViewModel(firebaseAuthManager, firebaseUserManager)
                val ownRecipesViewModel = OwnRecipesViewModel(userViewModel, firebaseRecipeManager)

                SousChefApp(
                    user,
                    activity = this,
                    context = this,
                    authViewModel,
                    userViewModel,
                    ownRecipesViewModel
                )
            }
        }
    }
}