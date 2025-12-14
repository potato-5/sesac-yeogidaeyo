package com.hyun.sesac.home.ui.state

import com.hyun.sesac.domain.model.AiRecommendValue

data class HomeUiState(
    val isLoading: Boolean = false,
    val recommendList: List<AiRecommendValue> = emptyList(),
    val errorMessage: String? = null
)