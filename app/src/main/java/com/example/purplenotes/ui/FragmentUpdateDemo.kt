package com.example.purplenotes.ui

import android.util.Log
import com.example.purplenotes.R
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.data.database.NoteDatabase
import kotlinx.android.synthetic.main.fragment_update_demo.*
import javax.inject.Inject

class FragmentUpdateDemo: BaseFragment() {

    private val TAG = FragmentUpdateDemo::class.simpleName

    @Inject
    lateinit var db: NoteDatabase

    override fun initView() {
        btnUpdate.setOnClickListener {
            db.noteDao().updateNote(edtUpdateId.text.toString().toInt(), edtTitle.text.toString(), edtContent.text.toString())
            Log.d(TAG, "Data after insert ${db?.noteDao()?.getAllNotes()}")
        }
    }

    override fun initData() {

    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.fragment_update_demo

    companion object {
        fun newInstance() = FragmentUpdateDemo()
    }
}