package com.samsantech.souschef.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.components.LongButton
import com.samsantech.souschef.ui.components.SelectionCard
import com.samsantech.souschef.ui.components.SkipButton
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher

@Composable
fun SelectDislikesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 70.dp, start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SkipButton(onClick = {})

            Text(
                text = "Do you have any allergies or dislikes?",
                color = Color(0xFF16A637),
                fontSize = 20.sp,
                fontFamily = Konkhmer_Sleokcher,
            )
            Text(
                text = "This will help us curate more recipe experience for you.",
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(12.dp))

            SelectionCard(
                text = "Shrimp",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Garlic",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Pepper",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Chili",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Milk",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Cheese",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Ketchup",
                clickable = {  }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LongButton(
                onClick = {  },
                text = "Previous",
                modifier = Modifier.width(160.dp),
                contentPadding = PaddingValues(12.dp, 12.dp),
                containerColorName = "white-black",
                border = BorderStroke(1.dp, Color.Black)
            )
            LongButton(
                onClick = {  },
                text = "Next Step",
                modifier = Modifier.width(160.dp),
                contentPadding = PaddingValues(12.dp, 12.dp),
            )
        }
    }
}