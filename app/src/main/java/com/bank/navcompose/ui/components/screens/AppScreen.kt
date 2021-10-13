package com.bank.navcompose.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.Radio
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.bank.navcompose.R
import com.bank.navcompose.ui.theme.purple200
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun AppScreen() {
    val tabs = BottomNavTab.values()
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = MaterialTheme.colors.primarySurface,
        topBar = { appTopBar() },
        bottomBar = {
            appBottomBar(items = tabs.toList(), navController = navController)
        }
    ) {
        innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        MainScreenNavigationConfigurations(navController = navController, modifier = modifier)
    }
}


@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(navController, startDestination = AppScreen.Peoples.route) {
        //TODO: PeopleList, PeopleDetail - pass view model to each of them,
        // in the people detail acquire the id passed from peopleLIst
        composable(AppScreen.Peoples.route) {
            PeoplesScreen(viewModel = hiltViewModel(), selectPeople = {
                navController.navigate("${AppScreen.PeopleDetails.route}/$it")
            }, modifier)
        }
        //TODO: fetch people Id from viewModel with api call as people/id
        // inside PeopleDetail screen viewModel call
        composable(AppScreen.PeopleDetails.routeWithArgument,arguments = listOf(
            navArgument(AppScreen.PeopleDetails.argument0) { type = NavType.IntType }
        )
        ) { backStackEntry ->
            val peopleId =
                backStackEntry.arguments?.getInt(AppScreen.PeopleDetails.argument0) ?: return@composable

            PeopleDetailScreen(peopleId = peopleId, viewModel = hiltViewModel(), modifier) {
                navController.navigateUp()
            }
        }

        composable(AppScreen.Planets.route) {
            //Text("Planets")
            PlanetsScreen(viewModel = hiltViewModel(), selectPlanet = {
                navController.navigate("${AppScreen.PlanetDetails.route}/$it")
            }, modifier)
        }
        composable(AppScreen.PlanetDetails.routeWithArgument,arguments = listOf(
            navArgument(AppScreen.PlanetDetails.argument0) { type = NavType.IntType }
        )
        ) { backStackEntry ->
            val planetId =
                backStackEntry.arguments?.getInt(AppScreen.PlanetDetails.argument0) ?: return@composable

            PlanetDetailScreen(planetId = planetId, viewModel = hiltViewModel()) {
                navController.navigateUp()
            }
        }

        composable(AppScreen.Vehicles.route) {
            Text("vehicles")
        }

    }
}

sealed class AppScreen(val route: String) {
    object Peoples : AppScreen("People")
    object PeopleDetails : AppScreen("PeopleDetails"){
        const val routeWithArgument: String = "PeopleDetails/{peopleId}"
        const val argument0: String = "peopleId"
    }

    object Planets : AppScreen("Planets")
    object PlanetDetails : AppScreen("PlanetDetails"){
        const val routeWithArgument: String = "PlanetDetails/{planetId}"
        const val argument0: String = "planetId"
    }

    object Vehicles : AppScreen("vehicles")
    object VehicleDetails : AppScreen("VehicleDetails")
}

@Preview
@Composable
fun appTopBar() {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = purple200,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun appBottomBar(
    navController: NavHostController,
    items: List<BottomNavTab>
) {
    var selectedIndex by remember { mutableStateOf(0) }
    BottomNavigation(
        backgroundColor = purple200,
        modifier = Modifier
            .navigationBarsHeight(56.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(text = stringResource(item.title), color = Color.White) },
                selected = index == selectedIndex,
                onClick = {
                    if (index != selectedIndex){
                        when(index){
                            0 -> navController.navigate(AppScreen.Peoples.route)
                            1 -> navController.navigate(AppScreen.Planets.route)
                            2 -> navController.navigate(AppScreen.Vehicles.route)
                        }
                    }
                    selectedIndex = index

                },
                selectedContentColor = LocalContentColor.current,
                unselectedContentColor = LocalContentColor.current,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}

enum class BottomNavTab(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(R.string.menu_home, Icons.Filled.Home),
    RADIO(R.string.menu_radio, Icons.Filled.Radio),
    LIBRARY(R.string.menu_library, Icons.Filled.LibraryAdd);

    companion object {
        fun getTabFromResource(@StringRes resource: Int): BottomNavTab {
            return when (resource) {
                R.string.menu_radio -> RADIO
                R.string.menu_library -> LIBRARY
                else -> HOME
            }
        }
    }
}