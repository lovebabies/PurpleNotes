package com.example.purplenotes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.purplenotes.data.database.Note
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): Observable<List<Note>>

    @Query("SELECT * FROM note WHERE title LIKE :title")
    fun getNoteByTitle(title: String): Observable<Note>

    @Query("UPDATE note SET title = :title, content = :content WHERE id = :id")
    fun updateNote(id: Int, title: String, content: String): Completable

    @Insert
    fun insertNote(vararg note: Note): Completable
    @Query("DELETE FROM note WHERE id = :id")
    fun deleteNote(id: Int): Completable
}