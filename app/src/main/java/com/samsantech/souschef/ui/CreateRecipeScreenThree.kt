package com.samsantech.souschef.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.FormBasicTextField
import com.samsantech.souschef.ui.components.Header
import com.samsantech.souschef.ui.components.ImageButton
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Yellow

@Composable
fun CreateRecipeScreenThree() {
    var instructionsCount = 5

    Column {
        Header()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 20.dp, end = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Instructions", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))

                for (i in 1..instructionsCount) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Step $i")
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = null,
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(22.dp)
                            )
                        }
                    }
                    FormBasicTextField(
                        value = "fndbfndbnfbdnbfndbfndbfndnfdndsandsbabndsbnbdnfbndbfndbfndbfndbfndbnfbdnbfndbfndbfndnfdndsandsbabndsbnbdnfbndbfndbfndbfndbfndbnfbdnbfndbfndbfndnfd",
                        onValueChange = {},
                        maxLines = 5,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ImageButton(onClick = { /*TODO*/ },
                        imageVector = Icons.Filled.Add, containerColor = Yellow, contentColor = Color.Black,
                        modifier = Modifier.fillMaxWidth(.3f))
                }
            }
            Column {
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    ColoredButton(onClick = { /*TODO*/ }, text = "Back", containerColor = Color.White,
                        contentColor = Color.Black, border = BorderStroke(1.dp, Green), modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(12.dp))
                    ColoredButton(onClick = { /*TODO*/ }, text = "Create", containerColor = Green,
                        contentColor = Color.Black, modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}