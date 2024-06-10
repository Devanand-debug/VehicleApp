package com.devanand.vehicleapp.ui.dashboard

import androidx.lifecycle.ViewModel
import com.devanand.vehicleapp.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel  @Inject constructor(private val vehicleRepository: VehicleRepository) : ViewModel() {
}