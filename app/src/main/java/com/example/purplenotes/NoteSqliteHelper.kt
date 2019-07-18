package com.example.purplenotes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

const val DB_NAME = "db_note"
const val DB_VERSION = 1
val TAG = NoteSqliteHelper::class.simpleName
class NoteSqliteHelper(val context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

    private val TABLE_NOTE = "table_note"
    private val COLUMN_ID = "id"
    private val COLUMN_TITLE = "title"
    private val COLUMN_CONTENT = "content"
    private val COLUMN_CREATED_TIME = "created_time"
    private val COLUMN_ALARM_TIME = "alarm_time"

    private val COLUMNS = arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_CREATED_TIME, COLUMN_ALARM_TIME)

    override fun onCreate(db: SQLiteDatabase?) {

        //Sql statement
        val CREATE_NOTE_TABLE_STATEMENT = "CREATE TABLE table_note (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, content TEXT, " +
                "created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "alarm_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"

        //create notes table
        db?.execSQL(CREATE_NOTE_TABLE_STATEMENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Drop older note if it existed
        db?.execSQL("DROP TABLE IF EXISTS table_note")

        //Create fresh note table
        this.onCreate(db)
    }

    fun addNote(note: Note) {

        //Log
        Log.d(TAG, "add Note $note")

        //Get reference
        var db = writableDatabase

        //create ContentValues to insert
        var values = ContentValues()
        values.put(COLUMN_TITLE, note.title)
        values.put(COLUMN_CONTENT, note.content)
        values.put(COLUMN_CREATED_TIME, note.createdTime)
        values.put(COLUMN_ALARM_TIME, note.alarmTime)

        //insert
        db.insert(TABLE_NOTE, null, values)

        //close
        db.close()
    }

    fun getNoteById(id: Int): Note {

        //get reference
        var db = readableDatabase

        //build query, return a cursor
        var cursor = db.query(TABLE_NOTE,
            COLUMNS,
            "$COLUMN_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null)

        //if we got result get the first one
        if (cursor != null) {
            cursor.moveToFirst()
        }

        //build note object
        var note = Note()
        note.id = cursor.getString(0).toInt()
        note.title = cursor.getString(1)
        note.content = cursor.getString(2)
        note.createdTime = cursor.getString(3).toLong()
        note.alarmTime = cursor.getString(4).toLong()

        return note
    }

    fun getNoteByTitle(title: String): List<Note> {

        //get reference
        var db = readableDatabase

        //build query
        var cursor = db.query(
            TABLE_NOTE,
            COLUMNS,
            COLUMN_TITLE + "like %?%",
            arrayOf(title),
            null, null, null, null
        )

        //
        var noteList = LinkedList<Note>()
        var note: Note? = null
        if (cursor.moveToFirst()) {
            do {
                note = Note(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4))

                noteList.add(note)
            } while (cursor.moveToNext())
        }

        //Log
        Log.d(TAG, "get Note $noteList")

        return noteList
    }

    //Delete single note
    fun deleteNote(note: Note) {

        //get reference
        var db = writableDatabase

        //delete
        //DELETE table_note WHERE ID = note.id
        db.delete(TABLE_NOTE,
            COLUMN_ID + " = ?",
            arrayOf(note.id.toString())
            )

        //close
        db.close()

        //Log
        Log.d(TAG, "delete note $note")

    }

    //update single note
    fun updateNote(note: Note) {

    }

}