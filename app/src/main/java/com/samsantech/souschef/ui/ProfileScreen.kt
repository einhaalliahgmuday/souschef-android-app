package com.samsantech.souschef.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.samsantech.souschef.ui.components.ColoredButton
import com.samsantech.souschef.ui.components.DisplayProfileImage
import com.samsantech.souschef.ui.components.Header
import com.samsantech.souschef.ui.components.ProgressSpinner
import com.samsantech.souschef.ui.components.UploadImagePopUp
import com.samsantech.souschef.ui.theme.Green
import com.samsantech.souschef.ui.theme.Konkhmer_Sleokcher
import com.samsantech.souschef.utils.convertUriToBitmap
import com.samsantech.souschef.viewmodel.UserViewModel


@Composable
fun ProfileScreen(
    context: Context,
    userViewModel: UserViewModel,
    onNavigateToEditProfile: () -> Unit
) {
    val user by userViewModel.user.collectAsState()

    if (user == null) {
        onNavigateToEditProfile()
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var showProfileImage by remember {
        mutableStateOf(false)
    }
    var showGetImageOptions by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val activityResultLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let {
                imageUri = it.data
            }
        }
    }

    Box {
        Column {
            Header()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(133.dp)
                            .background(Color.LightGray)
                            .clickable { showProfileImage = true },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = "${user?.photoUrl}",
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(130.dp)
                                .background(Color.White)
                        )
                    }
                    IconButton(
                        onClick = {
                            showGetImageOptions = true
                        },
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color(0xFFFFD600))
                            .align(Alignment.BottomEnd)
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                user?.let {
                    Text(
                        text = it.displayName,
                        fontFamily = Konkhmer_Sleokcher,
                        fontSize = 20.sp,
                        color = Color(0xFF16A637),
                        style = LocalTextStyle.current.merge(
                            TextStyle(
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                ),
                            )
                        )
                    )
                }
                user?.let {
                    Text(
                        text = it.username,
                        fontStyle = FontStyle.Italic
                    )
                }
                user?.let {
                    Text(
                        text = it.email
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                ColoredButton(onClick = onNavigateToEditProfile, text = "Settings")
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ColoredButton(
                        onClick = {  },
                        text = "Your Recipes",
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(12.dp, 12.dp),
                        containerColor = Color.White, contentColor = Green,
                        border = BorderStroke(1.dp, Color(0xFF16A637))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    ColoredButton(
                        onClick = {  },
                        text = "Favorites",
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(12.dp, 12.dp),
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    if (showGetImageOptions) {
        UploadImagePopUp(launcher = activityResultLauncher, isClicked = {
            if (it) {
                showGetImageOptions = false
            }
        })
    }

    if (imageUri != null)
    {
        val bitmap: Bitmap? = convertUriToBitmap(context, imageUri!!)

        if (bitmap != null) {
            DisplayProfileImage(
                bitmap = bitmap,
                onCancel = { imageUri = null },
                withCancelButton = true,
                onOkay = {
                    loading = true

                    userViewModel.setProfilePicture(imageUri!!) { _, error ->
                        if (error != null) {
                            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                        }

                        loading = false
                        imageUri = null
                    }
                },
                onOkayText = "Set Profile Photo",
            )
        }
    }

    if (showProfileImage) {
        DisplayProfileImage(
            uri = "${user?.photoUrl}",
            onOkay = {
                showProfileImage = false

                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intent)
            },
            onOkayText = "Update Profile Photo",
            onBoxClick = { showProfileImage = false },
            withCloseButton = true,
            onCloseClick = { showProfileImage = false }
        )
    }

    if (loading) {
        ProgressSpinner()
    }
}