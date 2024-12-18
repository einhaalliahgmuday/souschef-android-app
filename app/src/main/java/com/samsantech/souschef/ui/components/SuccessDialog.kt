package com.samsantech.souschef.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.samsantech.souschef.R
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher

@Composable
fun SuccessDialog(
    message: String,
    subMessage: String?,
    buttonName: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ){
        Box {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .zIndex(2f)
                    .offset((-8).dp, 8.dp)
                    .clickable { onClick() }
            )
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
                    contentDescription = null,
                    tint = Color(0xFF16A637),
                    modifier = Modifier
                        .size(50.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = message,
                    color = Color(0xFF16A637),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Konkhmer_Sleokcher,
                    textAlign = TextAlign.Center
                )
                if (subMessage != null) {
                    Text(
                        text = subMessage,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
//            Spacer(modifier = Modifier.height(20.dp))
//            ColoredButton(onClick = onClick, text = buttonName)
            }
        }
    }
}