package com.example.purplenotes.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.purplenotes.R
import com.example.purplenotes.data.database.Note

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var mNoteList: ArrayList<Note> = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note_infomation, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = mNoteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(mNoteList[position])
    }

    fun setNoteList(list: ArrayList<Note>) {
        mNoteList = list
    }

    fun addNotes(list: ArrayList<Note>) {
        mNoteList.addAll(list)
    }

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var mTitle = view.findViewById<TextView>(R.id.tvNoteTitle)
        private var mContent = view.findViewById<TextView>(R.id.tvNoteContent)
        fun bind(note: Note) {
            mTitle.text = note.title
            mContent.text = note.content
        }
    }
}