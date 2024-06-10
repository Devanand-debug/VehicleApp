package com.devanand.vehicleapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class VehicleMasterPayload(
    val clientid: Int, // 11
    val enterprise_code: Int, // 1007
    val mno: String, // 9889897789
    val passcode: Int // 3476
)