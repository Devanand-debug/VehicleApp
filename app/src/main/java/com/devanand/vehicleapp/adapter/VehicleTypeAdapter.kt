package com.devanand.vehicleapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devanand.vehicleapp.R

class VehicleTypeAdapter :  RecyclerView.Adapter<VehicleTypeAdapter.MyViewHolder>() {

    private var dataList: MutableList<String> = mutableListOf()
    private var showExtraItems: Boolean = false

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.vehicleIv)
        val textView: TextView = itemView.findViewById(R.id.vehicleTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageResource(R.drawable.fire_truck)
        holder.textView.text = dataList[position]
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(data: ArrayList<String>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    fun showExtraItems() {
        showExtraItems = true
        notifyDataSetChanged()
    }

}