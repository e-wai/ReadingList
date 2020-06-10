package com.example.readinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.readinglist.model.Entry
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Entry::class], version = 1, exportSchema = false)
abstract class EntryRoomDatabase : RoomDatabase() {

    abstract fun entryDao(): EntryDao

    companion object {
        @Volatile
        private var INSTANCE: EntryRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): EntryRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryRoomDatabase::class.java,
                    "entries"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}