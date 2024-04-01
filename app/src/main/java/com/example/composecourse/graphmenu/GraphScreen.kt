package com.example.composecourse.graphmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composecourse.perioddata.PeriodDataViewModel

@Composable
fun GraphScreen(viewModel: PeriodDataViewModel) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val data by viewModel.data.collectAsState()

        DropDownMenu(listOf("За день", "За неделю", "За месяц", "За всё время"))
    }
}