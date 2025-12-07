package com.hyun.sesac.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hyun.sesac.register.ui.RegisterScreen
import com.hyun.sesac.shared.navigation.MyPageNavigationRoute
import com.hyun.sesac.shared.navigation.RegisterNavigationRoute

fun NavGraphBuilder.registerNavGraph(navController: NavController) {
    composable(route = RegisterNavigationRoute.RegisterTab.route){
        RegisterScreen()
    }
}