package com.samsantech.souschef.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.components.CreateRecipeBottomButtons
import com.samsantech.souschef.ui.components.FormBasicTextField
import com.samsantech.souschef.ui.components.OwnRecipeHeader
import com.samsantech.souschef.ui.components.ProgressSpinner
import com.samsantech.souschef.ui.components.SuccessDialog
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Yellow
import com.samsantech.souschef.viewmodel.OwnRecipesViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateRecipeScreenFour(
    context: Context,
    ownRecipesViewModel: OwnRecipesViewModel,
    onNavigateToCreateRecipeThree: () -> Unit,
    closeCreateRecipe: () -> Unit
) {
    val newRecipe by ownRecipesViewModel.newRecipe.collectAsState()
    var suggestedTags by remember {
        mutableStateOf(
            listOf("filipino", "korean", "american", "vegan", "vegetarian", "gluten-free",
            "low-carb", "salty", "sweet", "spicy", "savory", "sour", "smoky", "baked",
            "grilled", "fried", "roasted", "fermented", "sauteed", "smoothie")
        )
    }
    var newTag by remember {
        mutableStateOf("")
    }
    var loading by remember {
        mutableStateOf(false)
    }
    var success by remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
    ) {
        val maxHeight = maxHeight
        Column {
            OwnRecipeHeader(closeCreateRecipe = closeCreateRecipe)
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .defaultMinSize(Dp.Unspecified, maxHeight)
                    .verticalScroll(rememberScrollState())
            ) {
                Column {
                    Text(text = "Tags", fontWeight = FontWeight.Bold)
                    Text(text = "You may select up to 7 tags.", fontSize = 14.sp, fontStyle = FontStyle.Italic)
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FormBasicTextField(
                            value = newTag,
                            onValueChange = {
                                newTag = it
                            },
                            padding = 10.dp,
                            modifier = Modifier.weight(1f),
                            borderColor = Green
                        )
                        Text(
                            text = "+ Add",
                            modifier = Modifier
                                .clickable {
                                    if (newTag.isEmpty()) return@clickable

                                    val updatedSuggestedTags = suggestedTags.toMutableList()
                                    updatedSuggestedTags.add(newTag)

                                    ownRecipesViewModel.addTag(newTag)
                                    suggestedTags = updatedSuggestedTags
                                }
                                .clip(RoundedCornerShape(8.dp))
                                .background(Yellow)
                                .padding(12.dp, 5.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        val tags = (suggestedTags.toMutableList() + newRecipe.tags).distinct()

                        tags.forEach {
                            val isSelected = newRecipe.tags.contains(it)
                            val borderColor = if (isSelected) Green else Color.Black
                            val backgroundColor = if (isSelected) Green.copy(.3f) else Color.White

                            Text(
                                text = it,
                                modifier = Modifier
                                    .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                                    .background(backgroundColor, RoundedCornerShape(10.dp))
                                    .clickable {
                                        if (isSelected) ownRecipesViewModel.removeTag(it) else ownRecipesViewModel.addTag(
                                            it
                                        )
                                    }
                                    .padding(10.dp, 5.dp)
                            )
                        }
                    }
                }

                CreateRecipeBottomButtons(
                    firstButtonText = "Back",
                    onFirstButtonClick = onNavigateToCreateRecipeThree,
                    secondButtonText = "Create",
                    onSecondButtonClick = {
                        loading = true
                        ownRecipesViewModel.uploadRecipe { isSuccess, error ->
                            loading = false

                            if (!isSuccess && error != null) {
                                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                            } else {
                                ownRecipesViewModel.resetNewRecipe()
                                closeCreateRecipe()
                                success = true
                            }
                        }
                    }
                )
            }
        }

        if (loading) {
            ProgressSpinner()
        }

        if (success) {
            SuccessDialog(
                message = "Recipe uploaded successfully!",
                subMessage = null,
                buttonName = "OK",
                onClick = {
                    success = false
                }
            )
        }
    }
}