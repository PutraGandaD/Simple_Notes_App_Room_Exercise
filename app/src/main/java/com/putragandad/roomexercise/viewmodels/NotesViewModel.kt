package com.putragandad.roomexercise.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.putragandad.roomexercise.database.NotesDatabase
import com.putragandad.roomexercise.models.Notes
import com.putragandad.roomexercise.repositories.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application)
    : AndroidViewModel(application){
        var allNotes: LiveData<List<Notes>>
        val repository : NotesRepository

        init {
            val dao = NotesDatabase.getDatabase(application).getNotesDao()
            repository = NotesRepository(dao)
            allNotes = repository.allNotes
        }

    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }

    fun updateNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(notes)
    }

    fun addNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notes)
    }
}