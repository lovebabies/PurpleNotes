package com.example.purplenotes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_note_demo.*

class FragmentAddNoteDemo: Fragment() {
    private val TAG = FragmentAddNoteDemo::class.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_add_note_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var helper = context?.let { NoteSqliteHelper(it) }
        btnAdd.setOnClickListener {
            if (!edtTitle.text.isNullOrEmpty() && !edtContent.text.isNullOrEmpty()) {
                helper?.addNote(Note(edtTitle.text.toString(), edtContent.text.toString()))
                Log.d(TAG, "total data is ${helper?.getAllNote()}")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentAddNoteDemo()
    }
}