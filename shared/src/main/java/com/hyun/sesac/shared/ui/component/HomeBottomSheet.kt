package com.hyun.sesac.shared.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    maxSheetHeight: Dp,
    mainContent: @Composable () -> Unit
) {
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded,
            // 💡 전체 HIDE 방지 (Peek 상태 유지)
            skipHiddenState = true
        )
    )

    val scope = rememberCoroutineScope()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val reactivePeekHeight = screenHeight * 0.3f

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetPeekHeight = reactivePeekHeight,
        sheetShape = MaterialTheme.shapes.extraLarge,
        sheetContent = {
            Column(
                modifier = Modifier
                    .heightIn(max = maxSheetHeight)
                    .fillMaxWidth()
            ) {
               // AiRecommend()
            }
        },
        content = {
            mainContent
        }
    )
}