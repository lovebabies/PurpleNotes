package com.example.purplenotes.data.repository

import com.example.purplenotes.data.database.Note
import com.example.purplenotes.data.database.NoteDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DataRepository @Inject constructor(val db: NoteDatabase){

    fun addNote(title: String, content: String): Completable =  db.noteDao().insertNote(Note(title, content))

    fun deleteNote(id: Int): Completable = db.noteDao().deleteNote(id)

    fun updateNote(id: Int, title: String, content: String): Completable = db.noteDao().updateNote(id, title, content)

    fun getNoteByTitle(title: String): Observable<Note> = db.noteDao().getNoteByTitle(title)

    fun getAllNote(): Observable<List<Note>> = db.noteDao().getAllNotes()

    fun getAllNoteTest(): List<Note> = db.noteDao().getAllNotesTest()
}