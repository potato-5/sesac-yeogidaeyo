package com.hyun.sesac.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.hyun.sesac.home.ui.screen.DetailScreen
import com.hyun.sesac.home.ui.screen.HomeScreen
import com.hyun.sesac.home.ui.screen.SearchScreen
import com.hyun.sesac.home.viewmodel.SearchViewModel
import com.hyun.sesac.shared.navigation.HomeNavigationRoute

fun NavGraphBuilder.homeNavGraph(navController: NavController, paddingValues: PaddingValues) {
    //composable("homescreen")
    composable<HomeNavigationRoute.HomeTab>{
        HomeScreen(
            navController = navController,
            paddingValues = paddingValues
        )
    }
    composable<HomeNavigationRoute.SearchScreen>{
        val viewModel: SearchViewModel = viewModel()

        SearchScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
    composable<HomeNavigationRoute.DetailScreen>{ backStackEntry ->
        val route: HomeNavigationRoute.DetailScreen = backStackEntry.toRoute()

        DetailScreen(
            navController = navController,
            paddingValues = paddingValues,
            searchQuery = route.searchValue,
            onBackClicked = { navController. popBackStack() }
        )
    }
}