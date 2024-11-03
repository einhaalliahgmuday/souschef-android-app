package com.samsantech.souschef.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.FormBasicTextField
import com.samsantech.souschef.ui.components.Header
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.ui.theme.Yellow

@Composable
fun CreateRecipeScreenOne() {
    var categories = arrayOf("Chicken", "Pork", "Beef", "Fish", "Vegetables", "Dessert")

    Box {
        Column {
            Header()
            Column(
                modifier = Modifier
                    .padding(bottom = 50.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Create a Recipe",
                    fontFamily = Konkhmer_Sleokcher,
                    fontSize = 32.sp,
                    color = Green,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Column {
                    Text(text = "Name", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    FormBasicTextField(
                        value = "",
                        onValueChange = {}
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Description", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    FormBasicTextField(
                        value = "",
                        onValueChange = {},
                        minLines = 3
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Category", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    categories.forEach { category ->
                        CreateRecipeCard(category, onClick = {})
                    }
                    ColoredButton(onClick = { /*TODO*/ }, text = "Add Category", containerColor = Yellow, contentColor = Color.Black)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Photo", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Skill Level", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    CreateRecipeCard("Easy", onClick = {})
                    CreateRecipeCard("Medium", onClick = {})
                    CreateRecipeCard("Hard", onClick = {})
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Cook Time", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FormBasicTextField(value = "", onValueChange = { /*TODO*/ },
                            textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                        Text(text = ":", fontWeight = FontWeight.Bold, modifier = Modifier.width(16.dp), textAlign = TextAlign.Center)
                        FormBasicTextField(value = "", onValueChange = { /*TODO*/ },
                            textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ColoredButton(onClick = { /*TODO*/ }, text = "Next", containerColor = Green, contentColor = Color.Black)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(.5f))
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 20.dp, horizontal = 24.dp)
            ) {
                Row(
                    
                ) {
                    Text(text = "Create Category", fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.height(12.dp))
                FormBasicTextField(value = "Chicken", onValueChange = { /*TODO*/ }, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ColoredButton(onClick = { /*TODO*/ }, text = "Add",
                        modifier = Modifier.fillMaxWidth(.5f),
                        containerColor = Green, contentColor = Color.Black)
                }

            }
        }
    }
}

@Composable
fun CreateRecipeCard(name: String, onClick: () -> Unit) {
    Text(
        text = name,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
            .clickable { }
            .padding(10.dp),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(10.dp))
}