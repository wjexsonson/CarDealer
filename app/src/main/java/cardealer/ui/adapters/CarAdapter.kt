package com.example.autohub.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autohub.R
import com.example.autohub.databinding.CarListItemBinding
import com.example.autohub.domain.model.CarVo

class CarAdapter(private val onAdapterItemClick: OnAdapterItemClick<CarVo>) :
    ListAdapter<CarVo, CarAdapter.CarViewHolder>(DiffUtilCarCallback()) {

    class CarViewHolder(
        private val binding: CarListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(car: CarVo) = with(binding) {
            carTitle.text = root.context.getString(R.string.car_info, car.make, car.model, car.year)
            carPrice.text = car.price
            carPhoto.load(car.primaryPhotoUrl) {
                error(R.drawable.error)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onAdapterItemClick.onClick(getItem(position))
        }
    }
}

class DiffUtilCarCallback : DiffUtil.ItemCallback<CarVo>() {
    override fun areItemsTheSame(oldItem: CarVo, newItem: CarVo) = oldItem == newItem
    override fun areContentsTheSame(oldItem: CarVo, newItem: CarVo) = oldItem == newItem
}