package com.hyun.sesac.register.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyun.sesac.register.ui.component.InputSection
import com.hyun.sesac.register.ui.component.LocationSection
import com.hyun.sesac.register.ui.component.PhotoSection
import com.hyun.sesac.shared.R
import com.hyun.sesac.shared.ui.component.CommonTitle
import com.hyun.sesac.shared.ui.component.CommonButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    paddingValues: PaddingValues
) {
    // 나중에는 viewmodel 로 수정해야 함
    val locationFromGps = ""
    val parkingFromGps = ""
    val capturedImageUri = null
    val floor = "2"
    val memo = ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 24.dp),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        CommonTitle(stringResource(id = R.string.my_parking_register_title))

        LocationSection(
            locationName = locationFromGps.ifEmpty { stringResource(id = R.string.register_location_empty) },
            parkingSpot = parkingFromGps.ifEmpty { stringResource(id = R.string.register_spot_empty) },
            modifier = Modifier
        )

        PhotoSection(
            modifier = Modifier,
            capturedImageUri = capturedImageUri,
            onTakePhotoClick = {}
        )

        InputSection(
            floor = floor,
            onFloorChange = { },
            memo = memo,
            onMemoChange = { },
            modifier = Modifier
        )

        CommonButton(
            modifier = Modifier.padding(vertical = 16.dp),
            onClick = {},
            text = stringResource(id = R.string.register_my_parking)
        )
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        Scaffold { innerPadding ->
            RegisterScreen(
                paddingValues = innerPadding
            )
        }
    }
}