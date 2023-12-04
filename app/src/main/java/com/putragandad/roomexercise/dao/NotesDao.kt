package com.putragandad.roomexercise.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.putragandad.roomexercise.models.Notes

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes() : LiveData<List<Notes>>

    @Update
    suspend fun update(notes: Notes)
}