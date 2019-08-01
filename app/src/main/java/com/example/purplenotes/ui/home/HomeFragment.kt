package com.example.purplenotes.ui.home

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.purplenotes.R
import com.example.purplenotes.ViewModelFactory
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class HomeFragment: BaseFragment() {
    private val mNoteList: ArrayList<Note> = ArrayList<Note>()
    lateinit var mAdapter: NoteAdapter
    private var viewModel: HomeViewModel? = null

    @Inject
    lateinit var factory: ViewModelFactory

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initView() {
        viewModel?.getAllNotes()
        setupRecyclerView()
        checkForDisplayNoteList()
        fab.setOnClickListener(onClickListener)

        viewModel?.noteList?.observe(this@HomeFragment, Observer {
            mAdapter.addNotes(it as ArrayList<Note>)
            mAdapter.notifyDataSetChanged()
            checkForDisplayNoteList()
        })
    }

    override fun initData() {
    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.fragment_home

    override fun getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    private fun setupRecyclerView() {
        rcvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = NoteAdapter()
            mAdapter.setNoteList(mNoteList)
            adapter = mAdapter
        }
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