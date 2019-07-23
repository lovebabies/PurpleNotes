package com.example.purplenotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_get_note_by_id_demo.*

class FragmentGetNoteByIdDemo: Fragment() {
    private val TAG = FragmentGetNoteByIdDemo::class.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_get_note_by_id_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = context?.let { Database.buildInstance(it) } as NoteDatabase
        btnGet.setOnClickListener {
            db.noteDao().getNoteByTitle(edtTitle.text.toString())
            Log.d(TAG, "Data get ${   db.noteDao().getNoteByTitle(edtTitle.text.toString())}")
        }
    }

    companion object {
        fun newInstance() = FragmentGetNoteByIdDemo()
    }
}