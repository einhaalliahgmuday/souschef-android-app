package com.samsantech.souschef.ui

import android.content.Context
import android.graphics.Bitmap
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

@Composable
fun CreateRecipeScreenOne(
    context: Context
) {
    var categories = arrayOf("Chicken", "Pork", "Beef", "Seafood", "Vegetables", "Dessert", "Drink")
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

    var showDifficultyDropdown by remember {
        mutableStateOf(false)
    }

    val pickImagePortrait = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            portrait = convertUriToBitmap(context, uri)
        }
    }
    val pickImageLandscape = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            landscape = convertUriToBitmap(context, uri)
        }
    }
    val pickImageSquare = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            square = convertUriToBitmap(context, uri)
        }
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
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .width(113.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .clickable {
                                pickImagePortrait.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            },
                        contentAlignment = Alignment.Center
                    ){
                        if (portrait != null) {
                            Image(
                                bitmap = portrait!!.asImageBitmap(),
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Box(
                            modifier = Modifier
                                .height(113.dp)
                                .width(200.dp)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .clickable {
                                    pickImageLandscape.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ){
                            if (landscape != null) {
                                Image(
                                    bitmap = landscape!!.asImageBitmap(),
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
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .clickable {
                                    pickImageSquare.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ){
                            if (square != null) {
                                Image(
                                    bitmap = square!!.asImageBitmap(),
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
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
//                    Text(text = "Title", fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.height(10.dp))
                    FormBasicTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = "What's the title of your recipe?",
                        borderColor = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
//                    Text(text = "Description", fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.height(10.dp))
                    FormBasicTextField(
                        value = "",
                        onValueChange = {},
                        minLines = 3,
                        placeholder = "Tell us something more about this recipe.",
                        borderColor = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                RecipeTime(name = "Cook Time")
                Spacer(modifier = Modifier.height(16.dp))
                RecipeTime(name = "Preparation Time")
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Serving", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    FormBasicTextField(
                        value = "",
                        onValueChange = { /*TODO*/ },
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
                            text = "Easy",
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
                                    onClick = { /*TODO*/ },
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
                        CreateRecipeCard(mealType, onClick = {})
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Category", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    categories.forEach { category ->
                        CreateRecipeCard(category, onClick = {})
                    }
                    ColoredButton(
                        onClick = { /*TODO*/ },
                        text = "Add Category",
                        containerColor = Yellow,
                        contentColor = Color.Black,
                        shadowElevation = 0.dp,
                        fontWeight = FontWeight.Normal
                    )
//                    Text(
//                        text = "+",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(Yellow, RoundedCornerShape(10.dp))
//                            .clickable { }
//                            .padding(10.dp),
//                        textAlign = TextAlign.Center
//                    )
                }


                Spacer(modifier = Modifier.height(30.dp))
                ColoredButton(onClick = { /*TODO*/ }, text = "Next", containerColor = Green, contentColor = Color.White)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        if (false) {
            CreateCategory()
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

@Composable
fun CreateCategory() {
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
                        .clickable { }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            FormBasicTextField(value = "Chicken", onValueChange = { /*TODO*/ }, textAlign = TextAlign.Center)
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
                        .clickable { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Yellow)
                            .padding(30.dp, 5.dp),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeTime(name: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            FormBasicTextField(
                value = "",
                onValueChange = { /*TODO*/ },
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
                placeholder = "hr",
                placeholderAlign = TextAlign.Center,
                borderColor = Color.Gray,
                padding = 8.dp
            )
            Text(text = ":", fontWeight = FontWeight.Bold, modifier = Modifier.width(16.dp), textAlign = TextAlign.Center)
            FormBasicTextField(
                value = "",
                onValueChange = { /*TODO*/ },
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