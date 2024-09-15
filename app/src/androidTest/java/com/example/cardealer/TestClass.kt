@file:Suppress("DEPRECATION")

package com.example.cardealer

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.autohub.data.database.Dao
import com.example.autohub.data.database.DataBase
import com.example.autohub.data.storage.model.SearchHistoryDto
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(JUnit4ClassRunner::class)
class TestClass {
    private lateinit var database: DataBase
    private lateinit var databaseDao: Dao

    @Before
    fun initDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            DataBase::class.java
        ).build()

        databaseDao = database.getDao()
    }

    @Test
    fun should_insert_data_in_db(): Unit = runBlocking{
       val entity = SearchHistoryDto(id = 1, query = "something")

        databaseDao.insertSearchHistoryItem(entity)

        val list = databaseDao.getSearchHistory()

        Assert.assertEquals(entity, list.first())
    }

    @After
    fun clear() {
        database.close()
    }
}