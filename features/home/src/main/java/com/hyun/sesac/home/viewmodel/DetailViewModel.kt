package com.hyun.sesac.home.viewmodel
/*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hyun.sesac.domain.model.ParkingDetailModel
import com.hyun.sesac.home.ui.state.DetailUiState
import com.hyun.sesac.shared.navigation.HomeNavigationRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle // hilt 자동 주입
) : ViewModel(){
    private val routeArgs = savedStateHandle.toRoute<HomeNavigationRoute.DetailScreen>()
    val searchQuery = routeArgs.searchValue

    private val _uiState = MutableStateFlow(DetailUiState(isLoading = true))
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailUiState(isLoading = true)
        )

    init {
        loadParkingDetail(searchQuery)
    }

    private fun loadParkingDetail(query: String){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            delay(1000)

            // usecase로 대체
            val mockData = ParkingDetailModel(
                name = query,
                address = "중구 세종대로 110",
                fullAddress = "중구 태평로 1가 31-0",
                operatingTime = "09:00 ~ 21:00",
                priceInfo = "무료/10분",
                distance = "0.5km",
                totalSpaces = 101,
                availableSpaces = 54,
                isFavorite = false
            )

            _uiState.update {
                it.copy(
                    isLoading = false,
                    parkingDetail = mockData
                )
            }
        }
    }

    fun onFavoriteClick(){
        _uiState.update{ currentState ->
            val currentDetail = currentState.parkingDetail ?: return@update currentState
            currentState.copy(
                parkingDetail = currentDetail.copy(isFavorite = !currentDetail.isFavorite)
            )
        }
    }
}*/
