package com.example.purplenotes.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object Database {
    var mDatabase: RoomDatabase? = null

    @JvmStatic
    fun buildInstance(context: Context) : RoomDatabase? {
        return Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "database_note").allowMainThreadQueries().build()
    }
}