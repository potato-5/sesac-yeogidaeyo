package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TurnRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ParkingStatusSection(
    total: Int?,
    current: Int?,
    onRouteClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Surface(
        color = Color(0xFFE8F5E9),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ){
        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column{
                Row(verticalAlignment = Alignment.CenterVertically){
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF00C853), CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "주차 여유",
                        color = Color(0xFF00C853),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // 주차 대수 (54 / 101)
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 42.sp, fontWeight = FontWeight.Bold)) {
                            append("$current")
                        }
                        withStyle(style = SpanStyle(fontSize = 18.sp, color = Color.Gray)) {
                            append("$total")
                        }
                    }
                )
            }

            Surface(
                color = Color(0xFF6200EE), // 보라색 (피그마 참고)
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .size(72.dp) // 정사각형 크기
                    .clickable {
                        onRouteClick()
                    },
                shadowElevation = 4.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.TurnRight, // 화살표 아이콘
                        contentDescription = "길찾기",
                        tint = Color.White,
                        modifier = Modifier
                            .size(34.dp)
                    )
                    Text(
                        text = "길찾기",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkingStatusSectionPreview() {
    MaterialTheme {
        ParkingStatusSection(
            total = 101,
            current = 54,
            onRouteClick = {}
        )
    }
}
