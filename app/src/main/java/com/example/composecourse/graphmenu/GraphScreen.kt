package com.example.composecourse.graphmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composecourse.roomtable.RecordEvent
import com.example.composecourse.roomtable.RecordState

@Composable
fun GraphScreen(state: RecordState, onEvent: (RecordEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        DropDownMenu(listOf("За день", "За неделю", "За месяц", "За всё время"))
    }
}