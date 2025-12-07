package com.hyun.sesac.home.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hyun.sesac.shared.ui.component.BottomSheet

@Composable
fun HomeBottomSheet(modifier: Modifier = Modifier){
    BottomSheet(
        modifier = modifier
    ) {
        AiRecommend()
    }
}