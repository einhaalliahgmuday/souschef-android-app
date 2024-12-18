package com.samsantech.souschef.ui

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.samsantech.souschef.R
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.FormBasicTextField
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.ui.theme.Yellow
import com.samsantech.souschef.utils.convertUriToBitmap
import com.samsantech.souschef.viewmodel.OwnRecipeViewModel

@Composable
fun CreateRecipeScreenOne(
    context: Context,
    ownRecipeViewModel: OwnRecipeViewModel
) {
    val newRecipe by ownRecipeViewModel.newRecipe.collectAsState()

    var categories by remember {
//        mutableStateOf(arrayOf("Chicken", "Pork", "Beef", "Seafood", "Vegetables", "Dessert", "Drink"))
        mutableStateOf(arrayOf("Meat", "Seafood", "Vegetables", "Dessert", "Drink"))
    }
    val mealTypes = arrayOf("Breakfast", "Lunch", "Dinner", "Snack")
    val difficulty = arrayOf("Easy", "Medium", "Hard")
//    val courses = arrayOf("Main", "Side", "Appetizer", "Dessert", "Drink")
    var portrait by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var landscape by remember {
        mutableStateOf<Bitmap?>(null)
    }
    var square by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val pickImagePortrait = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            portrait = convertUriToBitmap(context, uri)
            ownRecipeViewModel.addPhoto("portrait", uri)
        }
    }
    val pickImageLandscape = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            landscape = convertUriToBitmap(context, uri)
            ownRecipeViewModel.addPhoto("landscape", uri)
        }
    }
    val pickImageSquare = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            square = convertUriToBitmap(context, uri)
            ownRecipeViewModel.addPhoto("square", uri)
        }
    }

    var showDifficultyDropdown by remember {
        mutableStateOf(false)
    }
    var showAddCategory by remember {
        mutableStateOf(false)
    }
    var errors by remember {
        mutableStateOf(hashMapOf<String, String>())
    }

    Box(modifier = Modifier.background(Color.White)) {
        Column {
//            Header()
            Text(
                text = "Create Recipe",
                fontFamily = Konkhmer_Sleokcher,
                fontSize = 24.sp,
                color = Green,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = 32.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier
                .background(Color.LightGray)
                .height(1.dp)
                .shadow(1.dp, ambientColor = Color.LightGray, spotColor = Color.LightGray)
                .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    CreateImageContainer(
                        height = 200.dp,
                        width = 113.dp,
                        imageBitmap = portrait,
                        pickImage = pickImagePortrait,
                        onRemoveClick = {
                            portrait = null
                            ownRecipeViewModel.removePhoto("portrait")
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        CreateImageContainer(
                            height = 113.dp,
                            width = 200.dp,
                            imageBitmap = landscape,
                            pickImage = pickImageLandscape,
                            onRemoveClick = {
                                landscape = null
                                ownRecipeViewModel.removePhoto("landscape")
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CreateImageContainer(
                            height = 150.dp,
                            width = 150.dp,
                            imageBitmap = square,
                            pickImage = pickImageSquare,
                            onRemoveClick = {
                                square = null
                                ownRecipeViewModel.removePhoto("square")
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
//                    Text(text = "Title", fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.height(10.dp))
                    FormBasicTextField(
                        value = newRecipe.title,
                        onValueChange = {
                            ownRecipeViewModel.setTitle(it)
                        },
                        placeholder = "What's the title of your recipe?",
                        borderColor = Color.Gray,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
//                    Text(text = "Description", fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.height(10.dp))
                    FormBasicTextField(
                        value = newRecipe.description,
                        onValueChange = {
                            ownRecipeViewModel.setDescription(it)
                        },
                        minLines = 3,
                        placeholder = "Tell us something more about this recipe.",
                        borderColor = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                RecipeTime(
                    name = "Preparation Time",
                    hr = newRecipe.prepTimeHr,
                    onHrChange = {
                        if (it == newRecipe.prepTimeHr) return@RecipeTime

                        println(it)
                        val clearedErrors = HashMap<String, String>(errors.toMap())
                        clearedErrors.remove("prepTimeHr")
                        errors = clearedErrors

                        val isInt = it.toIntOrNull()
                        if (isInt != null || it == "") {
                            ownRecipeViewModel.setPrepTimeHr(it)
                        } else {
                            val newErrors = HashMap<String, String>(errors.toMap())
                            newErrors["prepTimeHr"] = "Can only input numbers."
                            errors = newErrors
                        }
                    },
                    min = newRecipe.prepTimeMin,
                    onMinChange = {
                        ownRecipeViewModel.setPrepTimeMin(it)
                    }
                )
                if (errors["prepTimeHr"] != null && errors["prepTimeHr"] != "") {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = errors["prepTimeHr"]!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                RecipeTime(
                    name = "Cook Time",
                    hr = newRecipe.cookTimeHr,
                    onHrChange = {
                        ownRecipeViewModel.setCookTimeHr(it)
                    },
                    min = newRecipe.cookTimeMin,
                    onMinChange = {
                        ownRecipeViewModel.setCookTimeMin(it)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Serving", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    FormBasicTextField(
                        value = newRecipe.serving,
                        onValueChange = {
                            ownRecipeViewModel.setServing(it)
                        },
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(.29f),
                        borderColor = Color.Gray,
                        padding = 8.dp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Difficulty", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Box {
                        Text(
                            text = if (newRecipe.difficulty != "") newRecipe.difficulty else "Select difficulty",
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                                .padding(10.dp),
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 10.dp)
                                .clickable { showDifficultyDropdown = !showDifficultyDropdown }
                        )
                    }
                    BoxWithConstraints(
                        contentAlignment = Alignment.Center,
                    ) {
                        DropdownMenu(
                            expanded = showDifficultyDropdown,
                            onDismissRequest = { showDifficultyDropdown = false },
                            modifier = Modifier
                                .width(maxWidth)
                                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                                .background(Color.White, RoundedCornerShape(10.dp)),
                            properties = PopupProperties(dismissOnClickOutside = true, focusable = true, dismissOnBackPress = true)
                        ) {
                            difficulty.forEach { difficulty ->
                                DropdownMenuItem(
                                    text = { Text(text = difficulty, fontSize = 16.sp) },
                                    onClick = {
                                        ownRecipeViewModel.setDifficulty(difficulty)
                                        showDifficultyDropdown = false
                                    },
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Meal Type", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    mealTypes.forEach { mealType ->
                        val isSelected = newRecipe.mealTypes?.contains(mealType)

                        CreateRecipeCard(
                            mealType,
                            onClick = {
                                if (isSelected == true) {
                                    ownRecipeViewModel.removeMealType(mealType)
                                } else {
                                    ownRecipeViewModel.addMealType(mealType)
                                }
                            },
                            borderColor = if (isSelected == true) { Green } else Color.Black,
                            backgroundColor = if (isSelected == true) { Green.copy(.2f) } else Color.White,
                            textColor = if (isSelected == true) { Color.Black } else Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Main Category", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    categories.forEach { category ->
                        val isSelected = newRecipe.categories?.contains(category)

                        CreateRecipeCard(
                            category,
                            onClick = {
                                if (isSelected == true) {
                                    ownRecipeViewModel.removeCategory(category)
                                } else {
                                    ownRecipeViewModel.addCategory(category)
                                }
                            },
                            borderColor = if (isSelected == true) { Green } else Color.Black,
                            backgroundColor = if (isSelected == true) { Green.copy(.2f) } else Color.White,
                            textColor = if (isSelected == true) { Color.Black } else Color.Black
                        )
                    }
//                    Icon(
//                        imageVector = Icons.Filled.Add,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(10.dp))
//                            .clickable {
//                                if (categories.size < 9) {
//                                    showAddCategory = true
//                                } else {
//                                    Toast
//                                        .makeText(
//                                            context,
//                                            "You can only add two categories.",
//                                            Toast.LENGTH_LONG
//                                        )
//                                        .show()
//                                }
//                                categories.size
//                            }
//                            .background(Yellow)
//                            .fillMaxWidth()
//                            .padding(30.dp, 10.dp),
//                        tint = Color.Black
//                    )
                }


                Spacer(modifier = Modifier.height(30.dp))
                ColoredButton(onClick = { /*TODO*/ }, text = "Next", containerColor = Green, contentColor = Color.White)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        if (showAddCategory) {
            CreateCategory(
                onCloseClick = { showAddCategory = false },
                onAddCategory = {
                    if (it.trim() != "") {
                        ownRecipeViewModel.addCategory(it)
                        categories = categories.plus(it)
                    }
                    showAddCategory = false
                }
            )
        }
    }
}

@Composable
fun CreateImageContainer(
    height: Dp,
    width: Dp,
    imageBitmap: Bitmap?,
    pickImage: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    onRemoveClick: () -> Unit
) {
    Box {
        Box(
            modifier = Modifier
                .height(height)
                .width(width)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .clickable {
                    pickImage.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
            contentAlignment = Alignment.Center
        ){
            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxSize()
                        .background(Color.White)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.images),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        if (imageBitmap != null) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                modifier = Modifier
                    .offset(7.dp, (-7).dp)
                    .size(16.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(50))
                    .background(Color.White, RoundedCornerShape(50))
                    .padding(2.dp)
                    .align(Alignment.TopEnd)
                    .clickable { onRemoveClick() },
                tint = Color.Gray,
            )
        }
    }
}

@Composable
fun CreateRecipeCard(name: String, onClick: () -> Unit, backgroundColor: Color, borderColor: Color, textColor: Color) {
    Text(
        text = name,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .padding(10.dp),
        textAlign = TextAlign.Center,
        color = textColor
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun CreateCategory(onCloseClick: () -> Unit, onAddCategory: (String) -> Unit) {
    var newCategory by remember {
        mutableStateOf("")
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(.5f))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(maxWidth - 48.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(vertical = 20.dp, horizontal = 24.dp)
        ) {
            Box() {
                Text(text = "Add Category", fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(RoundedCornerShape(50))
                        .clickable { onCloseClick() }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            FormBasicTextField(
                value = newCategory,
                onValueChange = {
                    newCategory = it
                },
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onAddCategory(newCategory) }
                ) {
                    Text(
                        text = "Add",
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Yellow)
                            .padding(30.dp, 5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeTime(name: String, hr: String, onHrChange: (value: String) -> Unit, min: String, onMinChange: (value: String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            FormBasicTextField(
                value = hr,
                onValueChange = {
                    onHrChange(it)
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
                placeholder = "hr",
                placeholderAlign = TextAlign.Center,
                borderColor = Color.Gray,
                padding = 8.dp
            )
            Text(text = ":", fontWeight = FontWeight.Bold, modifier = Modifier.width(16.dp), textAlign = TextAlign.Center)
            FormBasicTextField(
                value = min,
                onValueChange = {
                    onMinChange(it)
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
                placeholder = "min",
                placeholderAlign = TextAlign.Center,
                borderColor = Color.Gray,
                padding = 8.dp
            )
        }
    }
}