package com.hyun.sesac.register.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hyun.sesac.shared.ui.component.CommonWrapperCard

@Composable
fun PhotoSection(
    modifier: Modifier = Modifier,
    capturedImageUri: Any? = null,
    onTakePhotoClick: () -> Unit
){
    CommonWrapperCard(
        modifier = modifier.padding(top = 8.dp)
    ) {
        if (capturedImageUri == null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(
                        color = Color(0xFFF8F9FF),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { onTakePhotoClick() },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    modifier = Modifier.size(80.dp),
                    shadowElevation = 2.dp
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = "사진 찍기",
                        tint = Color(0xFF6200EE),
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "여기를 눌러 내 차를 찍어주세요!",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE)
                )
                Text(
                    text = "기둥 번호가 잘 나오게 찍으면 좋아요",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        } else {
            Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                Text(
                    text = "여기에 찍은 사진이 이제 보일거야",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}