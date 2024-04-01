package com.example.composecourse.homemenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecourse.PeriodDataViewModel

@Composable
fun HomeScreen(
    viewModel: PeriodDataViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        val data by viewModel.data.collectAsState()

        val todayData = data.getToday()
        StatisticsBarChartByPeriod(
            todayData,
            text = "Статистика за день",
        )

        val weekData = data.getCurrentWeek()
        StatisticsBarChartByPeriod(
            weekData,
            text = "Статистика за неделю",
        )

        val monthData = data.getCurrentMonth()
        StatisticsBarChartByPeriod(
            monthData,
            text = "Статистика за месяц",
        )

        val overallData = data.getWholeData()
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
                viewModel::addWaterRecord
            )
            CircleCupButton(
                title = "Чай",
                buttonText = todayData.getTeaCups().toString(),
                color = Color.Red,
                viewModel::addTeaRecord
            )
        }
    }
}
