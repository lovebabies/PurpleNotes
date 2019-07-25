package com.example.purplenotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.data.database.NoteDao

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}