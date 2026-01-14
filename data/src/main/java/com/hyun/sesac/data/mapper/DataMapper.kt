package com.hyun.sesac.data.mapper

import com.hyun.sesac.data.remote.dto.ParkingLotDTO
import com.hyun.sesac.domain.model.Parking

fun ParkingLotDTO.toDomainParking() = Parking(
    id = this.id,
    name = this.name,
    address = this.address
)

fun Parking.toFirestoreParkingLotDTO() = mapOf(
    "id" to this.id,
    "name" to this.name,
    "address" to this.address
)

fun List<ParkingLotDTO>.toDomainParkingLotList() =
    this.map { it.toDomainParking() }.toList()