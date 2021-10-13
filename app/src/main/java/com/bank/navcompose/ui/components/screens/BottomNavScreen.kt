package com.bank.navcompose.ui.components.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.Radio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bank.navcompose.R
import com.bank.navcompose.ui.components.AppScreen

import com.bank.navcompose.ui.theme.purple200


@Composable
fun BottomNavScreen() {
    val screens = listOf(Screen.DestinationA, Screen.DestinationB, Screen.DestinationC )
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = MaterialTheme.colors.primarySurface,
        topBar = { topAppBar() },
        bottomBar = {
            BottomNavigationBar(navController = navController, items = screens)
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Navigation(navController = navController, modifier = modifier)
    }
}

@Composable
fun topAppBar() {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = purple200,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = "Screens",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

sealed class Screen(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object DestinationA: Screen(route = "destinationA", title = R.string.screen_A, Icons.Filled.Home)
    object DestinationB: Screen(route = "destinationB", title = R.string.screen_B, Icons.Filled.Radio)
    object DestinationC: Screen(route = "destinationC", title = R.string.screen_C, Icons.Filled.LibraryAdd)
}

@Composable
fun BottomNavigationBar(navController: NavHostController, items: List<Screen>){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        backgroundColor = purple200,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(text = stringResource(item.title), color = Color.White) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                       /* launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        restoreState = true*/
                    }
                },
            )
        }
    }
}


@Composable
fun Navigation(navController: NavHostController, modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = Screen.DestinationA.route
    ){
        composable(Screen.DestinationA.route) { DestinationA()}
        composable(Screen.DestinationB.route) { DestinationB()}
        composable(Screen.DestinationC.route) { DestinationC()}
    }
}

// Design Bottom NavBar content
@Composable
fun DestinationA() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "A",
            color = Color.White,
            fontSize = 100.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun DestinationB() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "B",
            color = Color.White,
            fontSize = 100.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}


@Composable
fun DestinationC() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "C",
            color = Color.White,
            fontSize = 100.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}


/*
@Composable
fun Navigation(navController: NavHostController, modifier: Modifier){
    NavHost(navController = navController, startDestination = Screen.DestinationA.route){
        composable(Screen.DestinationA.route) { DestinationA(navController, modifier)}
        composable(Screen.DestinationB.route) { DestinationB()}
        composable(Screen.DestinationC.route) { DestinationC()}
    }
}

val listItems: List<ListItem> = listOf(
    ListItem("Jayme" , 1),
    ListItem("Gil", 2),
    ListItem("Juice WRLD", 3),
    ListItem("Callan", 4),
    ListItem("Braxton", 5),
    ListItem("Kyla", 6),
    ListItem("Lil Mosey", 7),
    ListItem("Allan", 8),
    ListItem("Mike", 9),
    ListItem("Drew", 10),
    ListItem("Nia", 11),
    ListItem("Coi Relay", 12)
)
@Composable
fun DestinationA(navController: NavController, modifier: Modifier) {
    DisplayList(items = listItems, modifier = modifier)
}
*/
