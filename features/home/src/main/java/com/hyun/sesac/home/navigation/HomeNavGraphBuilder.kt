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
import com.hyun.sesac.home.viewmodel.DetailViewModel
import com.hyun.sesac.home.viewmodel.HomeViewModel
import com.hyun.sesac.home.viewmodel.SearchViewModel
import com.hyun.sesac.shared.navigation.HomeNavigationRoute

// navController를 screen으로 넘겨서 .navigate()호출 x
fun NavGraphBuilder.homeNavGraph(navController: NavController, paddingValues: PaddingValues) {
    //composable("homescreen")
    composable<HomeNavigationRoute.HomeTab>{
        val viewModel: HomeViewModel = viewModel()
        HomeScreen(
            paddingValues = paddingValues,
            onNavigateToSearch = {
                navController.navigate(HomeNavigationRoute.SearchScreen)
            },
            viewModel = viewModel
        )
    }
    composable<HomeNavigationRoute.SearchScreen>{
        val viewModel: SearchViewModel = viewModel()

        SearchScreen(
            onNavigateToDetail = { query ->
                navController.navigate(HomeNavigationRoute.DetailScreen(query))
            },
            onBackClicked = { navController. popBackStack() },
            viewModel = viewModel,
        )
    }
    composable<HomeNavigationRoute.DetailScreen>{ backStackEntry ->
        //val route: HomeNavigationRoute.DetailScreen = backStackEntry.toRoute()
        val viewModel: DetailViewModel = viewModel()

        DetailScreen(
            //searchQuery = route.searchValue,
            paddingValues = paddingValues,
            onBackClicked = { navController. popBackStack() },
            viewModel = viewModel
        )
    }
}