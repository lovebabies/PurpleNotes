package com.example.purplenotes.ui.updatenotes

import android.annotation.SuppressLint
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.purplenotes.base.BaseViewModel
import com.example.purplenotes.data.repository.DataRepository
import com.example.purplenotes.util.SchedulersProvider
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

/**
 * Author: Jayden Nguyen
 * Created date: 8/1/2019
 */
class UpdateNoteViewModel @Inject constructor(val mDataRepository: DataRepository, val schedulersProvider: SchedulersProvider): BaseViewModel() {
    private val TAG = UpdateNoteViewModel::class.simpleName
    var updateStatus = MutableLiveData<Boolean>()

    fun updateNote(id: Int, title: String, content: String) {
        mDataRepository.updateNote(id, title, content)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(object: DisposableCompletableObserver(){
                override fun onComplete() {
                    updateStatus.value = true
                    getAllNotes()
                }

                override fun onError(e: Throwable) {
                    updateStatus.value = false
                    Log.e(TAG, "onError: ${e.message}")
                }
            })
    }

    @SuppressLint("CheckResult")
    fun getAllNotes() {
        mDataRepository.getAllNote().subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe({
                Log.d(TAG,"ALl the note is $it")
            }, {
                Log.e(TAG, "onError ${it.message}")
            })
    }
}