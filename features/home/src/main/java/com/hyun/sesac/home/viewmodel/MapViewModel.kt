package com.hyun.sesac.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyun.sesac.domain.common.DataResourceResult
import com.hyun.sesac.domain.model.Parking
import com.hyun.sesac.domain.repository.BookmarkRepository
import com.hyun.sesac.domain.usecase.firestore.GetParkingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.forEach

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getParkingUseCase: GetParkingUseCase,
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    // 전체 지도 마커용 ( firestore )
    private val _parkingSpots = MutableStateFlow<List<Parking>>(emptyList())
    val parkingSpots: StateFlow<List<Parking>> = _parkingSpots.asStateFlow()

    private val _selectedSpotID = MutableStateFlow<String?>(null)
    @OptIn(ExperimentalCoroutinesApi::class)
    val selectedSpot: StateFlow<Parking?> = _selectedSpotID
        .flatMapLatest { id ->
            if (id == null) {
                flowOf(null)
            } else {
                // (1) 일단 Firestore 목록에서 해당 주차장을 찾음 (이름, 주소, 전체대수 보존)
                val firestoreSpot = _parkingSpots.value.find { it.id == id }

                if (firestoreSpot == null) {
                    flowOf(null) // Firestore에 없는 데이터면 null
                } else {
                    // (2) Room DB(즐겨찾기/실시간)를 관찰
                    bookmarkRepository.getParking(id).map { roomData ->
                        if (roomData != null) {
                            firestoreSpot.copy(
                                currentCnt = roomData.currentCnt
                            )
                        } else {
                            firestoreSpot
                        }
                    }
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    init{
        loadParkingData()
        refreshBookmarkRealtimeInfo()
    }

    fun onSpotSelected(spot: Parking){
        _selectedSpotID.value = spot.id
    }

    fun cleanSpot(){
        _selectedSpotID.value = null
    }

    fun loadParkingData(){
        viewModelScope.launch {
            getParkingUseCase().collectLatest { result ->
                when(result){
                    is DataResourceResult.Loading -> {
                        Log.d("Firestore", "데이터 로딩 중...")
                    }
                    is DataResourceResult.Success -> {
                        val spots = result.data
                        Log.d("Firestore", "데이터 로드 성공! 개수: ${spots.size}")
                        spots.forEach {
                            Log.d("Firestore", "주차장: ${it.name}, 좌표: ${it.latitude}, ${it.longitude}")
                        }
                        _parkingSpots.value = spots
                    }
                    is DataResourceResult.Failure -> {
                        Log.e("FireStore", "데이터 로드 실패: ${result.exception.message}")
                    }

                    DataResourceResult.DummyConstructor -> TODO()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val isBookmarked: StateFlow<Boolean> = selectedSpot
        .flatMapLatest{ parking ->
            if(parking == null){
                flowOf(false)
            }else{
                bookmarkRepository.isBookmarked(parking.id)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun toggleBookmark(){
        val currentSpot = selectedSpot.value ?: return

        viewModelScope.launch{
            if(isBookmarked.value){
                bookmarkRepository.removeBookmark(currentSpot)
            }else{
                bookmarkRepository.addBookmark(currentSpot)
            }
        }
    }

    fun refreshBookmarkRealtimeInfo(){
        viewModelScope.launch{
            bookmarkRepository.syncBookMarkInfo()
        }
    }
}
/*
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
    }*/
