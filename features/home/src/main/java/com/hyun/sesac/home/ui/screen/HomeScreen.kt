package com.hyun.sesac.home.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hyun.sesac.home.ui.component.HomeBackGround
import com.hyun.sesac.home.ui.component.HomeBottomSheet
import com.hyun.sesac.home.ui.component.TopSearchBar
import com.hyun.sesac.shared.navigation.HomeNavigationRoute

@Composable
fun HomeScreen(navController: NavHostController, paddingValues: PaddingValues){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        HomeBackGround()

        TopSearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = paddingValues.calculateTopPadding())
                .padding(horizontal = 24.dp),
            onSearchClicked = { // home screen -> search screen 이동
                navController.navigate(HomeNavigationRoute.SearchScreen.route)
            }
        )

        HomeBottomSheet(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
    }
}