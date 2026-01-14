package com.hyun.sesac.domain.usecase.firestore

import com.hyun.sesac.domain.common.DataResourceResult
import com.hyun.sesac.domain.model.Parking
import com.hyun.sesac.domain.repository.ParkingRepository
import kotlinx.coroutines.flow.Flow

class GetParkingUseCase(val parkingRepository: ParkingRepository){
    operator fun invoke() : Flow<DataResourceResult<List<Parking>>>{
        return parkingRepository.read()
    }
}