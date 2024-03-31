package com.example.composecourse

import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import java.time.format.DateTimeFormatter

object DateTimeManipulation {
    private val dateFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy")
    private val timeFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("HH:mm")

    fun getBeginningDate(): String {
        return LocalDate.MIN.format(dateFormatter)
    }

    fun getBeginningTime(): String {
        return LocalTime.MIN.format(timeFormatter)
    }

    fun getTodayStr(): String {
        return LocalDate.now().format(dateFormatter)
    }

    fun getDateFromStr(dateStr: String): LocalDate {
        return LocalDate.parse(dateStr, dateFormatter)
    }

    fun getTodayTimeStr(): String {
        return LocalTime.now().format(timeFormatter)
    }

    fun getTimeFromStr(timeStr: String): LocalTime {
        return LocalTime.parse(timeStr, timeFormatter)
    }

    fun getPreviousWeekDate(): String {
        val weekPeriod = Period.of(0, 0, 7)
        return LocalDate.now().minus(weekPeriod).format(dateFormatter)
    }

    fun getPreviousMonthDate(): String {
        val weekPeriod = Period.of(0, 1, 0)
        return LocalDate.now().minus(weekPeriod).format(dateFormatter)
    }
}
