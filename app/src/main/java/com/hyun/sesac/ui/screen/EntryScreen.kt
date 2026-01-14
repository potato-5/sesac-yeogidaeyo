package com.hyun.sesac.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
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
import kotlin.reflect.KClass

@SuppressLint("RememberReturnType")
@Composable
fun EntryScreen() {
    val navController = rememberNavController()

    val bottomAppBarItems = remember {
        BottomAppBarItem.Companion.fetchBottomAppBarItems()
    }

    // 백스택 state 관찰
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val hideBottomBarRoutes = remember {
        listOf<KClass<*>>(
            HomeNavigationRoute.SearchScreen::class,
            HomeNavigationRoute.DetailScreen::class,
        )
    }

    val showBottomBar by remember(currentDestination) {
        derivedStateOf {
            currentDestination?.let { dest ->
                hideBottomBarRoutes.none { kClass -> dest.hasRoute(kClass) }
            } ?: true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomAppBarItems.forEach{ bottomItem ->

                        val isSelected = currentDestination?.hierarchy?.any { destination ->
                            destination.hasRoute(bottomItem.destination::class)
                        } == true

                        NavigationBarItem(
                            selected = isSelected,
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
                                navController.navigate(bottomItem.destination) {
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
            startDestination = HomeNavigationRoute.HomeTab,
            //startDestination = "HomeScreen",
            //modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            homeNavGraph(navController, paddingValues)
            myPageNavGraph(navController, paddingValues)
            registerNavGraph(navController,paddingValues)
        }
    }
}