package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.hyun.sesac.home.R

@Composable
fun HomeBackGround(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.seoul_map),
        contentDescription = "지도 배경",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}