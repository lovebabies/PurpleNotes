package com.example.purplenotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
            with(modelClass) {
                when {
                    isAssignableFrom()
                }
            }
}