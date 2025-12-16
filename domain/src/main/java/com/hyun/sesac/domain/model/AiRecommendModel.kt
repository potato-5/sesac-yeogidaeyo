package com.hyun.sesac.domain.model

data class AiRecommendModel(
    val id: String,
    val name: String,
    val imageUrl: Any,
    val operatingTime: String,
    val priceInfo: String,
    val isFavorite: Boolean = false
)
