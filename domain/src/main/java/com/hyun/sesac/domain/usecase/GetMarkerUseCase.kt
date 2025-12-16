package com.hyun.sesac.domain.usecase

import com.hyun.sesac.domain.model.KakaoMarkerModel
import com.hyun.sesac.domain.repository.KakaoRepository
import kotlinx.coroutines.flow.Flow

class GetMarkersUseCase(
    private val repository: KakaoRepository
) {
    // 'operator fun invoke'를 쓰면 useCase() 처럼 함수처럼 호출할 수 있어 편합니다.
    operator fun invoke(lat: Double, lng: Double): Flow<Result<List<KakaoMarkerModel>>> {
        return repository.getMarkers(lat, lng)
    }
}
