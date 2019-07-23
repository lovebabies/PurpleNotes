package com.example.purplenotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_update_demo.*

class FragmentUpdateDemo: Fragment() {
    private val TAG = FragmentUpdateDemo::class.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = context?.let { Database.buildInstance(it) } as NoteDatabase
        btnUpdate.setOnClickListener {
            db.noteDao().updateNote(edtUpdateId.text.toString().toInt(), edtTitle.text.toString(), edtContent.text.toString())
            Log.d(TAG, "Data after insert ${db?.noteDao()?.getAllNotes()}")
        }
    }

    companion object {
        fun newInstance() = FragmentUpdateDemo()
    }
}