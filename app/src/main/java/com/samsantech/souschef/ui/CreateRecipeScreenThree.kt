package com.samsantech.souschef.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.FormBasicTextField
import com.samsantech.souschef.ui.components.Header
import com.samsantech.souschef.ui.components.ImageButton
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.ui.theme.Yellow
import kotlin.math.min

@Composable
fun CreateRecipeScreenThree() {
    var instructionsCount = 3

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
                    Text(text = "Instructions", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))

                    for (i in 1..instructionsCount) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Step $i", fontSize = 14.sp)
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(Color.White)
                                    .clickable { },
                                tint = Color.Gray,
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Box {
                            FormBasicTextField(
                                value = "fndbfndbnfbdnbfndbfndbfndnfdndsandsbabndsbnbdnfbndbfndbfndbfndbfndbnfbdnbfndbfndbfndnfdndsandsbabndsbnbdnfbndbfndbfndbfndbfndbnfbdnbfndbfndbfndnfd",
                                onValueChange = {},
                                maxLines = 5,
                                modifier = Modifier.fillMaxWidth(),
                                borderColor = Color.Gray
                            )
//                            Icon(
//                                imageVector = Icons.Filled.Close,
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .offset(8.dp, (-8).dp)
//                                    .border(1.dp, Color.Gray, RoundedCornerShape(50))
//                                    .padding(2.dp)
//                                    .size(16.dp)
//                                    .background(Color.White)
//                                    .align(Alignment.TopEnd)
//                                    .clickable { },
//                                tint = Color.Gray,
//                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ImageButton(onClick = { /*TODO*/ },
                            imageVector = Icons.Filled.Add, containerColor = Yellow, contentColor = Color.Black,
                            modifier = Modifier
                                .width(80.dp)
                                .size(32.dp), contentPadding = PaddingValues(0.dp)
                        )
                    }
                }
                Column {
                    Spacer(modifier = Modifier.height(30.dp))
                    Row {
                        ColoredButton(
                            onClick = { /*TODO*/ },
                            text = "Back",
                            containerColor = Color.White,
                            contentColor = Color.Black,
                            border = BorderStroke(1.dp, Green),
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        ColoredButton(
                            onClick = { /*TODO*/ },
                            text = "Create",
                            containerColor = Green,
                            contentColor = Color.White,
                            border = BorderStroke(1.dp, Green),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}