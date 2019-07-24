package com.example.purplenotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.purplenotes.data.Database
import com.example.purplenotes.data.NoteDatabase
import kotlinx.android.synthetic.main.fragment_delete_demo.*

class FragmentDeleteDemo: Fragment() {
    private val TAG = FragmentDeleteDemo::class.simpleName
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_delete_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = context?.let { Database.buildInstance(it) } as NoteDatabase
        btnDelete.setOnClickListener {
            db.noteDao().deleteNote(edtIdToDelete.text.toString().toInt())
            Log.d(TAG, "Data after insert ${db?.noteDao()?.getAllNotes()}")
        }
    }
    companion object {
        fun newInstance() = FragmentDeleteDemo()
    }
}