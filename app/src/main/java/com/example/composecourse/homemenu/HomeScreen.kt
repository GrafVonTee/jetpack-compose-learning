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
import com.example.composecourse.PeriodData
import com.example.composecourse.makeTeaRecord
import com.example.composecourse.makeWaterRecord

@Composable
fun HomeScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        var currentDayHistory by remember {
            mutableStateOf(PeriodData.ZERO)
        }

        StatisticsBarChartByPeriod(
            currentDayHistory,
            text = "Статистика за день",
        )

        StatisticsBarChartByPeriod(
            PeriodData.ZERO,
            text = "Статистика за неделю",
        )

        StatisticsBarChartByPeriod(
            PeriodData.ZERO,
            text = "Статистика за месяц",
        )

        StatisticsBarChartByPeriod(
            PeriodData.ZERO,
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
                buttonText = currentDayHistory.getWaterCups().toString(),
                color = Color.Blue,
                ::makeWaterRecord
            )
            CircleCupButton(
                title = "Чай",
                buttonText = currentDayHistory.getTeaCups().toString(),
                color = Color.Red,
                ::makeTeaRecord
            )
        }
    }
}