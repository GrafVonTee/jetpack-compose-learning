package com.example.composecourse

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourseTheme {
                MyCanvas()
            }
        }
    }
}

@Composable
fun MyCanvas() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp),
    ) {
        drawRect(
            color = Color.Black,
            size = size
        )
        drawRect(
            color = Color.Red,
            topLeft = Offset(100f, 100f),
            size = Size(100f, 100f),
            style = Stroke(
                width = 10f,
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Yellow, Color.Red, Color.Black),
                center = center - Offset(75f, 75f),
                radius = 250f
            ),
            radius = 100f,
            center = center
        )

        drawArc(
            brush = Brush.radialGradient(
                colors = listOf(Color.Yellow, Color.Red, Color.Black),
                center = Offset(100f, 500f) + Offset(200f / 2, 200f / 2),
                radius = 200f
            ),
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true,
            topLeft = Offset(100f, 500f),
            size = Size(200f, 200f),
        )
        drawArc(
            brush = Brush.radialGradient(
                colors = listOf(Color.Cyan, Color.Blue, Color.Black),
                center = Offset(100f, 500f) + Offset(200f / 2, 200f / 2),
                radius = 200f
            ),
            startAngle = 270f,
            sweepAngle = 90f,
            useCenter = true,
            topLeft = Offset(100f, 500f),
            size = Size(200f, 200f),
        )

        drawArc(
            brush = Brush.radialGradient(
                colors = listOf(Color.Cyan, Color.Blue, Color.Black),
                center = Offset(500f, 500f) + Offset(200f / 4, 200f / 4),
                radius = 200f
            ),
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = false,
            topLeft = Offset(500f, 500f),
            size = Size(200f, 200f),
            style = Stroke(
                width = 5.dp.toPx(),
            )
        )

        drawOval(
            color = Color.Magenta,
            topLeft = Offset(500f, 100f),
            size = Size(200f, 300f),
        )

        drawLine(
            color = Color.Cyan,
            start = Offset(300f, 700f),
            end = Offset(700f, 700f),
            strokeWidth = 5.dp.toPx(),
        )
    }
}