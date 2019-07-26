package com.example.purplenotes.base

import androidx.lifecycle.ViewModel
import com.example.purplenotes.data.repository.DataRepository
import com.example.purplenotes.util.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {
    val compositeDisposable = CompositeDisposable()
}