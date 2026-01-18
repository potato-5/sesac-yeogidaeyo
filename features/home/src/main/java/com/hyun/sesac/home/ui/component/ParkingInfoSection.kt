package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyun.sesac.domain.model.Parking

@Composable
fun ParkingInfoSection(
    selectedSpot: Parking?,
    modifier: Modifier = Modifier
) {
    if (selectedSpot == null) return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        ParkingInfoRow(
            label = "운영시간",
            value = selectedSpot.address
        )
        ParkingDivider()

        ParkingInfoRow(
            label = "기본요금",
            value = selectedSpot.name,
            subLabel = "최초 30분",
            isFree = true
        )
        ParkingDivider()

        ParkingInfoRow(
            label = "추가요금",
            value = selectedSpot.name,
            subLabel = "10분당",
            isHighlight = true
        )
    }
}

@Preview
@Composable
fun ParkingInfoPreview() {
    MaterialTheme {
        ParkingInfoSection(
            selectedSpot = Parking(id = "12", name = "서울 본청사 주차장", address = "서울시 중구")
        )
    }
}