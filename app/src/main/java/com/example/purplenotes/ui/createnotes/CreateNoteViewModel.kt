package com.example.purplenotes.ui.createnotes

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.purplenotes.base.BaseViewModel
import com.example.purplenotes.data.repository.DataRepository
import com.example.purplenotes.util.SchedulersProvider
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

class CreateNoteViewModel @Inject constructor(val mDataRepository: DataRepository, val schedulersProvider: SchedulersProvider): BaseViewModel() {
    var mStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val TAG = CreateNoteViewModel::class.simpleName
    val listPhotoPath = MutableLiveData<List<String>>()

    @SuppressLint("CheckResult")
    fun addNote(title: String, content: String) {
        mDataRepository.addNote(title, content).subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe(object : DisposableCompletableObserver(){
                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: ${e.message}")
                }

                override fun onComplete() {
                    mStatus.value = true
                }
            })
    }

    @SuppressLint("CheckResult")
    fun getImageFromGallery() {
        mDataRepository.getImageFromGallery().subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .subscribe({
                listPhotoPath.value = it.photoPaths
            }, {
                Log.e(TAG, "onError: ${it.message}")
            })
    }
}