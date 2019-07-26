package com.example.purplenotes.ui

import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.purplenotes.R
import com.example.purplenotes.ViewModelFactory
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.data.database.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note_demo.*
import javax.inject.Inject

class FragmentAddNoteDemo: BaseFragment() {

    private val TAG = FragmentAddNoteDemo::class.simpleName
    private var viewModel: AddNoteViewModel? = null

    @Inject
    lateinit var db: NoteDatabase

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getViewRes(): Int = R.layout.fragment_add_note_demo

    override fun initData() {

    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun initView() {
        btnAdd.setOnClickListener {
            if (!edtTitle.text.isNullOrEmpty() && !edtContent.text.isNullOrEmpty()) {
                viewModel?.addNote(edtTitle.text.toString(), edtContent.text.toString())
            }
        }

    }

    override fun getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(AddNoteViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentAddNoteDemo()
    }
}