package com.example.autohub.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database
import com.example.autohub.data.storage.model.SearchHistoryDto

const val DATABASE_NAME = "autohub.db"
@Database (entities = [SearchHistoryDto::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        fun getDataBase(context: Context): DataBase {
            return Room.databaseBuilder(context, DataBase::class.java, DATABASE_NAME).build()
        }
    }
}