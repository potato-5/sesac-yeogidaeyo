package com.hyun.sesac.home.ui.state

import com.hyun.sesac.domain.model.AiRecommendModel
import com.hyun.sesac.domain.model.KakaoMarkerModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val recommendList: List<AiRecommendModel> = emptyList(),
    //val markers: List<KakaoMarkerModel> = emptyList(),
    val errorMessage: String? = null
)