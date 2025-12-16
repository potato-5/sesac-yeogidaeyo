package com.hyun.sesac.domain.usecase

import com.hyun.sesac.domain.model.MarkerModel
import com.hyun.sesac.domain.repository.KakaoRepository

class GetMarkersUseCase(
    private val repository: KakaoRepository
) {
    suspend operator fun invoke(
        query: String,
        lat: Double,
        lng: Double
    ): List<MarkerModel> {
        // [수정] Kakao REST API 규격에 맞춰 "KakaoAK " 접두어 추가
        // 보안 권장사항: API Key는 local.properties나 BuildConfig로 관리하는 것을 강력 추천합니다.
        val headerApiKey = "KakaoAK 2635106b67f96e5c3fc879bee650db4f"

        val poiList = repository.searchKeyword(
            apiKey = headerApiKey,
            query = query,
            longitude = lng.toString(),
            latitude = lat.toString()
        )

        return poiList.map { poi ->
            MarkerModel(
                id = poi.id,
                lat = poi.latitude,
                lng = poi.longitude,
                name = poi.placeName
            )
        }
    }
}