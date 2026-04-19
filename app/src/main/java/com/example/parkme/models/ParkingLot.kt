package com.example.parkme.models

import com.google.android.gms.maps.model.LatLng
data class ParkingLot(
    val id: String,
    val name: String,
    val location: LatLng,
    val pricePerMin: String,
    val pricePerHour: String,

    val fixedPrice: String,
    val terms: String,
    val electricCharges: Boolean,
    val hourStart: String,
    val hourFinish: String,
    val weekAvailability: String,
    val slot: Int
)