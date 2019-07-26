package com.example.purplenotes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.purplenotes.data.repository.DataRepository
import com.example.purplenotes.ui.AddNoteViewModel
import com.example.purplenotes.util.SchedulersProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(val mDataRepository: DataRepository, val schedulersProvider: SchedulersProvider) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
            with(modelClass) {
                when {
                    isAssignableFrom(AddNoteViewModel::class.java) -> {
                        AddNoteViewModel(mDataRepository, schedulersProvider) as T
                    }

                    else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
                }
            }
}