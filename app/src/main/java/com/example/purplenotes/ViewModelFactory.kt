package com.example.purplenotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.purplenotes.data.repository.DataRepository
import com.example.purplenotes.ui.createnotes.CreateNoteViewModel
import com.example.purplenotes.ui.home.HomeViewModel
import com.example.purplenotes.util.SchedulersProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(val mDataRepository: DataRepository, val schedulersProvider: SchedulersProvider) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
            with(modelClass) {
                when {
                    isAssignableFrom(CreateNoteViewModel::class.java) -> {
                        CreateNoteViewModel(mDataRepository, schedulersProvider) as T
                    }

                    isAssignableFrom(HomeViewModel::class.java) -> {
                        HomeViewModel(mDataRepository, schedulersProvider) as T
                    }



                    else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
                }
            }
}