package com.hyun.sesac.data.mapper

import com.google.firebase.firestore.GeoPoint
import com.hyun.sesac.data.remote.dto.ParkingLotDTO
import com.hyun.sesac.domain.model.Parking

fun ParkingLotDTO.toDomainParking() = Parking(
    id = this.parkingCd,
    name = this.parkingNm,
    address = this.address,
    latitude = this.location?.latitude ?: 0.0,
    longitude = this.location?. longitude ?: 0.0
)

fun Parking.toFirestoreParkingLotDTO() = mapOf(
    "parking_cd" to this.id,
    "parking_nm" to this.name,
    "address" to this.address,
    "location" to GeoPoint(this.latitude, this.longitude)
)

fun List<ParkingLotDTO>.toDomainParkingLotList() =
    this.map { it.toDomainParking() }.toList()