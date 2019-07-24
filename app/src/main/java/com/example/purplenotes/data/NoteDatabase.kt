package com.example.purplenotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.purplenotes.data.Note
import com.example.purplenotes.data.NoteDao

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}