package com.hyun.sesac.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hyun.sesac.mypage.ui.MyPageScreen
import com.hyun.sesac.shared.navigation.HomeNavigationRoute
import com.hyun.sesac.shared.navigation.MyPageNavigationRoute

fun NavGraphBuilder.myPageNavGraph(navController: NavController, paddingValues: PaddingValues) {
    composable<MyPageNavigationRoute.MyPageTab>{
        MyPageScreen(
            paddingValues = paddingValues,
            onNavigateToSearch = {
                // TODO 상세 페이지 만들면 해당 페이지로 변경하기
                navController.navigate(HomeNavigationRoute.SearchScreen)
            }
        )
    }
}