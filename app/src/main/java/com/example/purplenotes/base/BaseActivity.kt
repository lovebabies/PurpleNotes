package com.example.purplenotes.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.purplenotes.R

abstract class BaseActivity : AppCompatActivity() {
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