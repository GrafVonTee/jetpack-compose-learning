package com.example.composecourse.roomtable

sealed interface RecordEvent {
    object SaveTeaRecord: RecordEvent
    object SaveWaterRecord: RecordEvent
    object DeleteLast: RecordEvent
    object ClearHistory: RecordEvent
}