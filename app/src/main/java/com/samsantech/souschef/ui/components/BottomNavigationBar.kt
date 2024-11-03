package com.samsantech.souschef.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Yellow

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    val bottomNavigationItems = arrayOf(
        hashMapOf("imageVector" to Icons.Filled.Home),
        hashMapOf("imageVector" to Icons.Filled.AddCircle),
        hashMapOf("imageVector" to Icons.Filled.Search),
        hashMapOf("imageVector" to Icons.Filled.Person),
    )

    BottomNavigation(
        backgroundColor = Green,
        modifier = modifier.height(80.dp).fillMaxWidth()
    ) {
        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                selected = true,
                onClick = { /*TODO*/ },
                selectedContentColor = Yellow,
                unselectedContentColor = Color.White,
                modifier = Modifier.fillMaxSize(),
                icon = {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        item["imageVector"]?.let { Icon(imageVector = it, contentDescription = "home vector", modifier = Modifier.size(30.dp)) }
                    }
                },
            )
        }
    }
}