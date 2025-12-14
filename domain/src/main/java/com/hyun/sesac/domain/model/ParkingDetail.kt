package com.hyun.sesac.domain.model

data class ParkingDetail(
    val name: String,
    val address: String,
    val fullAddress: String,
    val operatingTime: String,
    val priceInfo: String,
    val distance: String,
    val totalSpaces: Int,
    val availableSpaces: Int,
    val isFavorite: Boolean = false
)
