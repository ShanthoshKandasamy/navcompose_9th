package com.bank.navcompose.ui.components.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.bank.navcompose.R
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun AppListScreenNavigator(){
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        AppNavigation(navController = navController)
    }

}
// original
internal sealed class Screen(val route: String) {
    object User : Screen("userroot")
    object Summary : Screen("summaryroot")
}

private sealed class LeafScreen(val route: String) {
    object Users : Screen("Users")
    object UserDetails : Screen("UserDetails") {
        const val routeWithArgument: String = "UserDetails/?itemId={itemId}"
        const val argument0: String = "itemId"
    }
}


@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.User.route){
        addUserGraph(navController = navController)
        //addSummaryGraph(navController = navController)
    }
}

fun NavGraphBuilder.addUserGraph(navController: NavController) {
    val uri = "screens://UserDetails/{itemId}" 
    navigation(startDestination = LeafScreen.Users.route, route = Screen.User.route) {
        composable(LeafScreen.Users.route) {
            UserScreen(items = listItems, onClick = {
                navController.navigate("${LeafScreen.UserDetails.route}/$it")
            })
        }
        composable(
            LeafScreen.UserDetails.routeWithArgument,
            arguments = listOf(navArgument(LeafScreen.UserDetails.argument0) {
                type = NavType.IntType,
                defaultValue = 5
            }),
            deepLinks = listOf(navDeepLink { uriPattern = uri })
        ) { backStackEntry ->
            val itemId =
                backStackEntry.arguments?.getInt(LeafScreen.UserDetails.argument0) ?: return@composable
            UserDetailScreen(itemId, onBackPressed = {
                navController.popBackStack()
            })
        }
    }
}

// only args
/*sealed class Screen(val route: String) {
    object User : Screen("User")
    object UserDetails : Screen("UserDetails") {
        const val routeWithArgument: String = "UserDetails/{itemId}"
        const val argument0: String = "itemId"
    }
}


@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.User.route){
        composable(Screen.User.route) {
            UserScreen(items = listItems, onClick = {
                navController.navigate("${Screen.UserDetails.route}/$it")
            })
        }
        composable(
            Screen.UserDetails.routeWithArgument,
            arguments = listOf(navArgument(Screen.UserDetails.argument0) { type = NavType.IntType }),
        ) { backStackEntry ->
            val itemId =
                backStackEntry.arguments?.getInt(Screen.UserDetails.argument0) ?: return@composable
            UserDetailScreen(itemId, onBackPressed = {
                navController.popBackStack()
            })
        }
    }
}*/


// very simple
/*sealed class Screen(val route: String) {
    object User : Screen("User")
    object UserDetails : Screen("UserDetails")
}


@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.User.route){
        composable(Screen.User.route) {
            UserScreen(onClick = {
                navController.navigate(Screen.UserDetails.route)
            })
        }
        composable(Screen.UserDetails.route) {
            UserDetailScreen(onUpPressed = {
                navController.popBackStack()
            })
        }
    }
}*/


val listItems: List<ListItem> = listOf(
    ListItem("Jayme" , 0),
    ListItem("Gil", 1),
    ListItem("Juice WRLD", 2),
    ListItem("Callan", 3),
    ListItem("Braxton", 4),
    ListItem("Kyla", 5),
    ListItem("Lil Mosey", 6),
    ListItem("Allan", 7),
    ListItem("Mike", 8),
    ListItem("Drew", 9),
    ListItem("Nia", 10),
    ListItem("Coi Relay", 11)
)

@Composable
fun UserScreen(items: List<ListItem>, onClick: (Int) -> Unit) {
    // toDO : Fire an intent after creating a button
    // with the details populated from adb cmd

    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(1F), state = listState) {
            items(items) { item ->
                ListItem(item = item, onClick = onClick)
            }
        }
    }
}


//simple
/*@Composable
fun UserScreen( onClick: (Int) -> Unit) {
    // toDO : Fire an intent after creating a button
    // with the details populated from adb cmd

    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(1F), state = listState) {
            items(items) { item ->
                ListItem(item = item, onClick = onClick)
            }
        }
    }
}*/

@Composable
fun ListItem(item: ListItem, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(60.dp)
            .background(color = Color.Gray)
            .clickable(
                onClick = {
                    onClick(item.id)
                }
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "user icon",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically),
                text = item.name,
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically),
                text = item.id.toString(),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}


// originial
@Composable
fun UserDetailScreen(itemId: Int, onBackPressed: () -> Unit){
    Scaffold(
        topBar = { DetailTopAppBar(onBackPressed) }
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colors.background)
        ) {
            val item = listItems.get(itemId)
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
}

/*@Composable
fun UserDetailScreen(onUpPressed: () -> Unit){
    Scaffold(
        topBar = { DetailTopAppBar(onUpPressed) }
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colors.background)
        ) {
            val item = listItems.get(itemId)
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
}*/


@Composable
fun DetailTopAppBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text("Detail", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        }
    )
}

/*
@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White
    )
}*/
