package com.hyun.sesac.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hyun.sesac.home.ui.component.DetailBottomSheet
import com.hyun.sesac.home.ui.component.HomeBackGround
import com.hyun.sesac.home.ui.component.HomeBottomSheet
import com.hyun.sesac.home.ui.component.SearchTopBar

@Composable
fun DetailScreen(
    navController: NavHostController,
    modifier: Modifier
) {
    var searchTextState by remember { mutableStateOf(TextFieldValue("서울시청")) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        HomeBackGround()

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            SearchTopBar(
                isSearchMode = false,
                onBackClicked = { navController.popBackStack() },
                searchText = searchTextState,
                onQueryChange = { newValue -> searchTextState = newValue },
                onSearchAction = { },
                modifier = Modifier
                    .fillMaxWidth(),
                resultQuery = "서울시청"
            )

            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 4.dp)
        }
        DetailBottomSheet(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 0.dp)
        )
    }
}
