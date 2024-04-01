package com.example.composecourse.roomtable

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composecourse.DateTimeManipulation

data class RecordState(
    private var history: MutableList<Record> = mutableListOf(),
    private var sum: Int = 0,
    private var teaCups: Int = 0,
    private var waterCups: Int = 0,
) {
    init {
        recalculateCups()
    }

    private fun recalculateCups() {
        if (history.size > 0) {
            teaCups = history.last().teaCups
            waterCups = history.last().waterCups
            sum = teaCups + waterCups
        } else {
            teaCups = 0
            sum = 0
            waterCups = 0
        }
    }

    fun getSum(): Int {
        return getTeaCups() + getWaterCups()
    }

    fun getTeaCups(): Int {
        return if (history.size > 0) {
            history.last().teaCups - history.first().teaCups
        } else 0
    }

    fun getWaterCups(): Int {
        return if (history.size > 0) {
            history.last().waterCups - history.first().waterCups
        } else 0
    }

    fun getHistory(): List<Record> {
        return history.toList()
    }

    fun getCopyStartingWithDate(dateStr: String? = null): RecordState {
        if (dateStr == null) return this.copy()

        val date = DateTimeManipulation.getDateFromStr(dateStr)

        val newHistory: MutableList<Record> = mutableListOf()
        for (record in history) {
            val recordDate = DateTimeManipulation.getDateFromStr(record.date)
            if (recordDate >= date) {
                newHistory.add(record)
            }
        }
        return RecordState(newHistory, 0, 0, 0)
    }

    fun getToday(): RecordState {
        return getCopyStartingWithDate(DateTimeManipulation.getTodayStr())
    }

    fun getCurrentWeek(): RecordState {
        return getCopyStartingWithDate(DateTimeManipulation.getPreviousWeekDate())
    }

    fun getCurrentMonth(): RecordState {
        return getCopyStartingWithDate(DateTimeManipulation.getPreviousMonthDate())
    }

    fun getWholeData(): RecordState {
        return getCopyStartingWithDate()
    }

    fun getLast(): Record {
        return history.last()
    }

    fun getTeaRecord(): Record {
        return Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = teaCups + 1,
            waterCups = waterCups
        )
    }

    fun getWaterRecord(): Record {
        return Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = teaCups,
            waterCups = waterCups + 1
        )
    }
}

@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val date: String,
    val time: String,
    val teaCups: Int,
    val waterCups: Int,
) {
    companion object {
        val ZERO: Record = Record(
            date = DateTimeManipulation.getBeginningDate(),
            time = DateTimeManipulation.getBeginningTime(),
            teaCups = 0,
            waterCups = 0
        )
        val ZERO_FROM_NOW: Record = Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = 0,
            waterCups = 0
        )
    }
}
