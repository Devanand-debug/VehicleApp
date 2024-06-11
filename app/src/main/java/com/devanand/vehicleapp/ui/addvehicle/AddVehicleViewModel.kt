package com.devanand.vehicleapp.ui.addvehicle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devanand.vehicleapp.model.AddVehicleModel
import com.devanand.vehicleapp.model.VehicleMasterPayload
import com.devanand.vehicleapp.repository.VehicleRepository
import com.devanand.vehicleapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AddVehicleViewModel @Inject constructor(private val vehicleRepository: VehicleRepository) : ViewModel(){
//    val vehicleMasterData: StateFlow<NetworkResult<AddVehicleModel>>
//        get() = vehicleRepository.vehicleMasterStateFlow

   // val mVehicleData = vehicleRepository.vehicleMasterStateFlow

    val payload = VehicleMasterPayload(
        clientid = 11,
        mno = "9889897789",
        passcode = 3476,
        enterprise_code = 1007
    )



    private val _vehicleMasterStateFlow = MutableLiveData<NetworkResult<AddVehicleModel>>(NetworkResult.Loading())
    val vehicleMasterStateFlow: LiveData<NetworkResult<AddVehicleModel>> = _vehicleMasterStateFlow

    init {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                vehicleRepository.addVehicleAPICall(payload)
//            }
//        }
        getVehicleData()
    }


    fun getVehicleData(){
        viewModelScope.launch {
            _vehicleMasterStateFlow.postValue(NetworkResult.Loading())

            val response = vehicleRepository.addVehicleAPICall(payload)

            if (response.isSuccessful && response.body() != null) {
                _vehicleMasterStateFlow.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _vehicleMasterStateFlow.postValue(NetworkResult.Error(errorObj.getString("message")))
            } else {
                _vehicleMasterStateFlow.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        }
    }

}