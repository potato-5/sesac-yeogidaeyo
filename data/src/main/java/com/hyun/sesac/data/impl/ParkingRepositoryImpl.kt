package com.hyun.sesac.data.impl


/*
class ParkingRepositoryImpl @Inject constructor(
    private val apiService: ParkingApiService
) : ParkingRepository {

    override suspend fun getParkingSpots(placeName: String): Result<List<ParkingSpotModel>> {
        return try {
            val apiKey = BuildConfig.PUBLIC_PARKING_API_KEY

            val response = apiService.getCityData(apiKey,placeName)
            val dtoList = response.cityData?.parkingStatuses

            if (dtoList.isNullOrEmpty()) {
                Result.success(emptyList())
            } else {
                val domainList = dtoList.mapNotNull { dto ->
                    val lat = dto.lat?.toDoubleOrNull()
                    val lng = dto.lng?.toDoubleOrNull()

                    if (lat != null && lng != null) {
                        ParkingSpotModel(
                            prkNm = dto.parkingName ?: "이름 없음",
                            prkCd = dto.parkingCode,
                            lat = lat,
                            lng = lng
                        )
                    } else null
                }
                Result.success(domainList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

     private fun formatPrice(rates: String?, timeRates: String?): String {
        return if (!rates.isNullOrEmpty() && !timeRates.isNullOrEmpty()) {
            "${rates}원 / ${timeRates}분"
        } else {
            "정보없음"
        }
    }
}*/
