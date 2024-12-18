package com.samsantech.souschef.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun TiktokVideosScreen() {
    val tiktokEmbedUrls = listOf(
        "https://www.tiktok.com/embed/v2/7128330261154090266?controls=0&progress_bar=1&play_button=1&volume_control=1&fullscreen_button=1&timestamp=0&loop=0&autoplay=0&music_info=1&description=0&rel=0&native_context_menu=1&closed_caption=0",
        "https://www.tiktok.com/embed/v2/6718335390845095173?controls=0&progress_bar=1&play_button=1&volume_control=1&fullscreen_button=1&timestamp=0&loop=0&autoplay=0&music_info=1&description=0&rel=0&native_context_menu=1&closed_caption=0",
    )

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(tiktokEmbedUrls.size) { index ->
            TikTokWebView(embedUrl = tiktokEmbedUrls[index])
        }
    }
}

@Composable
fun TikTokWebView(embedUrl: String) {
    val context = LocalContext.current
    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView,
                        request: WebResourceRequest
                    ): Boolean {
                        val url = request.url.toString()

                        if (url.contains("tiktok.com")) {
                            try {
                                // Check if TikTok app is installed
                                val packageManager = context.packageManager
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

                                val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("snssdk1180://"))
                                val resolveInfo = packageManager.resolveActivity(intentApp, PackageManager.MATCH_DEFAULT_ONLY)

                                if (resolveInfo != null) {
                                    // TikTok app is installed, open in the app
                                    context.startActivity(intentApp)
                                } else {
                                    // TikTok app is not installed, open in browser
                                    context.startActivity(intent)
                                }
                            } catch (e: Exception) {
                                // Handle any unexpected errors
                                Toast.makeText(
                                    context,
                                    "Error opening TikTok link",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(browserIntent)
                            }
                            return true
                        }
                        return false
                    }

                    override fun onPageFinished(view: WebView, url: String) {
                        super.onPageFinished(view, url)
                    }
                }
                loadUrl(embedUrl)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}