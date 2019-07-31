package com.example.purplenotes.di.application

import com.example.purplenotes.di.modules.ApplicationModules
import com.example.purplenotes.ui.*
import com.example.purplenotes.ui.createnotes.CreateNoteFragment
import com.example.purplenotes.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModules::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(createNoteFragment: CreateNoteFragment)
}