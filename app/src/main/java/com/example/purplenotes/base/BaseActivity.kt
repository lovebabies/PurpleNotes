package com.example.purplenotes.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.purplenotes.App
import com.example.purplenotes.R
import com.example.purplenotes.di.application.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {

    var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewRes())
        initInjector()
        injectInjector()
        initData()
        initView()
    }

    abstract fun initData()
    abstract fun initView()
    abstract fun injectInjector()
    abstract fun getViewRes(): Int

    private fun initInjector() {
        mApplicationComponent = (application as App).getApplicationComponent()
    }

    fun getInjector(): ApplicationComponent? {
        return mApplicationComponent
    }

    fun pushScreen(fragment: Fragment, tag: String?) {
        val action : FragmentTransaction.() -> Unit = {
            this.replace(R.id.mainContainer, fragment, tag)
                .addToBackStack(null)
        }
        supportFragmentManager.transact(action)
    }

    fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
        beginTransaction().apply {
            action()
        }.commit()
    }
}