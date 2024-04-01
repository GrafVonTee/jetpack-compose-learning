package com.example.composecourse

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update

class PeriodDataViewModel(periodData: PeriodData): ViewModel() {

    // Expose screen UI state
    private val _data = MutableStateFlow(periodData)
    val data = _data.asStateFlow()

    fun addTeaRecord() {
        _data.value.addTeaRecord()
    }

    fun addWaterRecord() {
        _data.value.addWaterRecord()
    }

    fun clearHistory() {
        _data.value.clearHistory()
    }
}