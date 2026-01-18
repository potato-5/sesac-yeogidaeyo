/*
package com.hyun.sesac.home.ui.map

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.hyun.sesac.home.viewmodel.CurrentLocationViewModel
import com.hyun.sesac.home.viewmodel.MapViewModel

@Composable
fun CurrentLocationScreen(
    locationViewModel: CurrentLocationViewModel,
    parkingViewModel: MapViewModel
) {
    val ctx = LocalContext.current

    // 서비스 시작을 위한 단일 함수
    */
/*fun startLocation() {
        locationViewModel.startTracking()
    }*//*


    // GPS 설정 요청 결과 처리
    val settingResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            // GPS가 on 이므로 서비스 시작
            //startLocation()
        } else {
            //commonToast(ctx, "GPS 활성화가 필요합니다")
        }
    }

    // GPS 설정 확인 및 서비스 시작을 통합한 함수
    fun checkGpsAndStart() {
        checkPhoneGpsSettings(
            context = ctx,
            onGpsEnabled = {
                // GPS가 이미 켜져 있으면 서비스 시작
                //startLocation()
            },
            onGpsSettingResolve = { intentSenderRequest ->
                // GPS를 켜야 하는 경우, 다이얼로그 요청
                settingResultLauncher.launch(intentSenderRequest)
            }
        )
    }

    // 권한 요청 결과 처리
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val fineLocation = permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false )
            // 위치 권한이 부여되었는지 확인
            val coarseLocation = permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)

                // 권한이 부여되었으면 GPS 확인 및 서비스 시작 절차 진행
            if(fineLocation || coarseLocation){
                checkGpsAndStart()
            } else {
                //commonToast(ctx, "위치 권한이 필요합니다")
            }
        }
    )

    // 화면이 처음 렌더링될 때 권한 상태 확인 및 요청
    LaunchedEffect(Unit) {
        val locationPermissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        // Android 13(Tiramisu) 이상 알림 권한 추가
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            locationPermissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        //현재 앱이 권한들을 가지고 있는지 확인
        val hasLocationPermission = locationPermissions.all {
            ContextCompat.checkSelfPermission(ctx, it) == PackageManager.PERMISSION_GRANTED
        }

        if (hasLocationPermission) {
            // 이미 권한이 있으면 GPS 확인 및 서비스 시작
            checkGpsAndStart()
        } else {
            permissionLauncher.launch(locationPermissions.toTypedArray())
        }
    }

    //현재 컴포즈 스크린 stop 시 서비스 중지
    */
/*DisposableEffect(Unit) {
        onDispose {
            locationViewModel.stopTracking()
        }
    }*//*


    //초기 서울 시청
    val seoulCityHall = LatLng(37.566535, 126.9779692)
    val currentLocation by locationViewModel.currentLocation.collectAsStateWithLifecycle(initialValue = null)
    val parkingSpots by parkingViewModel.parkingSpots.collectAsStateWithLifecycle()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(seoulCityHall, 15f)
    }
    val markerState = remember { MarkerState(position = seoulCityHall) }

    LaunchedEffect(currentLocation) {
        currentLocation?.let { newLocation ->
            val latLng = LatLng(newLocation.latitude, newLocation.longitude)
            markerState.position =  latLng
            cameraPositionState.animate(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(latLng, 15f)
                )
            )
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // TODO MARKER 말고 내 위치 수정
        currentLocation?.let {
            Marker(
                state = MarkerState(LatLng(it.latitude, it.longitude)),
                title = "내 위치"
            )
        }

        parkingSpots.forEach { spot ->
            Marker(
                state = MarkerState(LatLng(spot.latitude, spot.longitude)),
                title = spot.name,
                //snippet = spot.priceInfo
                onClick = { marker ->
                    parkingViewModel.onSpotSelected(spot)
                    false
                }
            )
        }
    }
    */
/*GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = markerState,
            title = "현재 내 위치",
            snippet = "위도: ${markerState.position.latitude}, 경도: ${markerState.position.longitude}"
        )
    }*//*

}


*/
/**
 * GPS 설정(Setting) 상태를 확인하고 결과를 콜백으로 전달
 * @param context 컨텍스트
 * @param onGpsEnabled GPS가 이미 켜져 있을 때 호출될 콜백 함수
 * @param onGpsSettingResolve GPS 설정이 필요할 때(꺼져 있을 때) 호출될 콜백 함수
 *//*


fun checkPhoneGpsSettings(
    context: Context,
    onGpsEnabled: () -> Unit,
    onGpsSettingResolve: (IntentSenderRequest) -> Unit
) {
    //위치 요청 설정 (정확도, 간격 등)
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()

    //현재 단말기의 GPS 설정이 위치 요청을 만족하는지 확인하기 위한 빌더
    val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
    val client: SettingsClient = LocationServices.getSettingsClient(context)

    //설정 확인 요청 실행
    val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

    //성공 리스너 (GPS가 켜져 있음)
    task.addOnSuccessListener {
        // GPS가 켜져 있으므로 성공 콜백 호출
        onGpsEnabled()
    }

    //실패 리스너 (GPS가 꺼져 있음)
    task.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            // 사용자가 직접 GPS 설정을 변경할 수 있는 경우
            try {
                // GPS 활성화 다이얼로그를 띄우기 위한 요청 생성 후 콜백 호출
                val intentSenderRequest = IntentSenderRequest.Builder(exception.resolution).build()
                onGpsSettingResolve(intentSenderRequest)
            } catch (sendEx: IntentSender.SendIntentException) {
                // 다이얼로그를 보여줄 수 없는 예외적인 경우, 시스템 설정 화면으로 직접 이동하여 사용자가 직접 on 설정유도
                context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        }
    }
}*/
