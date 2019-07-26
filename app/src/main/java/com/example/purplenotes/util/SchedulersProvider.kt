package com.example.purplenotes.util

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersProvider {
    fun io() = Schedulers.io()

    fun ui() = AndroidSchedulers.mainThread()

    fun compute() = Schedulers.computation()
}