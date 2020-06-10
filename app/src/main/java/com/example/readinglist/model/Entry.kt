package com.example.readinglist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//enum class Type {
//    TWITTER, NOTE, ARTICLE
//}

@Entity(tableName = "stored")
data class Entry (@PrimaryKey @ColumnInfo(name="value") val value: String, val type: String)