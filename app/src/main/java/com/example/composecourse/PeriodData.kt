package com.example.composecourse

class PeriodData(
    private var history: MutableList<Record>,
    private var sum: Int,
    private var teaCups: Int,
    private var waterCups: Int,
) {
    fun constructor(records: List<Record>) {
        history = records.toMutableList()
        sum = 0
        teaCups = 0
        waterCups = 0

        records.forEach {record ->
            teaCups += record.teaCups
            waterCups += record.waterCups
        }
        sum = teaCups + waterCups
    }

    fun getSum(): Int { return sum }

    fun getTeaCups(): Int { return teaCups }

    fun getWaterCups(): Int { return waterCups }

    companion object {
        val ZERO: PeriodData = PeriodData(mutableListOf(), 0, 0, 0)
    }

    fun addRecord(record: Record) {
        history.add(record)
    }

    fun clearHistory() {
        history.clear()
    }

    fun removeLast() {
        history.removeLast()
    }
}

data class Record(
    val date: String,
    val time: String,
    val teaCups: Int,
    val waterCups: Int,
)

fun makeTeaRecord() {

}

fun makeWaterRecord() {

}
