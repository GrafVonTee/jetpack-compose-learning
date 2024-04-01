package com.example.composecourse.perioddata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecourse.perioddata.PeriodData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PeriodDataViewModel(periodData: PeriodData): ViewModel() {

    // Expose screen UI state
    private val _data = MutableStateFlow(periodData)
    val data = _data.asStateFlow()

    fun addTeaRecord() {
        viewModelScope.launch {
            _data.value.addTeaRecord()
        }
    }

    fun addWaterRecord() {
        viewModelScope.launch {
            _data.value.addWaterRecord()
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            _data.value.clearHistory()
        }
    }
}