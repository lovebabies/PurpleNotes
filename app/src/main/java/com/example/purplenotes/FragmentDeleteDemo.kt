package com.example.purplenotes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_delete_demo.*

class FragmentDeleteDemo: Fragment() {
    private val TAG = FragmentDeleteDemo::class.simpleName
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_delete_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var helper = context?.let { NoteSqliteHelper(it) }
        btnDelete.setOnClickListener {
            helper?.deleteNote(edtIdToDelete.text.toString().toInt())
            Log.d(TAG, "Total: ${helper?.getAllNote()}")
        }
    }
    companion object {
        fun newInstance() = FragmentDeleteDemo()
    }
}