package com.example.readinglist

import androidx.lifecycle.LiveData
import com.example.readinglist.model.Entry

class EntryRepository (private val entryDao: EntryDao) {
    val allEntries: LiveData<List<Entry>> = entryDao.getAll()
    val twitterEntries: LiveData<List<Entry>> = entryDao.getTweets()
    val notesEntries: LiveData<List<Entry>> = entryDao.getNotes()
    val articlesEntries: LiveData<List<Entry>> = entryDao.getArticles()

    suspend fun insert(entry: Entry) {
        entryDao.insert(entry)
    }

    suspend fun delete(entry: Entry) {
        entryDao.delete(entry)
    }
}