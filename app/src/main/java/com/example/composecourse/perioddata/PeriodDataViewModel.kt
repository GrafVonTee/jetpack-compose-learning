package com.example.composecourse.perioddata

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecourse.perioddata.PeriodData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PeriodDataViewModel(periodData: PeriodData): ViewModel() {

    // Expose screen UI state
    private val _data = MutableStateFlow(periodData)
    val data = _data.asStateFlow()

    fun addTeaRecord() {
        _data.value.addTeaRecord()
        _data.update {
            it.getCopyEndingWithDate()
        }
    }

    fun addWaterRecord() {
        _data.value.addWaterRecord()
        _data.update {
            it.getCopyEndingWithDate()
        }
    }

    fun clearHistory() {
        _data.value.clearHistory()
        _data.update {
            it.getCopyEndingWithDate()
        }
    }
}