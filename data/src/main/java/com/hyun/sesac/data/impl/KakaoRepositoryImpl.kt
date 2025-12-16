package com.hyun.sesac.data.impl

import com.hyun.sesac.data.remote.KakaoApiClient
import com.hyun.sesac.domain.model.KakaoMarkerModel
import com.hyun.sesac.domain.repository.KakaoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class KakaoRepositoryImpl : KakaoRepository {
    override fun getMarkers(centerLat: Double, centerLng: Double): Flow<Result<List<KakaoMarkerModel>>> = flow {
        /*try {
            // ✅ 싱글톤 객체(KakaoApiClient)의 service에 직접 접근
            // (주의: KakaoApiClient 내부에 service 변수가 public으로 열려 있어야 함)
            val response = KakaoApiClient.service.searchKeyword(
                query = "주차장", // 예시 검색어
                x = centerLng.toString(),
                y = centerLat.toString(),
                radius = 1000
            )

            val markers = response.documents.map { it.toDomain() }
            emit(Result.success(markers))

        } catch (e: Exception) {
            emit(Result.failure(e))
        }*/
    }
}