package com.hyun.sesac.domain.model

data class KakaoMarkerModel(
    val id: String,          // 마커 고유 ID
    val placeName: String,   // 장소 이름 (예: "스타벅스 강남점")
    val latitude: Double,    // 위도
    val longitude: Double,   // 경도
    val address: String,     // 주소 (선택)
    val phone: String        // 전화번호 (선택)
)