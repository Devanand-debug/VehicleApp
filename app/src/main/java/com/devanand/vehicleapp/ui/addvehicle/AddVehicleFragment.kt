package com.devanand.vehicleapp.ui.addvehicle

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.devanand.vehicleapp.R
import com.devanand.vehicleapp.adapter.VehicleTypeAdapter
import com.devanand.vehicleapp.databinding.FragmentAddvehicleBinding
import com.devanand.vehicleapp.utils.NetworkResult
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class AddVehicleFragment : Fragment() {

    private var _binding: FragmentAddvehicleBinding? = null

    val addVehicleViewModel : AddVehicleViewModel by viewModels<AddVehicleViewModel>()

    private val binding get() = _binding!!

    private lateinit var adapter: VehicleTypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddvehicleBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = listOf("Truck", "Semi Truck", "Dump Truck") // Sample data

        adapter = VehicleTypeAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),3, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView.adapter = adapter

      //  adapter.setData(dataList)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_AddVehicleFragment_to_DashboardFragment)
        }

        binding.editTextNumber.setOnClickListener {
            startBarcodeScanning()
        }

        binding.toggleButton.setOnClickListener {
           // binding.recyclerView.visibility = View.VISIBLE

            if (binding.recyclerView.visibility == View.VISIBLE) {
                binding.recyclerView.visibility  = View.GONE
                binding.toggleButton.text = "+"
            } else {
                binding.recyclerView.visibility  = View.VISIBLE
                binding.toggleButton.text = "-"
            }
        }
       // binding.recyclerView.visibility = View.GONE

        addVehicleViewModel.vehicleMasterStateFlow.observe(viewLifecycleOwner){
            when(it) {
                    is NetworkResult.Success ->{
                        try {
                            val vehicleTypeList = it.data?.vehicleType
                            val stringList =
                                vehicleTypeList?.map { it.text.toString() } ?: emptyList()

                            val firstThree = stringList.subList(0, 3)
                            val remainingList =
                                stringList.size.let { it1 ->
                                    stringList.subList(3,
                                        it1
                                    )
                                }
                            adapter.setData(ArrayList(remainingList))

                            binding.tv1.vehicleTv.text = firstThree[0]
                            binding.tv2.vehicleTv.text = firstThree[1]
                            binding.tv3.vehicleTv.text = firstThree[2]

                            Log.e("vehicleRes1", stringList.toString())
                            Log.e("vehicleRes2", firstThree.toString())
                            Log.e("vehicleRes3", remainingList.toString())
                        }catch (e : Exception){}
                    }

                    is NetworkResult.Error -> {
                        try {
                        Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                        }catch (e : Exception){}
                    }
                    is NetworkResult.Loading -> {

                    }
                }
        }

    }

    private fun startBarcodeScanning() {
        val integrator = IntentIntegrator(requireActivity())
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES) // Scan all types of barcodes
        integrator.setPrompt("Scan a barcode")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Retrieve scan result
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Log.e("result = ", "$result")
        if (result != null) {
            if (result.contents == null) {
                // Handle canceled scan
            } else {
                // Handle scan results
                val scannedText = result.contents // Scanned text (barcode content)
                binding.editTextNumber.setText(scannedText)
                // Do something with the scanned text
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}