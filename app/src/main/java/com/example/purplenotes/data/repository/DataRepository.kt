package com.example.purplenotes.data.repository

import android.content.Context
import android.database.MergeCursor
import android.provider.MediaStore
import com.example.purplenotes.data.PhotoPathResponse
import com.example.purplenotes.data.database.Note
import com.example.purplenotes.data.database.NoteDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DataRepository @Inject constructor(val db: NoteDatabase, val context: Context){

    fun addNote(title: String, content: String): Completable =  db.noteDao().insertNote(Note(title, content))

    fun deleteNote(id: Int): Completable = db.noteDao().deleteNote(id)

    fun updateNote(id: Int, title: String, content: String): Completable = db.noteDao().updateNote(id, title, content)

    fun getNoteByTitle(title: String): Observable<Note> = db.noteDao().getNoteByTitle(title)

    fun getAllNote(): Observable<List<Note>> = db.noteDao().getAllNotes()

    fun getAllNoteTest(): List<Note> = db.noteDao().getAllNotesTest()

    fun getImageFromGallery(): Observable<PhotoPathResponse> {
        var path = ""
        var listPhotoPath = arrayListOf<String>()
        var projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DATE_MODIFIED)
        val uriExternal = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val uriInternal = MediaStore.Images.Media.INTERNAL_CONTENT_URI
        var cursorExternal = context.contentResolver.query(uriExternal, projection, null, null, MediaStore.MediaColumns.DATE_MODIFIED + " DESC")
        var cursorInternal = context.contentResolver.query(uriExternal, projection, null, null, MediaStore.MediaColumns.DATE_MODIFIED + " DESC")
        var cursor = MergeCursor(arrayOf(cursorExternal, cursorInternal))
        while(cursor.moveToNext()) {
            path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
            listPhotoPath.add(path)
        }

        return Observable.just(PhotoPathResponse(listPhotoPath))
    }
}