package com.hyun.sesac.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyun.sesac.domain.model.ParkingSpotModel
import com.hyun.sesac.domain.usecase.map.GetParkingSpotsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getParkingSpotsUseCase: GetParkingSpotsUseCase
): ViewModel() {

    private val _parkingSpots = MutableStateFlow<List<ParkingSpotModel>>(emptyList())
    val parkingSpots: StateFlow<List<ParkingSpotModel>> = _parkingSpots.asStateFlow()

    init{
        loadParkingData()
    }

    fun loadParkingData(){
        viewModelScope.launch {
            Log.d("API_TEST", "데이터 요청 시작...")
            getParkingSpotsUseCase("광화문·덕수궁")
                .onSuccess { spots ->
                    Log.d("API_TEST", "2. API 성공! 받아온 주차장 개수: ${spots.size}")

                    if (spots.isEmpty()) {
                        Log.d("API_TEST", "3. 성공은 했는데 리스트가 비어있음. (데이터 파싱 문제 가능성)")
                    } else {
                        spots.forEach {
                            Log.d("API_TEST", "4. 데이터 확인 - 이름: ${it.prkNm}, 위도: ${it.lat}, 경도: ${it.lng}")
                        }
                    }

                    _parkingSpots.value = spots
                }
                .onFailure { e ->
                    Log.e("API_TEST", "2. API 실패! 이유: ${e.message}")
                    e.printStackTrace()
                }
        }
    }
}