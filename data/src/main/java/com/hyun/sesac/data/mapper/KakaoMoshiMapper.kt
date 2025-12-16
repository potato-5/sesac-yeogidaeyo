package com.hyun.sesac.data.mapper

import com.hyun.sesac.domain.model.PoiInfo
import kotlin.collections.map
import kotlin.text.toDoubleOrNull

// Document(DTO) -> PoiInfo(common 모듈)으로 변환하는 Mapper
fun Document.toPoiInfoModel(): PoiInfo {

    return PoiInfo(
        id = this.id,
        placeName = this.placeName,
        categoryName = this.categoryName,
        phone = this.phone ?: "전화 번호 없음",
        addressName = this.addressName,
        roadAddressName = this.roadAddressName,
        latitude = this.y.toDoubleOrNull() ?: 0.0,
        longitude = this.x.toDoubleOrNull() ?: 0.0
    )
}
// 리스트 변환도 쉽게 가능하도록 Mapper 추가
fun List<Document>.toPoiInfoModelList(): List<PoiInfo> {
    return this.map { it.toPoiInfoModel() }
}