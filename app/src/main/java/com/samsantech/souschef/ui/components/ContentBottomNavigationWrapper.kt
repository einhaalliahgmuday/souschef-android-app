package com.samsantech.souschef.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun ContentBottomNavigationWrapper(
    name: String,
    onNavigateToHome: () -> Unit,
    onNavigateToCreateRecipe: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToProfile: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold (
        bottomBar = {
            BottomNavigationBar(
                name = name,
                onNavigateToHome,
                onNavigateToCreateRecipe,
                onNavigateToSearch,
                onNavigateToProfile
            )
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}