package com.example.purplenotes.ui

import android.util.Log
import com.example.purplenotes.R
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.data.database.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note_demo.*
import javax.inject.Inject

class FragmentAddNoteDemo: BaseFragment() {
    private val TAG = FragmentAddNoteDemo::class.simpleName

    @Inject
    lateinit var db: NoteDatabase

    override fun getViewRes(): Int = R.layout.fragment_add_note_demo

    override fun initData() {

    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun initView() {
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