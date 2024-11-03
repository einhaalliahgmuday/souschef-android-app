package com.samsantech.souschef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.samsantech.souschef.firebase.FirebaseAuthManager
import com.samsantech.souschef.firebase.FirebaseUserManager
import com.samsantech.souschef.ui.theme.SousChefTheme
import com.samsantech.souschef.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SousChefTheme {
                val db = Firebase.firestore
                val auth = Firebase.auth

                val firebaseUserManager = FirebaseUserManager(db)
                val firebaseAuthManager = FirebaseAuthManager(auth, firebaseUserManager)

                val authViewModel = AuthViewModel(firebaseAuthManager, firebaseUserManager)

                SousChefApp(
                    activity = this,
                    authViewModel
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()


    }
}