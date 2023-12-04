package com.putragandad.roomexercise.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.putragandad.roomexercise.R
import com.putragandad.roomexercise.adapters.NoteClickDeleteInterface
import com.putragandad.roomexercise.adapters.NoteClickInterface
import com.putragandad.roomexercise.adapters.NotesRVAdapter
import com.putragandad.roomexercise.models.Notes
import com.putragandad.roomexercise.viewmodels.NotesViewModel

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var viewModel: NotesViewModel
    lateinit var notesRV : RecyclerView
    lateinit var addFAB: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRV = findViewById(R.id.notes_rv)
        addFAB = findViewById(R.id.fab_add)

        notesRV.layoutManager = LinearLayoutManager(this)

        val notesRVAdapter = NotesRVAdapter(this, this, this)

        notesRV.adapter = notesRVAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)

        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                notesRVAdapter.updateList(it)
            }
        })

        addFAB.setOnClickListener{
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(notes: Notes) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", notes.noteTitle)
        intent.putExtra("noteDescription", notes.noteDescription)
        intent.putExtra("noteId", notes.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this, "Notes Deleted", Toast.LENGTH_LONG).show()
    }
}