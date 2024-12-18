package com.samsantech.souschef.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateRecipeScreenFour() {
    var tags = arrayOf("filipino", "korean", "american", "vegan", "vegetarian", "gluten-free",
        "low-carb", "salty", "sweet", "spicy", "savory", "sour", "smoky", "baked",
        "grilled", "fried", "roasted", "fermented", "sauteed", "smoothie")
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
            Spacer(
                modifier = Modifier
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
                Column {
                    Text(text = "Tags", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        tags.forEach {
                            Text(
                                text = it,
                                modifier = Modifier
                                    .border(1.dp, Green, RoundedCornerShape(10.dp))
                                    .background(Green.copy(.3f), RoundedCornerShape(10.dp))
                                    .padding(10.dp, 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}