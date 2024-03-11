package com.alex.yang.alexunsplashpagingcompose.home.presentation.component

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.alex.yang.alexunsplashpagingcompose.R
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.UnsplashImage
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.Urls
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.User
import com.alex.yang.alexunsplashpagingcompose.home.domain.model.UserLinks
import com.alex.yang.alexunsplashpagingcompose.ui.theme.HeartRed

/**
 * Created by AlexYang on 2024/3/7.
 *
 *
 */
@Composable
fun ListContents(pagingItems: LazyPagingItems<UnsplashImage>) {
    Log.d("TAG", pagingItems.loadState.toString())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id }
        ) { index ->
            pagingItems[index]?.let { UnsplashItem(unsplashImage = it) }
        }
    }
}

@Composable
fun UnsplashItem(unsplashImage: UnsplashImage) {
    val ctx = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(ctx)
            .data(unsplashImage.urls.regular)
            .crossfade(durationMillis = 500)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .build()
    )

    Box(
        modifier = Modifier
            .clickable {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://unsplash.com/@${unsplashImage.user.username}?utm_source=DemoApp&utm_medium=referral")
                )
                ContextCompat.startActivity(ctx, browserIntent, null)
            }
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "Unsplash Image",
            contentScale = ContentScale.Crop
        )

        Surface(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .alpha(ContentAlpha.medium),
            color = Color.Black
        ) {}

        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Photo by ")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Black)
                    ) {
                        append(unsplashImage.user.username)
                    }
                    append(" on Unsplash")
                },
                color = Color.White,
                fontSize = MaterialTheme.typography.caption.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            LikeCounter(
                modifier = Modifier.weight(3f),
                painter = painterResource(id = R.drawable.ic_heart),
                likes = "${unsplashImage.likes}"
            )
        }
    }
}

@Composable
fun LikeCounter(
    modifier: Modifier,
    painter: Painter,
    likes: String
) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painter,
            contentDescription = "Heart Icon",
            tint = HeartRed
        )
        Divider(modifier = Modifier.width(6.dp))
        Text(
            text = likes,
            color = Color.White,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
fun UnsplashImagePreview() {
    UnsplashItem(
        unsplashImage = UnsplashImage(
            id = "1",
            urls = Urls(regular = ""),
            likes = 100,
            user = User(username = "AlexYang", userLinks = UserLinks(html = ""))
        )
    )
}