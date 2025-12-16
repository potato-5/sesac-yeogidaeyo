package com.hyun.sesac.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.hyun.sesac.domain.model.AiRecommendModel
import com.hyun.sesac.domain.usecase.GetMarkersUseCase
import com.hyun.sesac.home.R
import com.hyun.sesac.home.ui.state.HomeUiState
import com.hyun.sesac.home.ui.state.MapUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModelFactory(
    private val getMarkersUseCase: GetMarkersUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(getMarkersUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class HomeViewModel(
    private val getMarkersUseCase: GetMarkersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiStateMap = MutableStateFlow(MapUiState())
    val uiStateMap = _uiStateMap.asStateFlow()

    /*    val test = flow {
            usecase.repoImpl()
            }.catch()
                }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeUiState(isLoading = true)
                )*/
    init {
        loadRecommendList()
        loadMarkers(
            query = "서울역",          // 기본 검색어
            lat = 37.5546,          // 기본 위도 (예: 서울역)
            lng = 126.9706          // 기본 경도
        )
    }

    private fun loadRecommendList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1000)
        }

        val mockList = listOf(
            AiRecommendModel(
                id = UUID.randomUUID().toString(),
                name = "남부초등학교 공영주차장",
                imageUrl = R.drawable.parking,
                operatingTime = "09:00 - 21:00",
                priceInfo = "3,000원 (분당)",
                isFavorite = false
            ),
            AiRecommendModel(
                id = UUID.randomUUID().toString(),
                name = "서울시청 주차장",
                imageUrl = R.drawable.parking,
                operatingTime = "24시간",
                priceInfo = "무료",
                isFavorite = true
            ),
            AiRecommendModel(
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


    fun loadMarkers(query: String, lat: Double, lng: Double) {
        viewModelScope.launch {
            _uiStateMap.update { it.copy(isLoading = true) }
            try {
                val markers = getMarkersUseCase(query, lat, lng)
                _uiStateMap.update { it.copy(markers = markers, isLoading = false) }
            } catch (e: Exception) {
                _uiStateMap.update { it.copy(errorMsg = e.message, isLoading = false) }
            }
        }
    }
}