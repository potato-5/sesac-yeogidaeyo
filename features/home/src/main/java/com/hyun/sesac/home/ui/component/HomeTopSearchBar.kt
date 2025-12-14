package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopSearchBar(
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp) // 표준 검색바 높이 유지
            .clickable(onClick = onSearchClicked),
        shape = RoundedCornerShape(32.dp),
        color = Color.White,
       // color = MaterialTheme.colorScheme.surfaceContainerHigh,
        shadowElevation = 6.dp // 검색바는 보통 떠 있는 느낌을 줌
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 8.dp), // 내부 패딩
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 2. 텍스트
            Text(
                text = "검색어를 입력해 주세요",
                style = MaterialTheme.typography.bodyLarge,
                // 하드코딩된 Color.Gray 대신 테마의 onSurfaceVariant 사용
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )

            FilledIconButton(
                onClick = onSearchClicked, // 버튼 눌러도 검색 동작 수행
                modifier = Modifier.size(40.dp), // 버튼 크기 조정
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Color.Blue 대체
                    contentColor = MaterialTheme.colorScheme.onPrimary // Color.White 대체
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "검색"
                )
            }
        }
    }
}

/*
fun TopSearchBar(modifier: Modifier = Modifier, onSearchClicked: () -> Unit) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
            .padding(start = 24.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "검색어를 입력해 주세요",
            modifier = Modifier
                .weight(1f)
                .clickable { onSearchClicked() },
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge
        )

        IconButton(
            onClick = {

            },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Blue)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "검색 버튼",
                tint = Color.White
            )
        }
    }
}*/
