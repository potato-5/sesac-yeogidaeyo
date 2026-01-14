package com.hyun.sesac.domain.repository

import com.hyun.sesac.domain.model.AiRecommendModel
import kotlinx.coroutines.flow.Flow

interface RecommendRepository {
    fun getRecommendations(): Flow<List<AiRecommendModel>>
    suspend fun getToggleFavorite(id: String)
}