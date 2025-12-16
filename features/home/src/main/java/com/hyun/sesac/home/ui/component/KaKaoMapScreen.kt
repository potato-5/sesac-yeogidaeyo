package com.hyun.sesac.home.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hyun.sesac.home.viewmodel.HomeViewModel
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.LabelLayerOptions
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles

@Composable
fun KaKaoMapScreen(
    viewModel: HomeViewModel
) {
    // ViewModel 상태 구독
    val uiState by viewModel.uiStateMap.collectAsStateWithLifecycle()

    // KakaoMap 객체를 Composable 내부 상태로 저장
    var kakaoMapRef by remember { mutableStateOf<KakaoMap?>(null) }

    KakaoMapComponent(
        modifier = Modifier.fillMaxSize(),
        onMapReady = { kakaoMap ->
            kakaoMapRef = kakaoMap
        }
    )

    LaunchedEffect(uiState.markers, kakaoMapRef) {
        val map = kakaoMapRef ?: return@LaunchedEffect
        val labelManager = map.labelManager ?: return@LaunchedEffect

        // [수정된 부분] 레이어 관리 로직
        // 1. "markers"라는 이름의 레이어를 가져와 봅니다.
        // 2. 없으면(null이면) "markers"라는 이름으로 새로 만듭니다.
        val layer = labelManager.getLayer("markers")
            ?: labelManager.addLayer(LabelLayerOptions.from("markers"))

        // 가져온 레이어가 여전히 null일 경우에 대한 방어 코드 (이론상 addLayer가 성공하면 null 아님)
        if (layer == null) return@LaunchedEffect

        // 기존 마커 제거
        layer.removeAll()

        // 마커 스타일 정의
        val iconResId = android.R.drawable.ic_menu_mylocation
        val style = LabelStyles.from(LabelStyle.from(iconResId))

        // 마커 추가
        uiState.markers.forEach { marker ->
            val options = LabelOptions.from(LatLng.from(marker.lat, marker.lng))
                .setStyles(style)
                .setTag(marker.id) // 태그 설정 (클릭 이벤트 식별용)

            layer.addLabel(options)
        }
    }
}