package com.example.purplenotes.ui.home

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.solver.widgets.Analyzer.setPosition
import androidx.recyclerview.widget.RecyclerView
import com.example.purplenotes.R
import com.example.purplenotes.data.database.Note

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var mNoteList: ArrayList<Note> = ArrayList<Note>()
    private var position = 0
    private var mListener: OnClickEditListener? = null

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
        notifyDataSetChanged()
    }

    fun addNotes(list: ArrayList<Note>) {
        mNoteList.addAll(list)
        notifyDataSetChanged()
    }

    fun deleteNote(position: Int) {
        mNoteList.removeAt(position)
        notifyDataSetChanged()
    }

    fun getPosition(): Int {
        return position
    }

    fun setPosition(pos: Int) {
        position = pos
    }

    fun setOnClickEditListener(listener: OnClickEditListener) {
        mListener = listener
    }

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        private var mTitle = view.findViewById<TextView>(R.id.tvNoteTitle)
        private var mContent = view.findViewById<TextView>(R.id.tvNoteContent)
        private var mOptions = view.findViewById<ImageView>(R.id.imgOptions)
        init {
            mOptions.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(0, 0, 0, "Edit")
            menu?.add(0, 1, 0, "Delete")
        }

        fun bind(note: Note) {
            mTitle.text = note.title
            mContent.text = note.content
            mOptions.setOnLongClickListener {
                setPosition(this.adapterPosition)
                mListener?.onClickEdit(note.title, note.content)
                false
            }
        }
    }


    interface OnClickEditListener {
        fun onClickEdit(title: String, content: String)
    }
}