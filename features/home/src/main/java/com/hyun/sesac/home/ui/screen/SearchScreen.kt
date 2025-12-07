package com.hyun.sesac.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hyun.sesac.home.ui.component.RecentSearchList
import com.hyun.sesac.home.ui.component.SearchTopBar
import com.hyun.sesac.shared.navigation.HomeNavigationRoute

@Composable
fun SearchScreen(
    navController: NavHostController,
    modifier: Modifier
) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            SearchTopBar(
                isSearchMode = true,
                onBackClicked = { navController.popBackStack() },
                searchText = searchText, // viewmodel 로 변경 해야 함
                onQueryChange = { newValue -> searchText = newValue },
                modifier = Modifier
                    .fillMaxWidth(),
                resultQuery = "",
                onSearchAction = {
                    navController.navigate(HomeNavigationRoute.DetailScreen.route)
                }
            )

            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 4.dp)

            RecentSearchList(
                navController,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}
