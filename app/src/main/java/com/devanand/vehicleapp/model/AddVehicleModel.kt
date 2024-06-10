package com.devanand.vehicleapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class AddVehicleModel(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("vehicle_type") var vehicleType: ArrayList<VehicleType> = arrayListOf(),
    @SerializedName("vehicle_capacity") var vehicleCapacity: ArrayList<VehicleCapacity> = arrayListOf(),
    @SerializedName("vehicle_make") var vehicleMake: ArrayList<VehicleMake> = arrayListOf(),
    @SerializedName("manufacture_year") var manufactureYear: ArrayList<ManufactureYear> = arrayListOf(),
    @SerializedName("fuel_type") var fuelType: ArrayList<FuelType> = arrayListOf()
)

data class VehicleType(
    @SerializedName("text") var text: String? = null,
    @SerializedName("value") var value: Int? = null,
    @SerializedName("images") var images: String? = null
)

data class VehicleCapacity(
    @SerializedName("text") var text: String? = null,
    @SerializedName("value") var value: Int? = null,
    @SerializedName("images") var images: String? = null
)

data class VehicleMake(
    @SerializedName("text") var text: String? = null,
    @SerializedName("value") var value: Int? = null,
    @SerializedName("images") var images: String? = null
)

data class ManufactureYear(
    @SerializedName("text") var text: String? = null,
    @SerializedName("value") var value: Int? = null,
    @SerializedName("images") var images: String? = null
)

data class FuelType(
    @SerializedName("text") var text: String? = null,
    @SerializedName("value") var value: Int? = null,
    @SerializedName("images") var images: String? = null
)
