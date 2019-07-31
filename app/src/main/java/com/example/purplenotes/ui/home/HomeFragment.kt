package com.example.purplenotes.ui.home

import android.view.View
import com.example.purplenotes.R
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class HomeFragment: BaseFragment() {
    private val mNoteList: List<Note>? = null

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initView() {
        checkForDisplayNoteList()

        fab.setOnClickListener(onClickListener)
    }

    override fun initData() {

    }

    override fun injectInjector() {

    }

    override fun getViewRes(): Int = R.layout.fragment_home

    override fun getViewModel() {

    }

    private fun setupRecyclerView() {

    }

    private fun checkForDisplayNoteList() {
        if (mNoteList.isNullOrEmpty()) {
            rcvNotes.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            rcvNotes.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            R.id.fab -> {
                (activity as MainActivity).pushCreateNoteScreen()
            }
        }
    }

}