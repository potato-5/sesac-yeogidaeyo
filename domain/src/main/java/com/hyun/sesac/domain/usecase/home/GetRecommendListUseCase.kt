package com.hyun.sesac.domain.usecase.home

import com.hyun.sesac.domain.model.AiRecommendModel
import com.hyun.sesac.domain.repository.RecommendRepository
import kotlinx.coroutines.flow.Flow

class GetRecommendListUseCase(
    private val repository: RecommendRepository
) {
    operator fun invoke(): Flow<List<AiRecommendModel>> {
        return repository.getRecommendations()
    }
}
