package com.hyun.sesac.data.impl

import com.hyun.sesac.data.mapper.toPoiInfoModelList
import com.hyun.sesac.data.remote.KakaoApiClient
import com.hyun.sesac.domain.model.PoiInfo
import com.hyun.sesac.domain.repository.KakaoRepository


class KakaoRepositoryImpl : KakaoRepository {
    val apiClient = KakaoApiClient.kakaoApi
    override suspend fun searchKeyword(
        apiKey: String,
        query: String,
        longitude: String,
        latitude: String,
        radius: Int
    ): List<PoiInfo> {
        val response = apiClient.searchKeyword(apiKey, query, longitude, latitude, radius)
        return response.documents.toPoiInfoModelList()
    }
}