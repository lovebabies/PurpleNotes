package com.example.purplenotes.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.purplenotes.R
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.data.Database
import com.example.purplenotes.data.NoteDatabase
import kotlinx.android.synthetic.main.fragment_get_note_by_id_demo.*
import javax.inject.Inject

class FragmentGetNoteByIdDemo: BaseFragment() {

    private val TAG = FragmentGetNoteByIdDemo::class.simpleName

    @Inject
    lateinit var db: NoteDatabase

    override fun initView() {
        btnGet.setOnClickListener {
            db.noteDao().getNoteByTitle(edtTitle.text.toString())
            Log.d(TAG, "Data get ${   db.noteDao().getNoteByTitle(edtTitle.text.toString())}")
        }
    }

    override fun initData() {

    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.fragment_get_note_by_id_demo

    companion object {
        fun newInstance() = FragmentGetNoteByIdDemo()
    }
}