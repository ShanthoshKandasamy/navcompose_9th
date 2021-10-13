package com.bank.navcompose.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bank.navcompose.R
import com.bank.navcompose.data.model.People
import com.bank.navcompose.ui.components.library.StaggeredVerticalGrid
import com.bank.navcompose.ui.extensions.visible
import com.bank.navcompose.ui.people.PeopleViewModel
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun PeoplesScreen(
    viewModel:PeopleViewModel,
    selectPeople: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val peopleUiState by viewModel.people.observeAsState()
    val isLoading: Boolean by viewModel.isLoading
    when(peopleUiState){
        is PeopleViewModel.PeopleUiState.Error -> {
            Text("Error")
        }
        PeopleViewModel.PeopleUiState.Loading -> {
            Text(text = "Loading")
        }
        is PeopleViewModel.PeopleUiState.Success -> {
            Column(
                modifier = modifier
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colors.background)
            ) {
                StaggeredVerticalGrid(
                    maxColumnWidth = 220.dp,
                    modifier = Modifier.padding(4.dp)
                ) {
                    (peopleUiState as PeopleViewModel.PeopleUiState.Success).peoples.forEachIndexed { index, people ->
                        PeopleCard(people = people, selectPeople = selectPeople, id = index +1)
                    }
                }
            }
        }
    }

    ConstraintLayout {
        val (progress) = createRefs()
        CircularProgressIndicator(
            modifier = Modifier
                .constrainAs(progress) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .visible(isLoading),
            color = Color.Red
        )
    }

}

@Composable
private fun PeopleCard(
    people: People,
    selectPeople: (Int) -> Unit,
    id: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = {
                    //TODO: Navcontroller navrgs as Id to detail screen
                    //navcontroller.navigate(id)
                    selectPeople(id)

                }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, title, content) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Image",
                modifier = Modifier
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
                    .aspectRatio(0.8f)
            )
            people.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .constrainAs(title) {
                            centerHorizontallyTo(parent)
                            top.linkTo(image.bottom)
                        }
                        .padding(8.dp)
                )
            }
            people.birth_year?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .constrainAs(content) {
                            centerHorizontallyTo(parent)
                            top.linkTo(title.bottom)
                        }
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 12.dp)
                )
            }
        }
    }
}
