package com.hyun.sesac.domain.repository

import com.hyun.sesac.domain.common.DataResourceResult
import com.hyun.sesac.domain.model.Parking
import com.hyun.sesac.domain.model.ParkingSpotModel
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {
    //suspend fun getParkingSpots(placeName: String): Result<List<ParkingSpotModel>>

    fun create(parkingInfo: Parking) : Flow<DataResourceResult<Unit>>
    fun read() : Flow<DataResourceResult<List<Parking>>>
    fun update(parkingInfo: Parking) : Flow<DataResourceResult<Unit>>

    fun delete(parkingID: String) : Flow<DataResourceResult<Unit>>
}