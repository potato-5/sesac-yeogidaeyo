package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hyun.sesac.domain.model.Parking

@Composable
fun ParkingSpotDetail(
    selectedSpot: Parking?,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onRouteClick: () -> Unit,
    currentCount: Int?,
    totalCount: Int?,
    modifier: Modifier = Modifier
){
    ParkingSpotDetailSection(
        selectedSpot = selectedSpot,
        isBookmarked = isBookmarked,
        onBookmarkClick = onBookmarkClick,
        modifier = modifier
    )
    Spacer(modifier = Modifier.padding(bottom = 8.dp))

    ParkingDivider()
    ParkingStatusSection(
        total = totalCount,
        current = currentCount,
        onRouteClick = onRouteClick,
        modifier = modifier
    )
    ParkingInfoSection(
        selectedSpot = selectedSpot,
        modifier = modifier
    )
}