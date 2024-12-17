package com.samsantech.souschef.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.ui.theme.Yellow

@Composable
fun CreateRecipeScreenTwo() {
    var ingredientsCount = 1

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
                Column {
                    Text(text = "Ingredients", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))

                    for (i in 1..ingredientsCount) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
//                            FormBasicTextField(
//                                value = "ndsandsbabndsbnbdnfbndbfndbfndbfndbfndbnfbdnbfndbfndbfndnfd",
//                                maxLines = 3,
//                                onValueChange = {},
//                                modifier = Modifier.weight(1f)
//                            )
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(RoundedCornerShape(50))
                                    .clickable { },
                                tint = Color.Gray,
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(RoundedCornerShape(5.dp))
                                .background(Yellow, RoundedCornerShape(5.dp))
                                .clickable { }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(20.dp),
                                tint = Color.Black,
                            )
                        }

//                        ImageButton(onClick = { /*TODO*/ },
//                            imageVector = Icons.Filled.Add, containerColor = Yellow, contentColor = Color.Black,
//                            modifier = Modifier.fillMaxWidth(.3f))
                    }
                }

                Column {
                    Spacer(modifier = Modifier.height(30.dp))
                    Row {
                        ColoredButton(onClick = { /*TODO*/ }, text = "Back", containerColor = Color.White,
                            contentColor = Color.Black, border = BorderStroke(1.dp, Green), modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(12.dp))
                        ColoredButton(onClick = { /*TODO*/ }, text = "Next", containerColor = Green,
                            contentColor = Color.White, border = BorderStroke(1.dp, Green), modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}