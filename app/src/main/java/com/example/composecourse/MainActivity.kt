package com.example.composecourse

import android.graphics.LinearGradient
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
                LinearProgressBar(0.3.toFloat(), 100)
            }
        }
    }
}

@Composable
fun LinearProgressBar(
    percentage: Float,
    maxNumber: Int,
    fontSize: TextUnit = 28.sp,
    width: Dp = 100.dp,
    height: Dp = 50.dp,
    gradient1: List<Color> = listOf(Color.Cyan, Color.Blue, Color.Black),
    gradient2: List<Color> = listOf(Color.Yellow, Color.Red, Color.Black),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = (percentage * maxNumber).toInt().toString(),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Canvas(
            modifier = Modifier
                .size(width = width, height = height)
        ) {
            val breakOffset: Offset = Offset(size.width * percentage, 0f)

            drawRoundRect(
                Brush.linearGradient(
                    colors = gradient1,
                    start = Offset.Zero,
                    end = Offset.Infinite,
                ),
                topLeft = Offset.Zero,
                size = Size(breakOffset.x, size.height),
            )
            drawRoundRect(
                Brush.linearGradient(
                    colors = gradient2,
                    start = Offset.Zero,
                    end = Offset.Infinite,
                ),
                topLeft = breakOffset,
                size = Size(size.width - breakOffset.x, size.height),
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = ((1 - percentage) * maxNumber).toInt().toString(),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )
    }
}