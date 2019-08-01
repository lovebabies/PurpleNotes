package com.example.purplenotes.ui.updatenotes

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note.NOTE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.purplenotes.R
import com.example.purplenotes.ViewModelFactory
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_update_note.*
import javax.inject.Inject

/**
 * Author: Jayden Nguyen
 * Created date: 8/1/2019
 */

const val NOTE_ID = "note_id"
const val NOTE_TITLE = "note_title"
const val NOTE_CONTENT = "note_content"

class UpdateNoteFragment: BaseFragment() {
    private var noteId = 0
    private var viewModel: UpdateNoteViewModel? = null
    private var title = ""
    private var content = ""

    @Inject
    lateinit var factory: ViewModelFactory

    companion object {
        @JvmStatic
        fun newInstance(id: Int, title: String, content: String) = UpdateNoteFragment().apply {
            arguments = Bundle().apply {
                putInt(NOTE_ID, id)
                putString(NOTE_TITLE, title)
                putString(NOTE_CONTENT, content)
            }
        }
    }

    override fun initView() {
        tvSave.setOnClickListener {
            viewModel?.updateNote(noteId, edtTitle.text.toString(), edtContent.text.toString())
        }

        viewModel?.updateStatus?.observe(this@UpdateNoteFragment, Observer {
            (activity as MainActivity).pushHomeScreen()
        })

        fillDataForUI()
    }

    override fun initData() {
        noteId = arguments?.getInt(NOTE_ID)!!
        title = arguments?.getString(NOTE_TITLE)!!
        content = arguments?.getString(NOTE_CONTENT)!!
    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.fragment_update_note

    override fun getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(UpdateNoteViewModel::class.java)
    }

    private fun fillDataForUI() {
        edtTitle.setText(title)
        edtContent.setText(content)
    }
}