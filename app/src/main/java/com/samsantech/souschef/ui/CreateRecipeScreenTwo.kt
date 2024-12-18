package com.samsantech.souschef.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.CreateRecipeBottomButtons
import com.samsantech.souschef.ui.components.ErrorText
import com.samsantech.souschef.ui.components.FormBasicTextField
import com.samsantech.souschef.ui.components.ImageButton
import com.samsantech.souschef.ui.components.OwnRecipeHeader
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.ui.theme.Yellow
import com.samsantech.souschef.viewmodel.OwnRecipeViewModel

@Composable
fun CreateRecipeScreenTwo(
    ownRecipeViewModel: OwnRecipeViewModel,
    onNavigateToCreateRecipeOne: () -> Unit,
    onNavigateToCreateRecipeThree: () -> Unit,
    closeCreateRecipe: () -> Unit
) {
    val newRecipe by ownRecipeViewModel.newRecipe.collectAsState()

    var error by remember {
        mutableStateOf("")
    }

    BoxWithConstraints(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {
        val maxHeight = maxHeight
        Column {
            OwnRecipeHeader(closeCreateRecipe)
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .defaultMinSize(Dp.Unspecified, maxHeight)
                    .verticalScroll(rememberScrollState())
            ) {
                Column {
                    Column {
                        Text(text = "Ingredients", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))

                        newRecipe.ingredients.forEachIndexed { index, ingredient ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                FormBasicTextField(
                                    value = ingredient,
                                    maxLines = 3,
                                    onValueChange = {
                                        error = ""
                                        ownRecipeViewModel.updateIngredients(index, it)
                                    },
                                    modifier = Modifier.weight(1f),
                                    borderColor = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clip(RoundedCornerShape(50))
                                        .clickable {
                                            error = ""
                                            ownRecipeViewModel.removeIngredient(index)
                                        },
                                    tint = Color.Gray,
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        if (error.isNotEmpty()) {
                            ErrorText(text = error, textAlign = TextAlign.Center)
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ImageButton(
                                onClick = {
                                    ownRecipeViewModel.addIngredient()
                                },
                                imageVector = Icons.Filled.Add,
                                containerColor = Yellow,
                                contentColor = Color.Black,
                                modifier = Modifier
                                    .width(80.dp)
                                    .size(32.dp),
                                contentPadding = PaddingValues(0.dp)
                            )
                        }
                    }
                }

                CreateRecipeBottomButtons(
                    firstButtonText = "Back",
                    onFirstButtonClick = onNavigateToCreateRecipeOne,
                    secondButtonText = "Next",
                    onSecondButtonClick = {
                        val newIngredients = newRecipe.ingredients.toMutableList()
                        newIngredients.removeAll { it.trim().isBlank() }

                        if(newIngredients.size == 0) {
                            error = "At least one ingredient is required."
                        } else {
                            ownRecipeViewModel.setIngredients(newIngredients)
                            onNavigateToCreateRecipeThree()
                        }
                    }
                )
            }
        }
    }
}