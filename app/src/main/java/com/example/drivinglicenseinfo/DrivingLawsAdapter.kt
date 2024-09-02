package com.example.drivinglicenseinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drivinglicenseinfo.databinding.ItemDrivingLawBinding

class DrivingLawsAdapter(private val lawsList: List<DrivingLaw>) :
    RecyclerView.Adapter<DrivingLawsAdapter.DrivingLawViewHolder>() {

    inner class DrivingLawViewHolder(private val binding: ItemDrivingLawBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(drivingLaw: DrivingLaw) {
            binding.textViewOffense.text = drivingLaw.offense
            binding.textViewPenalty.text = drivingLaw.penalty
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrivingLawViewHolder {
        val binding = ItemDrivingLawBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DrivingLawViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrivingLawViewHolder, position: Int) {
        holder.bind(lawsList[position])
    }

    override fun getItemCount(): Int = lawsList.size
}
