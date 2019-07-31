package com.example.purplenotes.ui.createnotes

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.purplenotes.R
import com.example.purplenotes.ViewModelFactory
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.base.BaseFragment
import com.example.purplenotes.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_create_note.*
import javax.inject.Inject

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class CreateNoteFragment: BaseFragment(), View.OnClickListener {

    private var viewModel: CreateNoteViewModel? = null

    @Inject
    lateinit var factory: ViewModelFactory

    companion object {
        @JvmStatic
        fun newInstance() = CreateNoteFragment()
    }

    override fun initView() {
        tvSave.setOnClickListener(this)

        viewModel?.mStatus?.observe(this@CreateNoteFragment, Observer {
            if (it) {
                Toast.makeText(context,"Add Note Success", Toast.LENGTH_LONG).show()
                (activity as MainActivity).pushHomeScreen()
            } else {
                Toast.makeText(context, "Add Note Failed", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun initData() {

    }

    override fun injectInjector() {
        (activity as BaseActivity).getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.fragment_create_note

    override fun getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(CreateNoteViewModel::class.java)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tvSave -> {
                viewModel?.addNote(edtTitle.text.toString(), edtContent.text.toString())
            }
        }
    }

}