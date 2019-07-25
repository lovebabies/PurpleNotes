package com.example.purplenotes.di.modules

import android.content.Context
import androidx.room.Room
import com.example.purplenotes.data.database.NoteDatabase
import com.example.purplenotes.util.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModules(val mContext: Context) {
    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): NoteDatabase {
        return Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "database_note").build()
    }

    @Provides
    @Singleton
    fun provideContex(): Context {
        return mContext
    }

    @Provides
    @Singleton
    fun provideSchedulersProvider(): SchedulersProvider {
        return SchedulersProvider()
    }
}