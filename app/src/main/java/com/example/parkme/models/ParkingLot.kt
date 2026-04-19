package com.example.parkme.models

import com.google.android.gms.maps.model.LatLng

data class ParkingLot(
    val id: String,
    val name: String,
    val location: LatLng,
    val pricePerHour: String,
    val availableSpots: Int
)
