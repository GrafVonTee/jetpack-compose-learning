package com.example.composecourse.homemenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composecourse.DateTimeManipulation
import com.example.composecourse.PeriodData

@Composable
fun HomeScreen(periodData: PeriodData) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        val todayData = periodData.getCopyStartingWithDate(DateTimeManipulation.getTodayStr())
        StatisticsBarChartByPeriod(
            todayData,
            text = "Статистика за день",
        )

        val weekData = periodData.getCopyStartingWithDate(DateTimeManipulation.getPreviousWeekDate())
        StatisticsBarChartByPeriod(
            weekData,
            text = "Статистика за неделю",
        )

        val monthData = periodData.getCopyStartingWithDate(DateTimeManipulation.getPreviousMonthDate())
        StatisticsBarChartByPeriod(
            monthData,
            text = "Статистика за месяц",
        )

        StatisticsBarChartByPeriod(
            periodData,
            text = "Статистика за всё время",
        )

        Row (
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CircleCupButton(
                title = "Вода",
                buttonText = todayData.getWaterCups().toString(),
                color = Color.Blue,
                periodData::addWaterRecord
            )
            CircleCupButton(
                title = "Чай",
                buttonText = todayData.getTeaCups().toString(),
                color = Color.Red,
                periodData::addTeaRecord
            )
        }
    }
}