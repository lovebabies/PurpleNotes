package com.example.purplenotes.ui.home

import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
import java.lang.Exception
import javax.inject.Inject

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class HomeFragment: BaseFragment(), NoteAdapter.OnClickEditListener {

    private val mNoteList: ArrayList<Note> = ArrayList<Note>()
    lateinit var mAdapter: NoteAdapter
    private var viewModel: HomeViewModel? = null
    private var mCurrentEditTitle = ""
    private var mCurrentEditContent = ""

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
        registerForContextMenu(rcvNotes)

        viewModel?.noteList?.observe(this@HomeFragment, Observer {
            mAdapter.addNotes(it as ArrayList<Note>)
            mAdapter.notifyDataSetChanged()
            checkForDisplayNoteList()
        })

        viewModel?.deletePosition?.observe(this@HomeFragment, Observer {
            mAdapter.deleteNote(it)
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

    override fun onClickEdit(title: String, content: String) {
        mCurrentEditTitle = title
        mCurrentEditContent = content
    }

    private fun setupRecyclerView() {
        rcvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = NoteAdapter()
            mAdapter.setNoteList(mNoteList)
            mAdapter.setOnClickEditListener(this@HomeFragment)
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

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        var position = -1
        try {
            position = mAdapter.getPosition()
        } catch (e: Exception) {

        }

        when(item?.itemId) {
            0 -> {
                Toast.makeText(context, "EDIT pos: $position", Toast.LENGTH_LONG).show()
                (activity as MainActivity).pushUpdateNoteScreen(position, mCurrentEditTitle, mCurrentEditContent)
            }

            1 -> {
                Toast.makeText(context, "DELETE pos: $position", Toast.LENGTH_LONG).show()
                viewModel?.deleteNote(position)
            }
        }

        return super.onContextItemSelected(item)
    }

}