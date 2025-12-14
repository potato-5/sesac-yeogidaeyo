package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hyun.sesac.domain.model.ParkingDetail
import com.hyun.sesac.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingInfo(
    height: Dp,
    listState: LazyListState,
    data: ParkingDetail,
    onFavoriteClick: () -> Unit
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
            //modifier = Modifier.fillMaxWidth(),
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data.name, //"서울시청 본청사",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )
                    // 하트 아이콘
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "즐겨찾기",
                            tint = if(data.isFavorite) Color.Red else Color.Gray
                        )
                    }
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clip(CircleShape)
                    ) {
                        Text("출발")
                    }
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clip(CircleShape)
                    ) {
                        Text("도착")
                    }
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clip(CircleShape)
                    ) {
                        Text("공유")
                    }
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    // [변경] 상세 정보 바인딩 helper 함수 사용 추천
                    InfoRow(label = "운영 중", value = data.operatingTime)
                    InfoRow(label = "기본 요금", value = data.priceInfo)
                    InfoRow(label = data.distance, value = data.address)


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "지번",
                            color = Color(0xFFFF5252),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .border(1.dp, Color(0xFFFF5252), RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = data.fullAddress, color = Color.Gray, fontSize = 14.sp)
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // [변경] 전체 주차면
                    ParkingCountBox(
                        title = "전체 주차면",
                        count = data.totalSpaces,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                    // [변경] 주차 가능면
                    ParkingCountBox(
                        title = "주차 가능면",
                        count = data.availableSpaces,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // 섹션 제목
                    Text(text = "요금 정보", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                    // 요금 상세 내용들 (Row 반복)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "기본 요금(시간)", color = Color.Gray, fontSize = 14.sp)
                        Text(text = "무료/10분", color = Color.Black, fontSize = 14.sp)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "추가 요금(시간)", color = Color.Gray, fontSize = 14.sp)
                        Text(text = "1,000원/10분", color = Color.Black, fontSize = 14.sp)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "경차/저공해자동차 할인율", color = Color.Gray, fontSize = 14.sp)
                        Text(text = "50%", color = Color.Black, fontSize = 14.sp)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "장애인 할인율", color = Color.Gray, fontSize = 14.sp)
                        Text(text = "80%", color = Color.Black, fontSize = 14.sp)
                    }
                }
            }
            item {
                Column {
                    Text(
                        text = "사진 정보",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // 가로 스크롤
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(5) {
                            Image(
                                painter = painterResource(id = R.drawable.parking), // 더미 이미지
                                contentDescription = "주차장 사진",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String){
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(modifier = Modifier.padding(end = 20.dp), text = label, fontWeight = FontWeight.SemiBold)
        Text(text = value)
    }
}

@Composable
fun ParkingCountBox(title: String, count: Int, color: Color, modifier: Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(BorderStroke(1.dp, color.copy(alpha = 0.3f)), RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title, fontSize = 14.sp, color = color)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color)
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "$count", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
    }
}