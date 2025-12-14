package com.hyun.sesac.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.hyun.sesac.register.R

// 1. 최상위 진입점 (Stateful): 상태 관리 및 BottomSheet 제어 담당
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    // ViewModel 대신 UI 내부에서 상태 관리 (화면 회전 대응)
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    // 공통 닫기 로직 (애니메이션 후 상태 변경)
    val closeSheet: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) showBottomSheet = false
        }
    }

    // 메인 UI 컨텐츠 (상태 없음, 이벤트만 전달)
    RegisterScreenContent(
        modifier = modifier,
        onMoreClicked = { showBottomSheet = true }
    )

    // Bottom Sheet 조건부 렌더링
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            containerColor = Color.White // 디자인에 맞춰 배경색 지정
        ) {
            // 시트 내부 컨텐츠
            RegisterBottomSheetContent(
                onExitClick = {
                    // TODO: 출차 로직 추가
                    closeSheet()
                },
                onEditClick = {
                    // TODO: 수정 로직 추가
                    closeSheet()
                },
                onDeleteClick = {
                    // TODO: 삭제 로직 추가
                    closeSheet()
                }
            )
        }
    }
}

// 2. 메인 화면 UI (Stateless): 보여주기만 담당
@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    locationName: String = "서울시청 본청사 주차장",
    spotName: String = "B1A 23",
    entryTime: String = "10:30",
    cost: String = "30,000 원",
    onMoreClicked: () -> Unit // 이벤트 호이스팅
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()) // 스크롤 가능하도록 변경
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
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                ) {
                    // 더미 이미지
                    Image(
                        painter = painterResource(id = R.drawable.parking),
                        contentDescription = "주차 사진",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // [수정됨] 더보기 버튼 동작 연결
                    IconButton(
                        onClick = onMoreClicked, // 상위로 이벤트 전달
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

                    // 카메라 버튼 (기능 없음)
                    IconButton(
                        onClick = { /* 사진 찍기 */ },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                            .size(48.dp)
                            .background(Color.Black.copy(alpha = 0.3f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = "카메라",
                            tint = Color.White
                        )
                    }

                    // 하단 정보 오버레이
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(20.dp)
                            .height(80.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.6f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
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
                            Text(
                                text = spotName,
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // 카드 하단 정보 영역
                Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("입차 시간", color = Color.Gray, fontSize = 14.sp)
                            Spacer(Modifier.height(6.dp))
                            Text(entryTime, color = Color.Black, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("예상 주차 요금", color = Color.Gray, fontSize = 14.sp)
                            Spacer(Modifier.height(6.dp))
                            Text(cost, color = Color(0xFFFF5252), fontSize = 26.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                            .padding(vertical = 14.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Warning, null, tint = Color(0xFFFF5252), modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "입차 시간과 주차 요금은 오차가 있을 수 있습니다",
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

// 3. Bottom Sheet 내부 컨텐츠 (디자인 반영)
@Composable
fun RegisterBottomSheetContent(
    onExitClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 50.dp, top = 10.dp), // 하단 여백 충분히
        verticalArrangement = Arrangement.spacedBy(12.dp) // 버튼 사이 간격
    ) {
        // 1. 출차하기 (파란색)
        Button(
            onClick = onExitClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)), // 파란색
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("출차하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        // 2. 정보 수정하기 (회색)
        Button(
            onClick = onEditClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9E9E9E)), // 회색
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("정보 수정하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        // 3. 등록 삭제하기 (빨간색)
        Button(
            onClick = onDeleteClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5252)), // 빨간색
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("등록 삭제하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}