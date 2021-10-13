package com.bank.navcompose.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.bank.navcompose.ui.planets.PlanetsViewModel

@Composable
fun PlanetDetailScreen(planetId: Int, viewModel: PlanetsViewModel,
                       pressOnBack: () -> Unit) {

    Text("Detail screen ".plus(planetId), color = Color.White)

}