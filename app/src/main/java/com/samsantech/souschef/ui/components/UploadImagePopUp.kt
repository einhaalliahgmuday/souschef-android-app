package com.samsantech.souschef.ui.components

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.samsantech.souschef.R

@Composable
fun UploadImagePopUp(isClicked: (Boolean) -> Unit, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(.4f))
            .clickable {
                isClicked(true)
            }
            .zIndex(1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(bottom = 50.dp, top = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isClicked(true)

                        val intent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        launcher.launch(intent)
                    }
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.images),
                        contentDescription = null,
                        modifier = Modifier
                            .background(Color.Gray.copy(.3f))
                            .padding(10.dp)
                            .size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Upload from Gallery", fontWeight = FontWeight.Bold)
            }
        }
    }
}