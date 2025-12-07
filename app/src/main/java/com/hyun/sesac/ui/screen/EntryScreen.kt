package com.hyun.sesac.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hyun.sesac.home.navigation.homeNavGraph
import com.hyun.sesac.mypage.navigation.myPageNavGraph
import com.hyun.sesac.register.navigation.registerNavGraph
import com.hyun.sesac.shared.navigation.HomeNavigationRoute
import com.hyun.sesac.ui.component.BottomAppBarItem

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EntryScreen() {

    val navController = rememberNavController()

    val bottomAppBarItems = remember {
        BottomAppBarItem.Companion.fetchBottomAppBarItems()
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // TODO bottomBar 여부
    val routesToHideBottomBar = remember {
        listOf(
            HomeNavigationRoute.SearchScreen.route,
            HomeNavigationRoute.DetailScreen.route,
        )
    }

    val showBottomBar = currentDestination?.route !in routesToHideBottomBar

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomAppBarItems.forEachIndexed { _, bottomItem ->
                        NavigationBarItem(
                            selected = currentDestination
                                ?.hierarchy
                                ?.any { it.route == bottomItem.destination.route } == true,
                            label = {
                                Text(
                                    text = bottomItem.tabName, color = Color.Blue
                                )
                            },
                            icon = {
                                Icon(
                                    bottomItem.icon,
                                    contentDescription = bottomItem.tabName,
                                    tint = Color.Blue
                                )
                            },
                            onClick = {
                                navController.navigate(bottomItem.destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true //현재 화면의 상태를 저장
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
                }
            }
        }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = HomeNavigationRoute.HomeTab.route,
            //startDestination = "homescreen",
            //modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            homeNavGraph(navController, paddingValues)
            myPageNavGraph(navController)
            registerNavGraph(navController)
        }
    }
}