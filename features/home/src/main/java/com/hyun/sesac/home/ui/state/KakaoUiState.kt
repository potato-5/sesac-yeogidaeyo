package com.hyun.sesac.home.ui.state

import com.hyun.sesac.domain.model.MarkerModel

// MapUiState.kt
data class MapUiState(
    val markers: List<MarkerModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
