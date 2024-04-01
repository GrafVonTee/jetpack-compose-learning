package com.example.composecourse.roomtable

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Record::class],
    version = 1
)
abstract class RecordDatabase: RoomDatabase() {

    abstract val dao: RecordDao
}