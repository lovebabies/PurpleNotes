package com.example.purplenotes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener(this)
        btnGetNoteByTitle.setOnClickListener(this)
        btnDeleteNote.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
    }

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

//    fun pushScreen(fragment: Fragment, tag: String?) {
//        supportFragmentManager.transact {
//            replace(R.id.mainContainer, fragment, tag)
//                .addToBackStack(null)
//        }
//    }
//
//    inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
//        beginTransaction().apply {
//            action
//        }.commit()
//    }

    fun pushScreen(fragment: Fragment, tag: String?) {
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment, tag).addToBackStack(null).commit()
    }
}
