package com.example.autohub.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.autohub.R
import com.example.autohub.databinding.SearchHistoryListItemBinding
import com.example.autohub.domain.model.SearchHistoryVo

class SearchHistoryAdapter(private val onAdapterItemClick: OnAdapterItemClick<SearchHistoryVo>) :
    ListAdapter<SearchHistoryVo, SearchHistoryAdapter.SearchHistoryViewHolder>(
        DiffUtilISearchHistoryCallback()
    ) {
    class SearchHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SearchHistoryListItemBinding.bind(view)

        fun bind(searchHistoryVo: SearchHistoryVo) = with(binding) {
            searchHistoryText.text = searchHistoryVo.query
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_history_list_item, parent, false)
        return SearchHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onAdapterItemClick.onClick(getItem(position))
        }
    }
}

class DiffUtilISearchHistoryCallback : DiffUtil.ItemCallback<SearchHistoryVo>() {
    override fun areItemsTheSame(oldItem: SearchHistoryVo, newItem: SearchHistoryVo) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: SearchHistoryVo, newItem: SearchHistoryVo) =
        oldItem == newItem
}