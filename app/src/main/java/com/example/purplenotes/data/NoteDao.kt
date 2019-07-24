package com.example.purplenotes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.purplenotes.data.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM note WHERE title LIKE :title")
    fun getNoteByTitle(title: String): Note

    @Query("UPDATE note SET title = :title, content = :content WHERE id = :id")
    fun updateNote(id: Int, title: String, content: String)

    @Insert
    fun insertNote(vararg note: Note)

    @Query("DELETE FROM note WHERE id = :id")
    fun deleteNote(id: Int)
}