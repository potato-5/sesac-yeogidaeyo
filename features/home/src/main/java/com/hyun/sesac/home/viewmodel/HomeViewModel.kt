package com.hyun.sesac.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyun.sesac.domain.model.AiRecommendValue
import com.hyun.sesac.home.R
import com.hyun.sesac.home.ui.state.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(isLoading = true)
        )

    init {
        loadRecommendList()
    }

    private fun loadRecommendList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1000)
        }

        val mockList = listOf(
            AiRecommendValue(
                id = UUID.randomUUID().toString(),
                name = "남부초등학교 공영주차장",
                imageUrl = R.drawable.parking,
                operatingTime = "09:00 - 21:00",
                priceInfo = "3,000원 (분당)",
                isFavorite = false
            ),
            AiRecommendValue(
                id = UUID.randomUUID().toString(),
                name = "서울시청 주차장",
                imageUrl = R.drawable.parking,
                operatingTime = "24시간",
                priceInfo = "무료",
                isFavorite = true
            ),
            AiRecommendValue(
                id = UUID.randomUUID().toString(),
                name = "동대문 DDP 주차장",
                imageUrl = R.drawable.parking,
                operatingTime = "10:00 - 22:00",
                priceInfo = "4,800원 (시간)",
                isFavorite = false
            )
        )

        _uiState.update {
            it.copy(
                isLoading = false,
                recommendList = mockList
            )
        }
    }

    fun onFavoriteClick(parkingId: String) {
        _uiState.update { currentState ->
            val updatedList = currentState.recommendList.map { item ->
                if (item.id == parkingId) {
                    item.copy(isFavorite = !item.isFavorite)
                } else {
                    item
                }
            }
            currentState.copy(recommendList = updatedList)
        }
    }
}