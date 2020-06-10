package com.example.readinglist

import android.app.Application
import androidx.lifecycle.*
import com.example.readinglist.model.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel (application: Application) : AndroidViewModel(application) {
    private val entryDao = EntryRoomDatabase.getDatabase(application, viewModelScope).entryDao()
    private val repository: EntryRepository = EntryRepository(entryDao)

    val allEntries: LiveData<List<Entry>> = repository.allEntries
    val twitterEntries: LiveData<List<Entry>> = repository.twitterEntries
    val notesEntries: LiveData<List<Entry>> = repository.notesEntries
    val articlesEntries: LiveData<List<Entry>> = repository.articlesEntries

    var entriesToDisplay = MutableLiveData<List<Entry>>()
    var currentMode: Int = R.id.all

    fun insert(entry: Entry) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entry)
    }

    fun delete(entry: Entry) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(entry)
    }

    fun getEntries():LiveData<List<Entry>>{
        when(currentMode) {
            R.id.twitter -> entriesToDisplay.value = twitterEntries.value
            R.id.articles -> entriesToDisplay.value = articlesEntries.value
            R.id.notes -> entriesToDisplay.value = notesEntries.value
            else -> entriesToDisplay.value = allEntries.value
        }
        return entriesToDisplay
    }

    fun showTweets() {currentMode = R.id.twitter}
    fun showArticles() {currentMode = R.id.articles}
    fun showNotes() {currentMode = R.id.notes}
    fun showAll() {currentMode = R.id.all}

}