package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ParkingDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = DividerDefaults.Thickness,
        color = DividerDefaults.color
    )
}

@Preview
@Composable
fun ParkingDividerPreview() {
    HorizontalDivider(
        modifier = Modifier,
        thickness = DividerDefaults.Thickness,
        color = DividerDefaults.color
    )
}
