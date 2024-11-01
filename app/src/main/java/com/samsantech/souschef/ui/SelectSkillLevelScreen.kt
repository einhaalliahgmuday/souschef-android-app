package com.samsantech.souschef.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsantech.souschef.R
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.SelectionCard
import com.samsantech.souschef.ui.components.SkipButton
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher

@Composable
fun SelectSkillLevelScreen() {
    Box(
        Modifier.fillMaxSize()
    ) {
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
                    text = "How skilled are you in the kitchen?",
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
                    text = "Beginner",
                    clickable = {  }
                )
                Spacer(modifier = Modifier.height(10.dp))
                SelectionCard(
                    text = "Intermediate",
                    clickable = {  }
                )
                Spacer(modifier = Modifier.height(10.dp))
                SelectionCard(
                    text = "Advanced",
                    clickable = {  }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ColoredButton(
                    onClick = {  },
                    text = "Previous",
                    modifier = Modifier.width(160.dp),
                    contentPadding = PaddingValues(12.dp, 12.dp),
                    containerColorName = "white-black",
                    border = BorderStroke(1.dp, Color.Black)
                )
                ColoredButton(
                    onClick = {  },
                    text = "Complete",
                    modifier = Modifier.width(160.dp),
                    contentPadding = PaddingValues(12.dp, 12.dp),
                )
            }
        }
        SignUpSuccessfulPopUp()
    }
}

@Composable
fun SignUpSuccessfulPopUp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(300.dp)
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.successful_vector),
                contentDescription = "successful vector",
                tint = Color(0xFF16A637),
                modifier = Modifier
                    .size(50.dp)
            )
            Text(
                text = "Sign up successful!",
                color = Color(0xFF16A637),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Konkhmer_Sleokcher
            )
            Text(
                text = "Your account has been created."
            )
            Spacer(modifier = Modifier.height(20.dp))
            ColoredButton(onClick = {  }, text = "Sign in")
        }
    }
}