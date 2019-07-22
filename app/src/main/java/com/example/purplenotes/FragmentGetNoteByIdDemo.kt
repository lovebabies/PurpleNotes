package com.example.purplenotes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_get_note_by_id_demo.*

class FragmentGetNoteByIdDemo: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_get_note_by_id_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var helper = context?.let { NoteSqliteHelper(it) }
        btnGet.setOnClickListener {
            Toast.makeText(context, "result is ${helper?.getNoteByTitle(edtTitle.text.toString())}", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance() = FragmentGetNoteByIdDemo()
    }
}