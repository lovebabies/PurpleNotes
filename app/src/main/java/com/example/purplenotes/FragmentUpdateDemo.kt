package com.example.purplenotes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentUpdateDemo: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update_demo, container, false)
    }

    companion object {
        fun newInstance() = FragmentUpdateDemo()
    }
}