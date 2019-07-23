//package com.example.purplenotes
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.Cursor
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.util.Log
//import android.widget.Toast
//import java.util.*
//
//const val DB_NAME = "db_note"
//const val DB_VERSION = 1
//val TAG = NoteSqliteHelper::class.simpleName
//class NoteSqliteHelper(val context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
//
//    private val TABLE_NOTE = "table_note"
//    private val COLUMN_ID = "id"
//    private val COLUMN_TITLE = "title"
//    private val COLUMN_CONTENT = "content"
//    private val COLUMN_CREATED_TIME = "created_time"
//    private val COLUMN_ALARM_TIME = "alarm_time"
//
//    private val COLUMNS = arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_CREATED_TIME, COLUMN_ALARM_TIME)
//
//    override fun onCreate(db: SQLiteDatabase?) {
//
//        //Sql statement
//        val CREATE_NOTE_TABLE_STATEMENT = "CREATE TABLE table_note (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "title TEXT, content TEXT, " +
//                "created_time TIMESTAMP," +
//                "alarm_time TIMESTAMP)"
//
//        //create notes table
//        db?.execSQL(CREATE_NOTE_TABLE_STATEMENT)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        //Drop older note if it existed
//        db?.execSQL("DROP TABLE IF EXISTS table_note")
//
//        //Create fresh note table
//        this.onCreate(db)
//    }
//
//    fun addNote(note: Note) {
//
//        //Log
//        Log.d(TAG, "add Note $note")
//
//        //Get reference
//        var db = writableDatabase
//
//        //create ContentValues to insert
//        var values = ContentValues()
//        values.put(COLUMN_TITLE, note.title)
//        values.put(COLUMN_CONTENT, note.content)
//        values.put(COLUMN_CREATED_TIME, note.createdTime)
//        values.put(COLUMN_ALARM_TIME, note.alarmTime)
//
//        //insert
//        db.insert(TABLE_NOTE, null, values)
//        Toast.makeText(context, "Add success", Toast.LENGTH_LONG).show()
//
//        //close
//        db.close()
//    }
//
//    fun getNoteById(id: Int): Note {
//
//        //get reference
//        var db = readableDatabase
//
//        //build query, return a cursor
//        var cursor = db.query(TABLE_NOTE,
//            COLUMNS,
//            "$COLUMN_ID = ?",
//            arrayOf(id.toString()),
//            null,
//            null,
//            null,
//            null)
//
//        //if we got result get the first one
//        if (cursor != null) {
//            cursor.moveToFirst()
//        }
//
//        //build note object
//        var note = Note()
//        note.title = cursor.getString(1)
//        note.content = cursor.getString(2)
//        note.createdTime = cursor.getString(3).toLong()
//        note.alarmTime = cursor.getString(4).toLong()
//
//        Toast.makeText(context, "Note is $note", Toast.LENGTH_LONG).show()
//
//        return note
//    }
//
//    fun getNoteByTitle(title: String): List<Note> {
//
//        //get reference
//        var db = readableDatabase
//
//        //build query
//        var cursor = db.query(
//            true,
//            TABLE_NOTE,
//            COLUMNS,
//            COLUMN_TITLE + " LIKE ?",
//            arrayOf("%$title"),
//            null, null, null, null
//        )
//
//     //   var cursor = db.rawQuery("SELECT TITLE,CONTENT,CREATED_TIME,ALARM_TIME FROM table_note WHERE TITLE like '%$title%'", null)
//
//        //
//        var noteList = LinkedList<Note>()
//        if (cursor.moveToFirst()) {
//            do {
//                var note: Note? = null
//                note = Note(
//                    cursor.getString(0),
//                    cursor.getString(1),
//                    cursor.getLong(2),
//                    cursor.getLong(3))
//
//                noteList.add(note)
//            } while (cursor.moveToNext())
//        }
//
//        //Log
//        Log.d(TAG, "get Note $noteList")
//
//        return noteList
//    }
//
//    //Delete single note
//    fun deleteNote(id: Int) {
//
//        //get reference
//        var db = writableDatabase
//
//        //delete
//        //DELETE table_note WHERE ID = note.id
//        db.delete(TABLE_NOTE,
//            COLUMN_ID + " = ?",
//            arrayOf(id.toString())
//            )
//
//        //close
//        db.close()
//
//    }
//
//    //update single note
//    fun updateNote(note: Note, id: Int): Int {
//
//        //get reference
//        var db = writableDatabase
//
//        //create ContentValues
//        var values = ContentValues()
//        values.put("title", note.title)
//        values.put("content", note.content)
//        values.put("createdTime", note.createdTime)
//        values.put("alarmTime", note.alarmTime)
//
//        //updating row
//        var i = db.update(TABLE_NOTE, values, COLUMN_ID + " = ?", arrayOf(id.toString()))
//
//        //close
//        db.close()
//
//        //Log
//        Log.d(TAG, "updateNote $note")
//
//        return i
//    }
//
//    fun getAllNote(): List<Note> {
//        var notes = ArrayList<Note>()
//
//        // build the query
//        var query = "SELECT * FROM $TABLE_NOTE"
//
//        //get reference
//        var db = writableDatabase
//        var cursor = db.rawQuery(query, null)
//
//        if (cursor.moveToFirst()) {
//            do {
//                var note = Note()
//                note.title = cursor.getString(1)
//                note.content = cursor.getString(2)
//
//                notes.add(note)
//            } while (cursor.moveToNext())
//        }
//
//        return notes
//    }
//
//}