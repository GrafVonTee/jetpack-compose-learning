package com.example.composecourse.roomtable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordStateViewModel(
    private val dao: RecordDao,
): ViewModel() {
    // Expose screen UI state
    private val _state = MutableStateFlow(RecordState())
    private val _history = dao.getRecordHistory()

    val state = combine(_state, _history) { state, history ->
        state.copy(history =
            if (history.isNotEmpty()) {
                history.toMutableList()
            } else mutableListOf(Record.ZERO_FROM_NOW)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecordState())

    fun onEvent(event: RecordEvent) {
        when (event) {
            RecordEvent.ClearHistory -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        dao.clearHistory()
                        dao.upsertRecord(Record.ZERO_FROM_NOW)
                    }
                }
            }
            is RecordEvent.DeleteLast -> {
                if (state.value.getHistory().size > 1) {
                    val lastRecord = state.value.getLast()
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            dao.deleteLast(lastRecord)
                        }
                    }
                }
            }
            is RecordEvent.SaveTeaRecord -> {
                val teaRecord = state.value.getTeaRecord()
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        dao.upsertRecord(teaRecord)
                    }
                }
            }
            is RecordEvent.SaveWaterRecord -> {
                val waterRecord = state.value.getWaterRecord()
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        dao.upsertRecord(waterRecord)
                    }
                }
            }
        }
    }
}