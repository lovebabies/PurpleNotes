package com.example.purplenotes.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.purplenotes.base.BaseViewModel
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.data.repository.DataRepository
import com.example.purplenotes.util.SchedulersProvider
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

/**
 * Author: Jayden Nguyen
 * Created date: 7/31/2019
 */
class HomeViewModel @Inject constructor(val mDataRepository: DataRepository, val schedulersProvider: SchedulersProvider): BaseViewModel() {
    private val TAG = HomeViewModel::class.simpleName
    var noteList = MutableLiveData<List<Note>>()
    var deletePosition = MutableLiveData<Int>()

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

    fun deleteNote(id: Int) {
        mDataRepository.deleteNote(id).subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(object : DisposableCompletableObserver(){
                override fun onComplete() {
                    deletePosition.value = id
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: ${e.message}")
                }
            })
    }
}