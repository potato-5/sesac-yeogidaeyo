package com.hyun.sesac.home.ui

import com.hyun.sesac.domain.model.ParkingDetail

data class DetailUiState(
    val isLoading: Boolean = false,
    val parkingDetail: ParkingDetail? = null,
    val errorMessage: String? = null
)