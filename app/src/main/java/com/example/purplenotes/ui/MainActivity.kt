package com.example.purplenotes.ui

import android.os.Bundle
import android.view.View
import com.example.purplenotes.*
import com.example.purplenotes.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun initData() {
    }

    override fun initView() {
        btnAdd.setOnClickListener(this)
        btnGetNoteByTitle.setOnClickListener(this)
        btnDeleteNote.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
    }

    override fun injectInjector() {
        getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.activity_main

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnAdd -> {
                pushScreen(FragmentAddNoteDemo.newInstance(), FragmentAddNoteDemo::class.simpleName)
            }

            R.id.btnGetNoteByTitle -> {
                pushScreen(FragmentGetNoteByIdDemo.newInstance(), FragmentGetNoteByIdDemo::class.simpleName)
            }

            R.id.btnDeleteNote -> {
                pushScreen(FragmentDeleteDemo.newInstance(), FragmentDeleteDemo::class.simpleName)
            }

            R.id.btnUpdate -> {
                pushScreen(FragmentUpdateDemo.newInstance(), FragmentUpdateDemo::class.simpleName)
            }
        }
    }
}
