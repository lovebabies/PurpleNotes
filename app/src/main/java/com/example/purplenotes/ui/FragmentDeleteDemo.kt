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
import kotlinx.android.synthetic.main.fragment_delete_demo.*
import javax.inject.Inject

class FragmentDeleteDemo: BaseFragment() {
    private val TAG = FragmentDeleteDemo::class.simpleName

    @Inject
    lateinit var db: NoteDatabase

    override fun initView() {
        btnDelete.setOnClickListener {
            db.noteDao().deleteNote(edtIdToDelete.text.toString().toInt())
            Log.d(TAG, "Data after insert ${db?.noteDao()?.getAllNotes()}")
        }
    }

    override fun initData() {

    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.fragment_delete_demo

    companion object {
        fun newInstance() = FragmentDeleteDemo()
    }
}