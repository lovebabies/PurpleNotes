package com.example.purplenotes.ui

import android.os.Bundle
import android.view.View
import com.example.purplenotes.*
import com.example.purplenotes.base.BaseActivity
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.ui.createnotes.CreateNoteFragment
import com.example.purplenotes.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun initData() {
    }

    override fun initView() {
        pushHomeScreen()
    }

    override fun injectInjector() {
        getInjector()?.inject(this)
    }

    override fun getViewRes(): Int = R.layout.activity_main

    fun pushHomeScreen() {
        pushScreen(HomeFragment.newInstance(), HomeFragment::class.simpleName)
    }

    fun pushCreateNoteScreen() {
        pushScreen(CreateNoteFragment.newInstance(), CreateNoteFragment::class.simpleName)
    }
}
