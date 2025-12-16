package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hyun.sesac.domain.model.AiRecommendModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiRecommend(
    height: Dp,
    recommendList: List<AiRecommendModel>,
    onFavoriteClick: (String) -> Unit,
    listState: LazyListState
) {
    Column(
        modifier = Modifier // modifier 순서 중요함 !
            .heightIn(max = height) // <--- 전체 높이 제한은 여기서!
            .fillMaxSize()
    ) {
        BottomSheetDefaults.DragHandle(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            width = 32.dp,
            height = 4.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        )

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "AI 추천 주차장",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "더보기", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            // value 받기
            item {
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                    items(
                        items = recommendList,
                        key = {it.id}
                    ){ item ->
                        CardView(
                            item = item,
                            onFavoriteClick = { onFavoriteClick(item.id) }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }
    }
}