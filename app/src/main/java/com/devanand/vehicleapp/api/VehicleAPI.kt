package com.devanand.vehicleapp.api

import com.devanand.vehicleapp.model.AddVehicleModel
import com.devanand.vehicleapp.model.VehicleMasterPayload
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface VehicleAPI {
    @POST("task")
    suspend fun addVehicle(@Body payload: VehicleMasterPayload):Response<AddVehicleModel>
}