package com.example.autohub.data.storage

import android.content.Context
import com.example.autohub.data.database.DataBase
import com.example.autohub.data.storage.model.SearchHistoryDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchHistoryStorageImpl(context: Context) : SearchHistoryStorage {

    private val database: DataBase

    init {
        database = DataBase.getDataBase(context)
    }


    override suspend fun loadSearchHistory(): List<SearchHistoryDto> {
        return withContext(Dispatchers.IO) {
            database.getDao().getSearchHistory()
        }
    }

    override suspend fun updateSearchHistory(query: String) {
        withContext(Dispatchers.IO) {
            val searchHistoryDto = SearchHistoryDto(null, query)

            val searchHistory = loadSearchHistory()
            val updatedList = mutableListOf<SearchHistoryDto>()

            updatedList.add(0, searchHistoryDto)

            updatedList.addAll(searchHistory)

            if (updatedList.size > 10) {
                updatedList.subList(10, updatedList.size).clear()
            }

            database.getDao().clearSearchHistory()

            updatedList.forEach { item ->
                database.getDao().insertSearchHistoryItem(item)
            }
        }
    }


    override suspend fun clearSearchHistory() {
        withContext(Dispatchers.IO) {
            database.getDao().clearSearchHistory()
        }
    }

}