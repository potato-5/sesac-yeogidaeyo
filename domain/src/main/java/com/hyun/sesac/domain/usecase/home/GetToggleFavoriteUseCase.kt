package com.hyun.sesac.domain.usecase.home

import com.hyun.sesac.domain.repository.RecommendRepository

class GetToggleFavoriteUseCase(
    private val repository: RecommendRepository
) {
    suspend operator fun invoke(id: String) {
        repository.getToggleFavorite(id)
    }
}
