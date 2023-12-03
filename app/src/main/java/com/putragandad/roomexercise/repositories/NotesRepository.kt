package com.putragandad.roomexercise.repositories

import androidx.lifecycle.LiveData
import com.putragandad.roomexercise.dao.NotesDao
import com.putragandad.roomexercise.models.Notes

class NotesRepository(private val notesDao: NotesDao) {
    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insert(notes: Notes) {
        notesDao.insert(notes)
    }

    suspend fun delete(notes: Notes) {
        notesDao.delete(notes)
    }

    suspend fun update(notes: Notes) {
        notesDao.update(notes)
    }
}