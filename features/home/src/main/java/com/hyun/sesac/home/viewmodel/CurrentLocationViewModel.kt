package com.hyun.sesac.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyun.sesac.domain.model.UserLocationModel
import com.hyun.sesac.domain.usecase.map.ObserveLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CurrentLocationViewModel @Inject constructor(
    observeLocationUseCase: ObserveLocationUseCase,
    //private val startTrackingUseCase: StartTrackingUseCase,
    //private val stopTrackingUseCase: StopTrackingUseCase
) : ViewModel() {

    val currentLocation: StateFlow<UserLocationModel?> =
        observeLocationUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Companion.WhileSubscribed(5_000),
                initialValue = null
            )

    /*fun startTracking() {
        startTrackingUseCase()
    }

    fun stopTracking() {
        stopTrackingUseCase()
    }*/
}