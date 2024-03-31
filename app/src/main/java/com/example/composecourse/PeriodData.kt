package com.example.composecourse

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf

class PeriodData(records: List<Record>) {
    private var history = records.toMutableList()
    private var sum = 0
    private var teaCups = 0
    private var waterCups = 0

    init {
        recalculateCups()
    }

    private fun recalculateCups() {
        if (history.size > 1) {
            teaCups = history.last().teaCups - history.first().teaCups
            waterCups = history.last().waterCups - history.first().waterCups
            sum = teaCups + waterCups
        } else if (history.size == 1) {
            teaCups = history.last().teaCups
            waterCups = history.last().waterCups
            sum = teaCups + waterCups
        } else {
            teaCups = 0
            sum = 0
            waterCups = 0
        }
    }

    fun getSum(): Int { return sum }

    fun getTeaCups(): Int { return teaCups }

    fun getWaterCups(): Int { return waterCups }

    fun getHistory(byDate: String? = null): List<Record> {
        return history.subList(1, history.size - 1).toList()
    }

    fun getCopyEndingWithDate(dateStr: String? = null): PeriodData {
        if (dateStr == null) return this

        val date = DateTimeManipulation.getDateFromStr(dateStr)

        var newHistory: MutableList<Record> = mutableListOf()
        history.forEach { record ->
            val recordDate = DateTimeManipulation.getDateFromStr(record.date)
            if (recordDate <= date) {
                newHistory.add(record)
            }
        }
        return PeriodData(newHistory)
    }

    fun getCopyStartingWithDate(dateStr: String? = null): PeriodData {
        if (dateStr == null) return this

        val date = DateTimeManipulation.getDateFromStr(dateStr)

        var newHistory: MutableList<Record> = mutableListOf()
        history.forEach { record ->
            val recordDate = DateTimeManipulation.getDateFromStr(record.date)
            if (recordDate >= date) {
                newHistory.add(record)
            }
        }
        return PeriodData(newHistory)
    }

    private fun addRecord(record: Record) {
        history.add(record)
        recalculateCups()
    }

    fun clearHistory() {
        history.clear()
        recalculateCups()
    }

    fun removeLast() {
        history.removeLast()
        recalculateCups()
    }

    fun addTeaRecord() {
        addRecord(Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = teaCups + 1,
            waterCups = waterCups
        ))
    }

    fun addWaterRecord() {
        addRecord(Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = teaCups,
            waterCups = waterCups + 1
        ))
    }

    companion object {
        val ZERO: PeriodData = PeriodData(mutableListOf(Record.ZERO))
    }
}

data class Record(
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
    }
}
