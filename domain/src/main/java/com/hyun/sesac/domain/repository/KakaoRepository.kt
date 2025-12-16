package com.hyun.sesac.domain.repository

import com.hyun.sesac.domain.model.KakaoMarkerModel
import kotlinx.coroutines.flow.Flow

interface KakaoRepository {
    fun getMarkers(centerLat: Double, centerLng: Double): Flow<Result<List<KakaoMarkerModel>>>
}