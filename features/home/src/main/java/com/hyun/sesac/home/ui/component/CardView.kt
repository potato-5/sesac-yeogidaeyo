package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyun.sesac.home.R

@Preview
@Composable
fun CardView(modifier: Modifier = Modifier) {
    // 1. 주 컨테이너: Card (elevation과 둥근 모서리 제공)
    Card(
        modifier = modifier
            .width(220.dp)
            .height(260.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // 그림자 효과
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // 2. 상단 이미지 영역 (160dp 높이)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                // 이미지 Placeholder
                Image(
                    painter = painterResource(id = R.drawable.parking),
                    contentDescription = "주차장 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                )

                // 하트 아이콘 (우측 상단 플로팅)
                IconButton(
                    onClick = { /* TODO: 즐겨찾기 기능 */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "즐겨찾기",
                        tint = Color.Red.copy(alpha = 0.8f)
                    )
                }
            }

            // 3. 텍스트 정보 영역 (하단 텍스트 컨테이너)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                // 주차장 이름
                Text(
                    text = "남부초등학교 공영주차장",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 이용 시간
                Text(
                    text = "이용시간: 09:00 - 21:00",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 이용 요금
                Text(
                    text = "이용요금: 3,000원 (분당)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}