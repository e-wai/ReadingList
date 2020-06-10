package com.example.readinglist

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.readinglist.model.Entry

@Dao
interface EntryDao {
    @Query("SELECT * from stored")
    fun getAll(): LiveData<List<Entry>>

    @Query("SELECT * from stored WHERE type = 'TWEET'")
    fun getTweets(): LiveData<List<Entry>>

    @Query("SELECT * from stored WHERE type = 'NOTE'")
    fun getNotes(): LiveData<List<Entry>>

    @Query("SELECT * from stored WHERE type = 'ARTICLE'")
    fun getArticles(): LiveData<List<Entry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: Entry)

    @Delete
    suspend fun delete(entry: Entry)
}