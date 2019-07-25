package com.example.purplenotes.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.purplenotes.base.BaseViewModel
import com.example.purplenotes.data.repository.DataRepository
import com.example.purplenotes.util.SchedulersProvider
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(val mDataRepository: DataRepository, val schedulersProvider: SchedulersProvider): BaseViewModel() {
    var mStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val TAG = AddNoteViewModel::class.simpleName

    @SuppressLint("CheckResult")
    fun addNote(title: String, content: String) {
        mDataRepository.addNote(title, content).subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(object : DisposableCompletableObserver(){
                override fun onError(e: Throwable) {

                }

                override fun onComplete() {
                    mStatus.value = true
                }
            })
    }
}