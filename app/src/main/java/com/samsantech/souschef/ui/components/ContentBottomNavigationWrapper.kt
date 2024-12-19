package com.samsantech.souschef.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
fun ContentBottomNavigationWrapper(
    systemNavigationBarHeight: Dp,
    name: String,
    onNavigateToHome: () -> Unit,
    onNavigateToCreateRecipe: () -> Unit,
    onNavigateToSearch: () -> Unit,
    //onNavigateToTiktokVideos: () -> Unit,
    onNavigateToProfile: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold (
        bottomBar = {
            BottomNavigationBar(
                systemNavigationBarHeight,
                name,
                onNavigateToHome,
                onNavigateToCreateRecipe,
                onNavigateToSearch,
                //onNavigateToTiktokVideos,
                onNavigateToProfile
            )
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}