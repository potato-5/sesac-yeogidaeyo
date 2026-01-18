package com.hyun.sesac.home.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hyun.sesac.home.ui.component.AiRecommend
import com.hyun.sesac.home.ui.component.KakaoMapScreen
import com.hyun.sesac.home.ui.component.TopSearchBar
import com.hyun.sesac.home.ui.map.CurrentLocationScreen
import com.hyun.sesac.home.viewmodel.HomeViewModel
import com.hyun.sesac.home.viewmodel.CurrentLocationViewModel
import com.hyun.sesac.home.viewmodel.MapViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.selects.select

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onNavigateToSearch: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    locationViewModel: CurrentLocationViewModel = hiltViewModel(),
    parkingViewModel: MapViewModel = hiltViewModel()
) {
    // 12/08 TODO BottomSheet Coroutine으로 main thread에서 실행 안되도록 ( 예제 찾아보면 잇음 )

    //val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current
    val fullScreenHeight = configuration.screenHeightDp.dp
    val searchBarHeight = 60.dp
    val statusBarHeight = paddingValues.calculateTopPadding()
    val bottomBarHeight = paddingValues.calculateBottomPadding()

    val sheetMaxHeight = fullScreenHeight - statusBarHeight - searchBarHeight - bottomBarHeight - 4.dp

    // bottom sheet state
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded,
            skipHiddenState = true
        )
    )
    // AiRecommend 내부 리스트 스크롤 state
    val aiRecommendListState = rememberLazyListState()
    // bottom sheet state + scroll init
    LaunchedEffect(sheetState.bottomSheetState){
        snapshotFlow{ sheetState.bottomSheetState.targetValue }
            .distinctUntilChanged()
            .collect{ targetValue ->
                Log.d("BottomSheet", "Target Value Changed: $targetValue")
                if(targetValue == SheetValue.PartiallyExpanded){
                    aiRecommendListState.scrollToItem(0)
                }
            }
    }

    val selectedSpot by parkingViewModel.selectedSpot.collectAsStateWithLifecycle()

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetPeekHeight = bottomBarHeight,
        modifier = Modifier.fillMaxSize()
            .padding(bottom = bottomBarHeight),
        sheetContent = {
            if(selectedSpot != null){
                val spot = selectedSpot!!

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                ){
                    Column{
                        Text(
                            text = spot.name,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = spot.address,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }else{
                Box(modifier = Modifier.height(1.dp)){
                    Text(
                        text = "검색결과 없습니다.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            /*AiRecommend(
                height = sheetMaxHeight,
                listState = aiRecommendListState,
                recommendList = uiState.recommendList,
                onFavoriteClick = viewModel::onFavoriteClick
            )*/
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContainerColor = Color.White,
        sheetDragHandle = null,

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CurrentLocationScreen(locationViewModel,parkingViewModel)

            TopSearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .statusBarsPadding()
                    .padding(horizontal = 24.dp),
                onSearchClicked =
                    //navController.navigate(HomeNavigationRoute.SearchScreen)
                    onNavigateToSearch
            )
            // TODO floating button 내 위치 버튼
        }
    }
}


