package com.example.purplenotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "content") var content: String = "",
    @ColumnInfo(name = "created_time") var createdTime: Long = 0,
    @ColumnInfo(name = "alarm_time") var alarmTime: Long = 0) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}