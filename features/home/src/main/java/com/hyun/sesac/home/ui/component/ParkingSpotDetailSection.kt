package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CommentsDisabled
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyun.sesac.domain.model.Parking
import com.hyun.sesac.shared.ui.component.CommonIcon
import com.hyun.sesac.shared.ui.component.CommonTitle

@Composable
fun ParkingSpotDetailSection(
    selectedSpot: Parking?,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (selectedSpot == null) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CommonIcon(
                    icon = Icons.Default.CommentsDisabled,
                    iconPadding = 12.dp,
                    iconColor = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "정보가 없습니다.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = selectedSpot.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // TODO 위치 계산 하기
                Text(
                    text = "0.5km · ${selectedSpot.address}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    maxLines = 1
                )
            }

            CommonIcon(
                icon = if(isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                iconPadding = 12.dp,
                iconColor = Color.Red,
                modifier = Modifier
                    .clickable { onBookmarkClick() }
            )
        }
    }
}

/*
@Preview
@Composable
fun ParkingInfoPre() {
    MaterialTheme {
        ParkingSpotDetailSection(
            selectedSpot = null
        )
    }
}*/
