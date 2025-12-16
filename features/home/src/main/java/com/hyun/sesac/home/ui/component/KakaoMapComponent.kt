package com.hyun.sesac.home.ui.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView

/**
 * Kakao Map V2를 Jetpack Compose에서 사용할 수 있게 래핑한 컴포넌트
 *
 * @param modifier Compose Modifier
 * @param onMapReady 지도가 준비되었을 때 호출되는 콜백 (KakaoMap 객체 반환)
 */
@Composable
fun KakaoMapComponent(
    modifier: Modifier = Modifier,
    onMapReady: (KakaoMap) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // 1. MapView를 Composable의 수명 주기 동안 한 번만 생성하고 유지합니다.
    val mapView = remember { MapView(context) }

    // 2. 생명주기 관리 (공식 문서: MapView의 resume(), pause() 호출 필수)
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapView.resume()
                Lifecycle.Event.ON_PAUSE -> mapView.pause()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            // 필요 시 mapView.finish() 등을 호출할 수 있으나,
            // AndroidView가 제거될 때 View 시스템이 정리 작업을 수행합니다.
        }
    }

    // 3. AndroidView를 통해 MapView 렌더링
    AndroidView(
        modifier = modifier,
        factory = { _ ->
            // factory 블록은 뷰가 처음 생성될 때 1회만 실행됩니다.
            mapView.apply {
                start(
                    object : MapLifeCycleCallback() {
                        override fun onMapDestroy() {
                            Log.d("KakaoMap", "Map Destroyed")
                        }

                        override fun onMapError(error: Exception?) {
                            Log.e("KakaoMap", "Map Error: ${error?.message}")
                        }
                    },
                    object : KakaoMapReadyCallback() {
                        override fun onMapReady(kakaoMap: KakaoMap) {
                            // 지도가 완전히 로드되어 조작 가능한 상태
                            Log.d("KakaoMap", "Map Ready")
                            onMapReady(kakaoMap)
                        }
                    }
                )
            }
        }
    )
}