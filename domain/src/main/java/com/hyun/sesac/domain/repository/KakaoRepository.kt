package com.hyun.sesac.domain.repository

import com.hyun.sesac.domain.model.PoiInfo

interface KakaoRepository {
    suspend fun searchKeyword(
        apiKey: String,
        query: String,
        longitude: String, // 경도
        latitude: String,  // 위도
        radius: Int = 3000 // 3km 반경
    ): List<PoiInfo>
}