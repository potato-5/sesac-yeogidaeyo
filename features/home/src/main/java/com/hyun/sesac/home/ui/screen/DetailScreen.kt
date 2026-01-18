package com.hyun.sesac.home.ui.screen
//
//import android.annotation.SuppressLint
//import android.util.Log
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.BottomSheetScaffold
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.SheetValue
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberBottomSheetScaffoldState
//import androidx.compose.material3.rememberStandardBottomSheetState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.snapshotFlow
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import com.hyun.sesac.home.ui.component.ParkingInfo
//import com.hyun.sesac.home.ui.component.SearchTopBar
//import com.hyun.sesac.home.viewmodel.DetailViewModel
//import kotlinx.coroutines.flow.distinctUntilChanged
//
//@SuppressLint("ConfigurationScreenWidthHeight")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DetailScreen(
//    //navController: NavController,
//    //searchQuery: String,
//    paddingValues: PaddingValues,
//    onBackClicked: () -> Unit,
//    viewModel: DetailViewModel
//) {
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//    val configuration = LocalConfiguration.current
//    val fullScreenHeight = configuration.screenHeightDp.dp
//    val searchBarHeight = 60.dp
//    val statusBarHeight = paddingValues.calculateTopPadding()
//    val bottomBarHeight = paddingValues.calculateBottomPadding()
//
//    val sheetMaxHeight = fullScreenHeight - searchBarHeight - statusBarHeight - bottomBarHeight
//    val sheetHalfHeight = fullScreenHeight/2 - bottomBarHeight
//
//    val sheetState = rememberBottomSheetScaffoldState(
//        bottomSheetState = rememberStandardBottomSheetState(
//            initialValue = SheetValue.PartiallyExpanded,
//            skipHiddenState = true
//        )
//    )
//    // ParkingInfo 내부 리스트 스크롤 state
//    val parkingInfoListState = rememberLazyListState()
//
//    // bottom sheet state scroll init
//    LaunchedEffect(sheetState.bottomSheetState){
//        snapshotFlow{ sheetState.bottomSheetState.targetValue }
//            .distinctUntilChanged()
//            .collect{ targetValue ->
//                Log.d("BottomSheet", "Target Value Changed: $targetValue")
//                if(targetValue == SheetValue.PartiallyExpanded){
//                    parkingInfoListState.scrollToItem(0)
//                }
//            }
//    }
//
//    // TODO 수정 -> 검색결과가 없으면 없다고 보여주기
//    BottomSheetScaffold(
//        scaffoldState = sheetState,
//        sheetDragHandle = null,
//        sheetContent = {
//            uiState.parkingDetail?.let { detail ->
//                ParkingInfo(
//                    height = sheetMaxHeight,
//                    listState = parkingInfoListState,
//                    data = detail,
//                    onFavoriteClick = viewModel::onFavoriteClick
//                )
//            } ?: Box(modifier = Modifier.height(sheetMaxHeight)){
//                Text("로딩중...", modifier = Modifier.align(Alignment.Center))
//            }
//        },
//        sheetPeekHeight = sheetHalfHeight,
//        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
//        sheetContainerColor = Color.White,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Box(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            //HomeBackGround()
//
//            SearchTopBar(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                isSearchMode = false,
//                searchQuery = "",
//                onQueryChange = {},
//                onClearQuery = {},
//                onBackClicked = onBackClicked,
//                onSearchAction = {},
//                // 1. api name -> 2. 초기 검색어
//                resultQuery = uiState.parkingDetail?.name?:viewModel.searchQuery,
//            )
//            // TODO floating button 내 위치 버튼
//        }
//    }
//}
///*    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        HomeBackGround()
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.White)
//        ) {
//            SearchTopBar(
//                isSearchMode = false,
//                onBackClicked = { navController.popBackStack() },
//                searchText = searchTextState,
//                onQueryChange = { newValue -> searchTextState = newValue },
//                onSearchAction = { },
//                modifier = Modifier
//                    .fillMaxWidth(),
//                resultQuery = "서울시청"
//            )
//
//            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 4.dp)
//        }
//        DetailBottomSheet(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(bottom = 0.dp)
//        )
//    }
//}*/
