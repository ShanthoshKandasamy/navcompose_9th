package com.bank.navcompose.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bank.navcompose.R
import com.google.accompanist.insets.statusBarsPadding

/*
@Composable
fun ListDetailScreen(modifier: Modifier, item: ListItem){
    Column(
        modifier = modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
    ) {
        ConstraintLayout {
            val (image, title, content, gifTitle) = createRefs()

            Image(
                painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .aspectRatio(0.85f)
            )

            item.name.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(image.bottom)
                        }
                        .padding(start = 16.dp, top = 12.dp)
                )
            }

            Text(
                text = item.id.toString(),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(title.bottom)
                    }
                    .padding(16.dp)
            )

            Text(
                text = "Gif",
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(gifTitle) {
                        top.linkTo(content.bottom)
                    }
            )
        }
    }
}
*/
