package com.example.purplenotes.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.purplenotes.data.database.Note

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var mNoteList: List<Note>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

    }

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}