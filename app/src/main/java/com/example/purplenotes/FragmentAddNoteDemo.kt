package com.example.purplenotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.purplenotes.data.Database
import com.example.purplenotes.data.Note
import com.example.purplenotes.data.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note_demo.*

class FragmentAddNoteDemo: Fragment() {
    private val TAG = FragmentAddNoteDemo::class.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_add_note_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = context?.let { Database.buildInstance(it) } as NoteDatabase
        btnAdd.setOnClickListener {
            if (!edtTitle.text.isNullOrEmpty() && !edtContent.text.isNullOrEmpty()) {
                db?.noteDao()?.insertNote(
                    Note(
                        edtTitle.text.toString(),
                        edtContent.text.toString()
                    )
                )
                Log.d(TAG, "Data after insert ${db?.noteDao()?.getAllNotes()}")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentAddNoteDemo()
    }
}