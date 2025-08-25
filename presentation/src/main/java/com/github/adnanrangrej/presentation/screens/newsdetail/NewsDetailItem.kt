package com.github.adnanrangrej.presentation.screens.newsdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.adnanrangrej.domain.model.Article
import com.github.adnanrangrej.presentation.component.NewsImage
import com.github.adnanrangrej.presentation.screens.newslist.formatDate

@Composable
fun NewsDetailItem(
    modifier: Modifier = Modifier,
    article: Article
) {

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        // Title
        Text(
            text = article.title,
            style = typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))


        // Source
        Text(
            text = article.sourceName,
            style = typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(8.dp))

        // Timestamp
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = "Published At",
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = formatDate(article.publishedAt),
                style = typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.height(8.dp))

        // Image
        NewsImage(
            url = article.imageUrl,
            contentDescription = article.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
        )
        Spacer(Modifier.height(8.dp))

        // Description
        Text(
            text = article.description,
            style = typography.titleSmall
        )
        Spacer(Modifier.height(8.dp))

        // Content
        val cleanHtml = article.content
            .replace("\\r\\n", "<br>")
            .substringBeforeLast("[+")
        Text(
            text = AnnotatedString.fromHtml(cleanHtml),
            style = typography.bodyMedium
        )
        Spacer(Modifier.height(8.dp))

    }

}

@Preview(showBackground = true)
@Composable
private fun NewsDetailItemPreviews() {
    MaterialTheme {

        NewsDetailItem(
            modifier = Modifier.fillMaxSize(),
            article = Article(
                publishedAt = "2025-08-01T16:51:29Z",
                author = "Sean Hollister",
                content = "<ul><li></li><li></li><li></li></ul>\\r\\nGoogle is asking the court for an emergency stay following Epics big win.\\r\\nGoogle is asking the court for an emergency stay following Epics big win.\\r\\nby\\r\\nSean Ho… [+8078 chars]",
                description = "Yesterday, when Epic won its Google antitrust lawsuit for a second time, it wasn’t quite clear how soon Google would need to start dismantling its affirmed illegal monopoly. Today, Google admits the answer is: 14 days. Google has just 14 days to enact major c…",
                sourceName = "The Verge",
                title = "Google has just two weeks to begin cracking open Android, it admits in emergency filing",
                url = "https://www.theverge.com/news/717440/google-epic-open-play-store-emergency-stay",
                imageUrl = "https://platform.theverge.com/wp-content/uploads/sites/2/2025/08/STKS487_Antitrust_STK093_Google_A.jpg?quality=90&strip=all&crop=0%2C10.732984293194%2C100%2C78.534031413613&w=1200"
            )
        )
    }
}