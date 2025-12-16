package com.hyun.sesac.data.impl

import com.hyun.sesac.data.mapper.PoiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApiRepository {
    @GET("/v2/local/search/keyword.json")
    suspend fun searchKeyword(
        @Header("Authorization") apiKey: String = "",
        @Query("query") query: String,
        @Query("x") longitude: String, // 경도
        @Query("y") latitude: String,  // 위도
        @Query("radius") radius: Int = 3000 // 3km 반경
    ): PoiResponse
}