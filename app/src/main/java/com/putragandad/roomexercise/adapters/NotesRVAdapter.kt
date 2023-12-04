package com.putragandad.roomexercise.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.roomexercise.R
import com.putragandad.roomexercise.models.Notes

class NotesRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NotesRVAdapter.ViewHolder>()
{
    private val allNotes = ArrayList<Notes>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notesTV = itemView.findViewById<TextView>(R.id.tv_notes_title)
        val datesTV = itemView.findViewById<TextView>(R.id.tv_notes_timestamp)
        val deleteIV = itemView.findViewById<ImageView>(R.id.iv_btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notesTV.setText(allNotes.get(position).noteTitle)
        holder.datesTV.setText("Last Updated : " + allNotes.get(position).timeStamp)
        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    fun updateList(newList: List<Notes>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(notes: Notes)
}

interface NoteClickInterface {
    fun onNoteClick(notes: Notes)
}