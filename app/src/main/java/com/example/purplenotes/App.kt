package com.example.purplenotes

import android.app.Application
import com.example.purplenotes.di.application.ApplicationComponent
import com.example.purplenotes.di.application.DaggerApplicationComponent
import com.example.purplenotes.di.modules.ApplicationModules

class App: Application() {
    private var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        initAppicationComponent()
    }

    fun initAppicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModules(ApplicationModules(this)).build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}