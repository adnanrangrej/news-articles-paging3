package com.github.adnanrangrej.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.github.adnanrangrej.presentation.R

@Composable
fun NewsImage(
    modifier: Modifier = Modifier,
    url: Any?,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: @Composable () -> Unit = { CircularProgressIndicator(modifier = modifier) },
    error: @Composable () -> Unit = {
        Image(
            painter = painterResource(R.drawable.ic_broken_image),
            contentDescription = "Image loading error or Image does not exist",
            modifier = modifier
        )
    },
    contentDescription: String?
) {

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url)
            .crossfade(true)
            .build()
    )
    val state by painter.state.collectAsState()


    when (state) {
        AsyncImagePainter.State.Empty -> {
            error()
        }

        is AsyncImagePainter.State.Error -> {
            error()
        }

        is AsyncImagePainter.State.Loading -> {
            placeholder()
        }

        is AsyncImagePainter.State.Success -> {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier
            )
        }
    }
}

