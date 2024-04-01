package com.example.composecourse

class PeriodData(records: List<Record>) {
    private var history = records.toMutableList()
    private var sum = 0
    private var teaCups = 0
    private var waterCups = 0

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
        if (dateStr == null) return this

        val date = DateTimeManipulation.getDateFromStr(dateStr)

        val newHistory: MutableList<Record> = mutableListOf()
        for (record in history) {
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

        val newHistory: MutableList<Record> = mutableListOf()
        for (record in history) {
            val recordDate = DateTimeManipulation.getDateFromStr(record.date)
            if (recordDate >= date) {
                newHistory.add(record)
            }
        }
        return PeriodData(newHistory)
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
        val ZERO_FROM_NOW: PeriodData = PeriodData(mutableListOf(Record.ZERO_FROM_NOW))
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
        val ZERO_FROM_NOW: Record = Record(
            date = DateTimeManipulation.getTodayStr(),
            time = DateTimeManipulation.getTodayTimeStr(),
            teaCups = 0,
            waterCups = 0
        )
    }
}
