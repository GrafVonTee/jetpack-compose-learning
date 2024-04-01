package com.example.composecourse.homemenu

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourse.perioddata.PeriodData

@Composable
fun StatisticsBarChartByPeriod(
    periodData: PeriodData,
    text: String,
) {
    val headerFontSize: TextUnit = 18.sp
    val percentage: Float = periodData.getWaterCups().toFloat() / periodData.getSum()

    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = headerFontSize,
    )
    LinearProgressBar(
        percentage = percentage,
        teaNumber = periodData.getTeaCups(),
        waterNumber = periodData.getWaterCups(),
        width = 200.dp
    )
}

@Composable
fun LinearProgressBar(
    percentage: Float,
    teaNumber: Int,
    waterNumber: Int,
    fontSize: TextUnit = 24.sp,
    width: Dp = 100.dp,
    height: Dp = 40.dp,
    color1: Color = Color.Blue,
    color2: Color = Color.Red,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = waterNumber.toString(),
            color = MaterialTheme.colorScheme.primary,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Canvas(
            modifier = Modifier
                .size(width = width, height = height)
        ) {
            val breakOffset = Offset(size.width * percentage, 0f)

            drawRect(
                color = color1,
                topLeft = Offset.Zero,
                size = Size(breakOffset.x, size.height),
            )
            drawRect(
                color = color2,
                topLeft = breakOffset,
                size = Size(size.width - breakOffset.x, size.height),
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = teaNumber.toString(),
            color = MaterialTheme.colorScheme.primary,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )
    }
}