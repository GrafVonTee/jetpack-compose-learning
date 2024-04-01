package com.example.composecourse.perioddata

import android.content.Context.MODE_PRIVATE
import com.example.composecourse.DateTimeManipulation
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.internal.readJson
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintWriter

class PeriodData(records: List<Record>) {
    private var history = records.toMutableList()
    private var sum = 0
    private var teaCups = 0
    private var waterCups = 0
    private val defaultFileName = "myFile.txt"

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

    fun getCopyEndingWithDate(dateStr: String? = null): PeriodData {
        if (dateStr == null) return PeriodData(getHistory())

        val date = DateTimeManipulation.getDateFromStr(dateStr)

        val newHistory: MutableList<Record> = mutableListOf()
        for (record in history) {
            val recordDate = DateTimeManipulation.getDateFromStr(record.date)
            if (recordDate <= date) {
                newHistory.add(record)
            }
        }
        return PeriodData(newHistory.toList())
    }

    fun getCopyStartingWithDate(dateStr: String? = null): PeriodData {
        if (dateStr == null) return PeriodData(getHistory())

        val date = DateTimeManipulation.getDateFromStr(dateStr)

        val newHistory: MutableList<Record> = mutableListOf()
        for (record in history) {
            val recordDate = DateTimeManipulation.getDateFromStr(record.date)
            if (recordDate >= date) {
                newHistory.add(record)
            }
        }
        return PeriodData(newHistory.toList())
    }

    fun getToday(): PeriodData {
        return getCopyStartingWithDate(DateTimeManipulation.getTodayStr())
    }

    fun getCurrentWeek(): PeriodData {
        return getCopyStartingWithDate(DateTimeManipulation.getPreviousWeekDate())
    }

    fun getCurrentMonth(): PeriodData {
        return getCopyStartingWithDate(DateTimeManipulation.getPreviousMonthDate())
    }

    fun getWholeData(): PeriodData {
        return getCopyStartingWithDate()
    }

    private fun addRecord(record: Record) {
        history.add(record)
        recalculateCups()
    }

    fun clearHistory() {
        history.clear()
        addRecord(Record.ZERO_FROM_NOW)
        recalculateCups()
    }

    fun removeLast() {
        history.removeLast()
        recalculateCups()
    }

    fun addTeaRecord() {
        addRecord(
            Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = teaCups + 1,
            waterCups = waterCups
        )
        )
    }

    fun addWaterRecord() {
        addRecord(
            Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = teaCups,
            waterCups = waterCups + 1
        )
        )
    }

    fun saveToInternalStorage(filename: String = defaultFileName): Boolean {
        return try {
            PrintWriter(filename).use {
                it.write(Json.encodeToString(history))
            }
            true
        } catch(e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun loadFromInternalStorage(filename: String = defaultFileName): Boolean {
        return try {
            history = Json.decodeFromString<List<Record>>(
                File(filename)
                    .inputStream()
                    .readBytes()
                    .toString(Charsets.UTF_8)
            ).toMutableList()
            true
        } catch(e: IOException) {
            e.printStackTrace()
            false
        }
    }

    companion object {
        val ZERO: PeriodData = PeriodData(mutableListOf(Record.ZERO))
        val ZERO_FROM_NOW: PeriodData = PeriodData(mutableListOf(Record.ZERO_FROM_NOW))
    }
}

@Serializable
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
        val ZERO_FROM_NOW: Record = Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = 0,
            waterCups = 0
        )
    }
}
