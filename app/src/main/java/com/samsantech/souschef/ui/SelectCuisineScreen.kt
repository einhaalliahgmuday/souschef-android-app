package com.samsantech.souschef.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.ui.components.FormTextField
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.SelectionCard
import com.samsantech.souschef.ui.components.SkipButton
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher

@Composable
fun SelectCuisineScreen() {
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
                text = "Type of cuisines you're most interested with?",
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
                text = "Filipino",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Korean",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Italian",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "American",
                clickable = {  }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectionCard(
                text = "Others (Please specify)",
                clickable = {  }
            )

            FormTextField(value = "HAHA", onValueChange = {}, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }

        ColoredButton(
            onClick = {  },
            text = "Continue"
        )
    }
}