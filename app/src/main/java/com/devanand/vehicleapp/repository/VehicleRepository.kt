package com.devanand.vehicleapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devanand.vehicleapp.api.VehicleAPI
import com.devanand.vehicleapp.model.AddVehicleModel
import com.devanand.vehicleapp.model.VehicleMasterPayload
import com.devanand.vehicleapp.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import javax.inject.Inject

class VehicleRepository @Inject constructor(private val vehicleApi:VehicleAPI) {

    private val _vehicleMasterStateFlow = MutableLiveData<NetworkResult<AddVehicleModel>>(NetworkResult.Loading())
   val vehicleMasterStateFlow: LiveData<NetworkResult<AddVehicleModel>> = _vehicleMasterStateFlow

    suspend fun addVehicleAPICall(payload: VehicleMasterPayload): MutableLiveData<NetworkResult<AddVehicleModel>> {
        _vehicleMasterStateFlow.postValue(NetworkResult.Loading())
        val response = vehicleApi.addVehicle(payload)
        Log.d("TAG", "addVehicleAPICall: ${response.body()?.vehicleType}")

        if (response.isSuccessful && response.body() != null) {
            _vehicleMasterStateFlow.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _vehicleMasterStateFlow.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _vehicleMasterStateFlow.postValue(NetworkResult.Error("Something Went Wrong"))
        }
        return _vehicleMasterStateFlow
    }
}