package com.example.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourseTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        StatisticsByPeriod(
                            percentage = 0.3.toFloat(),
                            maxNumber = 100,
                            text = "Статистика за день",
                        )

                        StatisticsByPeriod(
                            percentage = 0.6.toFloat(),
                            maxNumber = 350,
                            text = "Статистика за неделю",
                        )

                        StatisticsByPeriod(
                            percentage = 0.1.toFloat(),
                            maxNumber = 350,
                            text = "Статистика за месяц",
                        )

                        StatisticsByPeriod(
                            percentage = 0.5.toFloat(),
                            maxNumber = 8888,
                            text = "Статистика за всё время",
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun StatisticsByPeriod(
    percentage: Float,
    maxNumber: Int,
    text: String,
) {
    val headerFontSize: TextUnit = 18.sp

    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = headerFontSize,
    )
    LinearProgressBar(percentage, maxNumber, width = 200.dp)
}

@Composable
fun LinearProgressBar(
    percentage: Float,
    maxNumber: Int,
    fontSize: TextUnit = 24.sp,
    width: Dp = 100.dp,
    height: Dp = 30.dp,
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
            text = (percentage * maxNumber).toInt().toString(),
            color = MaterialTheme.colorScheme.primary,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Canvas(
            modifier = Modifier
                .size(width = width, height = height)
        ) {
            val breakOffset: Offset = Offset(size.width * percentage, 0f)

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
            text = ((1 - percentage) * maxNumber).toInt().toString(),
            color = MaterialTheme.colorScheme.primary,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )
    }
}