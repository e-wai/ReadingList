package com.example.readinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.readinglist.model.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel (application: Application) : AndroidViewModel(application) {
    val entryDao = EntryRoomDatabase.getDatabase(application, viewModelScope).entryDao()
    private val repository: EntryRepository = EntryRepository(entryDao)
    val allEntries: LiveData<List<Entry>> = repository.allEntries

    fun insert(entry: Entry) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entry)
    }

    fun delete(entry: Entry) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entry)
    }
}