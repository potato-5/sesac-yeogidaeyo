package com.hyun.sesac.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hyun.sesac.register.R

//@Preview(showBackground = true)
@Composable
fun RegisterScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    locationName: String = "서울시청 본청사 주차장",
    spotName: String = "B1A 23",
    entryTime: String = "10:30",
    cost: String = "30,000 원"
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .statusBarsPadding()
        // .verticalScroll(rememberScrollState()) // 필요 시 스크롤 추가
    ) {
        Text(
            text = "내 주차 등록",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "AI와 함께 나의 주차 위치를 한눈에",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(), // 높이를 내용물에 맞게 (너무 작아지지 않게)
            shape = RoundedCornerShape(28.dp), // 둥근 모서리 크게
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp) // 이미지 높이를 충분히 줌 (스크린샷 비율 반영)
                ) {
                    // 1-1. 배경 이미지 (실제 주차장 사진 or 회색 placeholder)
                    Image(
                        painter = painterResource(id = R.drawable.parking), // 더미 이미지
                        contentDescription = "주차 사진",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // 1-2. 우측 상단 더보기 아이콘 (동그라미 배경)
                    IconButton(
                        onClick = { /* 메뉴 */ },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .size(36.dp)
                            .background(Color.Black.copy(alpha = 0.3f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "더보기",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = { /* 메뉴 */ },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                            .size(48.dp)
                            .background(Color.Black.copy(alpha = 0.3f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "더보기",
                            tint = Color.White
                        )
                    }

                    // 1-3. 반투명 검은 박스
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // 이미지 하단에 배치
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 20.dp) // 이미지 끝에서 좀 띄움
                            .height(80.dp) // 박스 높이 설정
                            .background(
                                color = Color.Black.copy(alpha = 0.6f), // 60% 투명도 검은색
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 20.dp), // 박스 내부 글자 패딩
                        contentAlignment = Alignment.CenterStart // 글자들 수직 중앙 정렬
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                // 작은 위치 텍스트
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    // 파란색 위치 아이콘 점 (Canvas나 Icon으로 대체 가능)
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .background(Color(0xFF2196F3), CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = locationName,
                                        color = Color.White.copy(alpha = 0.9f),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                // 큰 주차면 텍스트 (B1A 23)
                                Text(
                                    text = spotName,
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // ==========================================================
                // 2. 하단 정보 영역 (흰색 배경)
                // ==========================================================
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp) // [요청하신 부분] 내부 패딩을 넓게 줘서 영역 확보
                ) {
                    // 2-1. 입차 시간 & 요금 Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // 왼쪽: 입차 시간
                        Column {
                            Text(
                                text = "입차 시간",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = entryTime,
                                color = Color.Black,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // 오른쪽: 요금 (우측 정렬)
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "예상 주차 요금",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = cost,
                                    color = Color(0xFFFF5252), // 빨간색
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // 2-2. 하단 경고 문구 (연한 회색/빨강 배경 캡슐)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFF5F5F5), // 아주 연한 회색 (스크린샷 유사)
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 14.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning, // 경고 아이콘
                            contentDescription = null,
                            tint = Color(0xFFFF5252),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "입차 시간과 주차 요금은 오차가 있을 수 있습니다",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}