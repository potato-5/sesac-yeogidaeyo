package com.hyun.sesac.data.mapper

import com.hyun.sesac.domain.model.KakaoMarkerModel

/*
// 확장 함수로 만들면 사용하기 편합니다.
fun KakaoSearchResponse.Document.toDomain(): KakaoMarkerModel {
    return KakaoMarkerModel(
        id = this.id,
        placeName = this.place_name,
        latitude = this.y.toDoubleOrNull() ?: 0.0,
        longitude = this.x.toDoubleOrNull() ?: 0.0,
        address = this.road_address_name.ifEmpty { this.address_name },
        phone = this.phone
    )
}*/
