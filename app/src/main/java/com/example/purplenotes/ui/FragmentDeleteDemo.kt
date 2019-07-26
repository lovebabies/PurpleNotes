package com.example.purplenotes.ui

import android.util.Log
import com.example.purplenotes.R
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.data.database.NoteDatabase
import kotlinx.android.synthetic.main.fragment_delete_demo.*
import javax.inject.Inject

class FragmentDeleteDemo: BaseFragment() {

    private val TAG = FragmentDeleteDemo::class.simpleName

    @Inject
    lateinit var db: NoteDatabase

    override fun initView() {
        btnDelete.setOnClickListener {
            db.noteDao().deleteNote(edtIdToDelete.text.toString().toInt())
            Log.d(TAG, "Data after insert ${db?.noteDao()?.getAllNotesTest()}")
        }
    }

    override fun initData() {

    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.fragment_delete_demo

    override fun getViewModel() {

    }

    companion object {
        fun newInstance() = FragmentDeleteDemo()
    }
}