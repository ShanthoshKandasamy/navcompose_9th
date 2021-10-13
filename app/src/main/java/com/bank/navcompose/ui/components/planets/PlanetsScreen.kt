package com.bank.navcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bank.navcompose.R
import com.bank.navcompose.data.model.Planets
import com.bank.navcompose.ui.planets.PlanetsViewModel
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun PlanetsScreen(
    viewModel: PlanetsViewModel
    /*planets: List<Planets>*/,
    selectPlanet: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val planetUIState by viewModel.planets.observeAsState()
    when(planetUIState){
        is PlanetsViewModel.PlanetsUiState.Error -> Text("Error")
        PlanetsViewModel.PlanetsUiState.Loading -> Text("Loading")
        is PlanetsViewModel.PlanetsUiState.Success -> {
            val planets = (planetUIState as PlanetsViewModel.PlanetsUiState.Success).planets
            Column(
                modifier = modifier
                    .statusBarsPadding()
                    .background(MaterialTheme.colors.background)
            ) {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(
                        items = planets,
                        itemContent = { planet ->
                            PlanetListItem(
                                planets = planet,
                                selectPlanet = selectPlanet,
                                id = planets.indexOf(planet)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun PlanetListItem(
    planets: Planets,
    selectPlanet: (Int) -> Unit,
    id: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(
                onClick = { selectPlanet(id) }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(8.dp)
        ) {
            val (image, title, content) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Image",
                modifier = Modifier
                    .constrainAs(image) {
                        centerVerticallyTo(parent)
                        end.linkTo(title.start)
                    }
                    .height(64.dp)
                    .aspectRatio(1f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(4.dp)),

            )
            planets.name?.let {
                Text(
                    it,
                    Modifier
                        .constrainAs(title) {
                            start.linkTo(image.end)
                        }
                        .padding(horizontal = 12.dp),
                    Color.Unspecified, TextUnit.Unspecified, null, null, null, TextUnit.Unspecified, null, null,
                    TextUnit.Unspecified, TextOverflow.Ellipsis,
                    true, 1,
                    {}, MaterialTheme.typography.h2
                )
            }
            planets.population?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .constrainAs(content) {
                            start.linkTo(image.end)
                            top.linkTo(title.bottom)
                        }
                        .padding(start = 12.dp, top = 4.dp)
                )
            }
        }
    }
}