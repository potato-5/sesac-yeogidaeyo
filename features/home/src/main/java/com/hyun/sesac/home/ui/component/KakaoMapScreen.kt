package com.hyun.sesac.home.ui.component

import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView

@Composable
fun KakaoMapScreen(
    modifier: Modifier = Modifier,
    // 나중에 마커 리스트 같은 거 받으면 여기 추가 (지금은 일단 지도만)
    onMapReady: (KakaoMap) -> Unit = {}
) {
    val context = LocalContext.current

    // 지도가 준비되었는지 확인하는 상태 (나중에 써먹음)
    var kakaoMapState by remember { mutableStateOf<KakaoMap?>(null) }

    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            // Kakao MapView 생성 (XML 대신 코드로 생성)
            MapView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                // 지도 시작!
                start(object : MapLifeCycleCallback() {
                    override fun onMapDestroy() {
                        // 지도가 죽을 때 할 일 (딱히 없음)
                    }

                    override fun onMapError(error: Exception?) {
                        // 에러 났을 때 (로그 찍기 등)
                    }
                }, object : KakaoMapReadyCallback() {
                    override fun onMapReady(kakaoMap: KakaoMap) {
                        // 지도가 짠! 하고 준비됨
                        kakaoMapState = kakaoMap
                        onMapReady(kakaoMap) // 상위 화면에 "준비됐어!" 알려줌
                    }
                })
            }
        },
        update = { mapView ->
            // 나중에 마커 업데이트 할 때 여기서 함
        }
    )
}