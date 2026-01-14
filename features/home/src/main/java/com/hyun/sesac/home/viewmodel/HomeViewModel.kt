package com.hyun.sesac.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyun.sesac.domain.usecase.home.GetRecommendListUseCase
import com.hyun.sesac.domain.usecase.home.GetToggleFavoriteUseCase
import com.hyun.sesac.home.ui.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecommendListUseCase: GetRecommendListUseCase,
    private val getToggleFavoriteUseCase: GetToggleFavoriteUseCase
) : ViewModel() {

    /*    val test = flow {
           usecase.repoImpl()
           }.catch()
               }.stateIn(
               scope = viewModelScope,
               started = SharingStarted.WhileSubscribed(5000),
               initialValue = HomeUiState(isLoading = true)
               )*/
    // TODO 12/24 button 클릭시 flow 처리
    // TODO 12/24 @hiltviewmodel 차이 -> owner에 맞춰서 viewmodel 유지 cleared 마지막 재정의
    // screen 1개 당 1개의 viewmodel이 아님 2개를 줄 수 있음
    val uiState: StateFlow<HomeUiState> = getRecommendListUseCase()
        .map{ list ->
            HomeUiState(
                isLoading = false,
                recommendList = list
            )
        }.catch{
            emit(HomeUiState(isLoading = false))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(isLoading = true)
        )

    fun onFavoriteClick(parkingId: String) {
        viewModelScope.launch {
            getToggleFavoriteUseCase(parkingId)
        }
    }
}