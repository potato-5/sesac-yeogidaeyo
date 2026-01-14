package com.hyun.sesac.register.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hyun.sesac.shared.ui.component.CommonIcon
import com.hyun.sesac.shared.ui.component.CommonWrapperCard

@Composable
fun InputSection(
    floor: String,
    onFloorChange: (Int) -> Unit,
    memo: String,
    onMemoChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    ){
    CommonWrapperCard(
        modifier = modifier.padding(top = 1.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "주차 층수",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE0E0E0),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    IconButton(
                        onClick = { onFloorChange(-1) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "빼기",
                            tint = Color(0xFF6200EE)
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color(0xFFE0E0E0))
                    )

                    Text(
                        text = floor,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color(0xFFE0E0E0))
                    )

                    IconButton(
                        onClick = { onFloorChange(1) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "더하기",
                            tint = Color(0xFF6200EE)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = Color(0xFFF8F9FF),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {

                CommonIcon(
                    icon = Icons.Default.EditNote,
                    iconPadding = 8.dp,
                    iconColor = Color(0xFF6200EE),
                    modifier = Modifier
                )
                /*Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .size(48.dp),
                    shadowElevation = 2.dp
                ) {
                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = "한 줄 메모",
                        tint = Color(0xFF6200EE),
                        modifier = Modifier.padding(8.dp)
                    )
                }*/

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "한 줄 메모",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .offset(y = 10.dp)
                            .padding(start = 15.dp, top = 5.dp)
                    )

                    TextField(
                        value = memo,
                        onValueChange = onMemoChange,
                        placeholder = { Text("예: 엘리베이터 앞") },
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                }
            }
        }
    }
}