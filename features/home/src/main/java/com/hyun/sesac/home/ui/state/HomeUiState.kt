package com.hyun.sesac.home.ui.state

import com.hyun.sesac.domain.model.AiRecommendModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val recommendList: List<AiRecommendModel> = emptyList(),
    val errorMessage: String? = null
)