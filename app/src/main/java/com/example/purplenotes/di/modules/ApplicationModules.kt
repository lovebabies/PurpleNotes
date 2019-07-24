package com.example.purplenotes.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.purplenotes.data.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModules(val context: Context) {
    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): RoomDatabase {
        return Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "database_note").allowMainThreadQueries().build()
    }
}