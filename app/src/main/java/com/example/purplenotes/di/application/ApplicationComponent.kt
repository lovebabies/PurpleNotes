package com.example.purplenotes.di.application

import com.example.purplenotes.di.modules.ApplicationModules
import com.example.purplenotes.ui.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModules::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragmentAddNoteDemo: FragmentAddNoteDemo)
    fun inject(fragmentDeleteDemo: FragmentDeleteDemo)
    fun inject(fragmentGetNoteByIdDemo: FragmentGetNoteByIdDemo)
    fun inject(fragmentUpdateDemo: FragmentUpdateDemo)
}