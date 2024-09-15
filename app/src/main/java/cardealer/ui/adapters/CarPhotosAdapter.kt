package com.example.autohub.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autohub.R
import com.example.autohub.databinding.FragmentCarPhotoItemBinding

class CarPhotosAdapter :
    ListAdapter<String, CarPhotosAdapter.PhotoViewHolder>(DiffUtilCarPhotoCallback()) {

    class PhotoViewHolder(private val binding: FragmentCarPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photoUrl: String) {
            binding.carPhoto.load(photoUrl) {
                error(R.drawable.error)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            FragmentCarPhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class DiffUtilCarPhotoCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
}

