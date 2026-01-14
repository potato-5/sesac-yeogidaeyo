package com.hyun.sesac.domain.usecase.map

import com.hyun.sesac.domain.model.ParkingSpotModel
import com.hyun.sesac.domain.repository.ParkingRepository
import javax.inject.Inject

class GetParkingSpotsUseCase @Inject constructor(
    private val repository: ParkingRepository
) {
    suspend operator fun invoke(placeName: String = "광화문·덕수궁"): Result<List<ParkingSpotModel>>{
        return repository.getParkingSpots(placeName)
    }
}

/*
class GetRecommendListUseCase(
    private val repository: RecommendRepository
) {
    operator fun invoke(): Flow<List<AiRecommendModel>> {
        return repository.getRecommendations()
    }
}
*/
