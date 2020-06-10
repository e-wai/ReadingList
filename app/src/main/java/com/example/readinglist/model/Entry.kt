package com.example.readinglist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stored")
data class Entry (@PrimaryKey val value: String,
                  @ColumnInfo(name="type") val type: String)