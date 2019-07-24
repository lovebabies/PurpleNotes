package com.example.purplenotes.di.application

import com.example.purplenotes.MainActivity
import com.example.purplenotes.di.modules.ApplicationModules
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModules::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}