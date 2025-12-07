package com.hyun.sesac.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hyun.sesac.home.ui.screen.DetailScreen
import com.hyun.sesac.home.ui.screen.HomeScreen
import com.hyun.sesac.home.ui.screen.SearchScreen
import com.hyun.sesac.shared.navigation.HomeNavigationRoute

fun NavGraphBuilder.homeNavGraph(navController: NavController, paddingValues: PaddingValues) {
    //composable("homescreen")
    composable(route = HomeNavigationRoute.HomeTab.route){
        HomeScreen(
            navController = navController as NavHostController,
            paddingValues = paddingValues
        )
    }
    composable(route = HomeNavigationRoute.SearchScreen.route){
        SearchScreen(
            navController = navController as NavHostController,
            modifier = Modifier
        )
    }
    composable(route = HomeNavigationRoute.DetailScreen.route){
        DetailScreen(
            navController = navController as NavHostController,
            modifier = Modifier
        )
    }
}