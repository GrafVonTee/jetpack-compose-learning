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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourse.roomtable.RecordState

@Composable
fun StatisticsBarChartByPeriod(
    recordState: RecordState,
    text: String,
) {
    val headerFontSize: TextUnit = 18.sp
    val percentage: Float = recordState.getWaterCups().toFloat() / recordState.getSum()

    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontSize = headerFontSize,
    )
    LinearProgressBar(
        percentage = percentage,
        teaNumber = recordState.getTeaCups(),
        waterNumber = recordState.getWaterCups(),
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
    cornerRadius: CornerRadius = CornerRadius(50f, 50f)
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

            drawPath(
                path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset.Zero,
                                size = Size(breakOffset.x, size.height),
                            ),
                            topLeft = cornerRadius,
                            bottomLeft = cornerRadius,
                        )
                    )
                },
                color = color1,
            )
            drawPath(
                path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = breakOffset,
                                size = Size(size.width - breakOffset.x, size.height),
                            ),
                            topRight = cornerRadius,
                            bottomRight = cornerRadius,
                        )
                    )
                },
                color = color2,
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