package com.hyun.sesac.home.ui.screen
/*
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hyun.sesac.home.ui.component.RecentSearchList
import com.hyun.sesac.home.ui.component.SearchTopBar
import com.hyun.sesac.home.viewmodel.SearchUiEffect
import com.hyun.sesac.home.viewmodel.SearchViewModel
import com.hyun.sesac.shared.navigation.HomeNavigationRoute

@Composable
fun SearchScreen(
    //navController: NavController,
    viewModel: SearchViewModel, // Hilt 사용시 변경
    onNavigateToDetail: (String) -> Unit,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // viewModel로 부터 오는 side effect 처리
    LaunchedEffect(Unit){
        viewModel.effect.collect{ effect ->
            when(effect){
                is SearchUiEffect.NavigateToDetail -> {
                    // 전달받은 query(searchtext)와 함께 이동
                    // navController.navigate(HomeNavigationRoute.DetailScreen(searchValue = effect.value))
                    onNavigateToDetail(effect.value)
                }
                is SearchUiEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            SearchTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                isSearchMode = true,
                searchQuery = uiState.query,
                onQueryChange = viewModel::onQueryChange,
                onClearQuery = viewModel::onClearQuery,
                onBackClicked = onBackClicked,
                onSearchAction =
                // 버튼 클릭 시 view model 에게 알림
                    // TODO viewModel::onSearch -> 해당 함수 객체 리턴
                    // TODO viewModel.onSearch() -> 해당 함수 당장 실행
                    // ::는 1대 1 실행일 때, 다수 실행일 때는 .
                    viewModel::onSearch
            )

            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 4.dp)

            RecentSearchList(
               // navController,
                modifier = Modifier
                    .fillMaxSize(),
                recentSearch = uiState.recentSearch,
                onItemClicked = { selectedQuery ->
                    viewModel.onQueryChange(selectedQuery)
                    viewModel.onSearch()
                },
                onDeleteClicked = viewModel::onDeleteRecentSearch
            )
        }
    }
}
*/
