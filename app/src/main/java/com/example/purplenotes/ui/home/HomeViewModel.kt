package com.example.purplenotes.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.purplenotes.base.BaseViewModel
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.data.repository.DataRepository
import com.example.purplenotes.util.SchedulersProvider
import javax.inject.Inject

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class HomeViewModel @Inject constructor(val mDataRepository: DataRepository, val schedulersProvider: SchedulersProvider): BaseViewModel() {
    private val TAG = HomeViewModel::class.simpleName
    var noteList = MutableLiveData<List<Note>>()

    @SuppressLint("CheckResult")
    fun getAllNotes() {
        mDataRepository.getAllNote().subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe({
                noteList.value = it
            }, {
                Log.e(TAG, "onError ${it.message}")
            })
    }
}