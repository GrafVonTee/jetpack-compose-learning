package com.example.composecourse.homemenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composecourse.roomtable.RecordEvent
import com.example.composecourse.roomtable.RecordState

@Composable
fun HomeScreen(state: RecordState, onEvent: (RecordEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        val todayData = state.getToday()
        StatisticsBarChartByPeriod(
            todayData,
            text = "Статистика за день",
        )

        val weekData = state.getCurrentWeek()
        StatisticsBarChartByPeriod(
            weekData,
            text = "Статистика за неделю",
        )

        val monthData = state.getCurrentMonth()
        StatisticsBarChartByPeriod(
            monthData,
            text = "Статистика за месяц",
        )

        val overallData = state.getWholeData()
        StatisticsBarChartByPeriod(
            overallData,
            text = "Статистика за всё время",
        )

        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CircleCupButton(
                title = "Вода",
                buttonText = todayData.getWaterCups().toString(),
                color = Color.Blue,
                { onEvent(RecordEvent.SaveWaterRecord) }
            )
            CircleCupButton(
                title = "Чай",
                buttonText = todayData.getTeaCups().toString(),
                color = Color.Red,
                { onEvent(RecordEvent.SaveTeaRecord) }
            )
        }
    }
}
