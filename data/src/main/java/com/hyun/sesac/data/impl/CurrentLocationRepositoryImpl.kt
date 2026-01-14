package com.hyun.sesac.data.impl

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.hyun.sesac.data.mapper.toDomain
import com.hyun.sesac.domain.model.UserLocationModel
import com.hyun.sesac.domain.repository.CurrentLocationRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import kotlin.let

class CurrentLocationRepositoryImpl(context: Context) : CurrentLocationRepository {
    //private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    //private val _latestLocation = MutableStateFlow<UserLocationModel?>(null)
    //val latestLocationState: Flow<UserLocationModel?> = _latestLocation.asStateFlow()
    /*fun updateLatestLocation(location: UserLocationModel) {
        _latestLocation.value = location
    }*/

    // TODO 여기서 위 3개를 주석처리 후 아래 fusedLocationClient 한줄로 변경한 이유는 ??
    // TODO 즉시 초기화 vs 지연 초기화 ( context 누수 방지 + 최적화 )
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context.applicationContext)
//    private val fusedLocationClient: FusedLocationProviderClient by lazy {
//        LocationServices.getFusedLocationProviderClient(context)
//    }

    @SuppressLint("MissingPermission")
    override  fun getCurrentLocationUpdates(): Flow<UserLocationModel> = callbackFlow {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            TimeUnit.SECONDS.toMillis(10)
        ).build()
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                /**
                 * 가장 최신 위치 정보만을 갸져옴
                 */
                locationResult.lastLocation?.let { location ->
                    trySend(location.toDomain()).isSuccess
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        /**
         * Flow 가 취소(Coroutine Finish)될 때  위치 갱신 중지
         */
        awaitClose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
    /**
     * Tutor Pyo
     *  - 더 복잡한 앱에서는 Repository 내부에서 상태를 관리할 수도 있음
     *  - 여기서는 Service Life Cycle에 맞춰 외부에서 직접 호출
     *  - getCurrentLocationUpdates() 가 호출되면 자동시작되므로
     *  - 현재 구현에서는 비워뒀음
     */
    //override suspend fun startLocationUpdates() {}
    /**
     * 현재 코드는 Flow가 취소되면 awaitClose 블록에서 자동으로 중지되므로
     * 현재는 비워둠
     */
    //override suspend fun stopLocationUpdates() {}
}