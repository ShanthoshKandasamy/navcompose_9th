package com.bank.navcompose.ui.components

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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bank.navcompose.R
import com.bank.navcompose.ui.people.PeopleDetailViewModel
import com.bank.navcompose.ui.people.PeopleViewModel
import com.google.accompanist.insets.statusBarsPadding

//TDODO:
@Composable
fun PeopleDetailScreen(peopleId: Int,
                       viewModel: PeopleDetailViewModel,
                       modifier: Modifier = Modifier,
                       pressOnBack: () -> Unit
) {
    val peopleDetailUiState by viewModel.peopleDetail.observeAsState()
    when(peopleDetailUiState){
        is PeopleDetailViewModel.PeopleDetailUiState.Error -> Text("Error")
        PeopleDetailViewModel.PeopleDetailUiState.Loading -> Text("Loading")
        is PeopleDetailViewModel.PeopleDetailUiState.Success -> {
            val people = (peopleDetailUiState as PeopleDetailViewModel.PeopleDetailUiState.Success).people
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

                    people.name?.let {
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

                    people.birth_year?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .constrainAs(content) {
                                    top.linkTo(title.bottom)
                                }
                                .padding(16.dp)
                        )
                    }

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
    }
}