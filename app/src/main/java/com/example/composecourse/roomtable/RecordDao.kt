package com.example.composecourse.roomtable

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Upsert
    fun upsertRecord(record: Record)

    @Delete
    fun deleteLast(record: Record)

    @Query("DELETE FROM record")
    fun clearHistory()

    @Query("SELECT * FROM record")
    fun getRecordHistory(): Flow<List<Record>>
}