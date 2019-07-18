package com.example.purplenotes

data class Note(var id: Int = 0,
                var title: String = "",
                var content: String = "",
                var createdTime: Long = 0,
                var alarmTime: Long = 0)