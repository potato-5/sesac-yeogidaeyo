package com.hyun.sesac.data.remote.dto

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class ParkingLotDTO(
    @DocumentId
    val id: String = "",

    @get:PropertyName("parking_name") @set:PropertyName("parking_name")
    var name: String = "",

    @get:PropertyName("address") @set:PropertyName("address")
    var address : String = ""
)
