package com.hyun.sesac.home.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hyun.sesac.home.ui.component.HomeBackGround
import com.hyun.sesac.home.ui.component.ParkingInfo
import com.hyun.sesac.home.ui.component.SearchTopBar
import com.hyun.sesac.home.viewmodel.DetailViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    searchQuery: String,
    onBackClicked: () -> Unit,
    viewModel: DetailViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current
    val fullScreenHeight = configuration.screenHeightDp.dp
    val searchBarHeight = 60.dp
    val statusBarHeight = paddingValues.calculateTopPadding()
    val bottomBarHeight = paddingValues.calculateBottomPadding()

    val sheetMaxHeight = fullScreenHeight - searchBarHeight - statusBarHeight - bottomBarHeight
    val sheetHalfHeight = fullScreenHeight/2 - bottomBarHeight

    var searchTextState by remember { mutableStateOf(TextFieldValue("서울시청")) }
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded,
            skipHiddenState = true
        )
    )

    // ParkingInfo 내부 리스트 스크롤 state
    val parkingInfoListState = rememberLazyListState()
    // bottom sheet state + scroll init
    LaunchedEffect(sheetState.bottomSheetState){
        snapshotFlow{ sheetState.bottomSheetState.targetValue }
            .distinctUntilChanged()
            .collect{ targetValue ->
                Log.d("BottomSheet", "Target Value Changed: $targetValue")
                if(targetValue == SheetValue.PartiallyExpanded){
                    parkingInfoListState.scrollToItem(0)
                }
            }
    }

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetDragHandle = null,
        sheetContent = {
            ParkingInfo(
                height = sheetMaxHeight,
                listState = parkingInfoListState
            )
        },
        sheetPeekHeight = sheetHalfHeight,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContainerColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HomeBackGround()

            /*SearchTopBar(
                isSearchMode = false,
                onBackClicked = { navController.popBackStack() },
                searchText = searchTextState,
                onQueryChange = { newValue -> searchTextState = newValue },
                onSearchAction = { },
                modifier = Modifier
                    .fillMaxWidth(),
                resultQuery = "서울시청"
            )*/
            // TODO floating button 내 위치 버튼
        }
    }
}
/*    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeBackGround()

        Column(
            modifier = Modifier
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
}*/
