package com.hyun.sesac.domain.model

data class PoiInfo(
    val id: String,
    val placeName: String,
    val categoryName: String,
    val phone: String,
    val addressName: String,
    val roadAddressName: String,
    val longitude: Double,
    val latitude: Double
)
data class FriendInfo(
    val friendName:  String,
    val latitude: Double,
    val longitude: Double
)
