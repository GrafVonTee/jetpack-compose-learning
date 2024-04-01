package com.example.composecourse

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.ButtonColors
import androidx.glance.ButtonDefaults
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory.Companion
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.composecourse.roomtable.RecordDatabase
import com.example.composecourse.roomtable.RecordEvent
import com.example.composecourse.roomtable.RecordStateViewModel

class CounterWidget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            InitializeDatabase()
            Text(text = "Hello world")
            Content()
        }
    }

    private lateinit var viewModel: RecordStateViewModel

    @Composable
    private fun InitializeDatabase() {
        val db = Room.databaseBuilder(
            LocalContext.current,
            RecordDatabase::class.java, "records.db"
        ).build()

        @Suppress("UNCHECKED_CAST")
        viewModel = viewModel<RecordStateViewModel>(
            factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return RecordStateViewModel(db.dao) as T
                }
            }
        )
    }

    @Composable
    private fun Content() {
        val state by viewModel.state.collectAsState()
        val currentTeaCups = state.getToday().getTeaCups()
        val currentWaterCups = state.getToday().getWaterCups()

        Row(
            modifier = GlanceModifier
                .background(Color.DarkGray)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircleCupButton(
                title = "Чай",
                buttonText = currentTeaCups.toString(),
                color = Color.Red,
                onClickFunction = { IncreaseTeaCupsActionCallback::class.java },
                size = 75.dp,
            )
            CircleCupButton(
                title = "Вода",
                buttonText = currentWaterCups.toString(),
                color = Color.Blue,
                onClickFunction = { IncreaseWaterCupsActionCallback::class.java },
                size = 75.dp,
            )
        }
    }

    fun increaseTeaCount() {
        viewModel.onEvent(RecordEvent.SaveTeaRecord)
    }

    fun increaseWaterCount() {
        viewModel.onEvent(RecordEvent.SaveWaterRecord)
    }
}

class SimpleCounterWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = CounterWidget()
}

class IncreaseWaterCupsActionCallback: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        CounterWidget().increaseWaterCount()
        CounterWidget().update(context, glanceId)
    }
}

class IncreaseTeaCupsActionCallback: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        CounterWidget().increaseTeaCount()
        CounterWidget().update(context, glanceId)
    }
}

@Composable
fun CircleCupButton(
    title: String,
    buttonText: String,
    color: Color,
    onClickFunction: () -> Unit,
    size: Dp = 75.dp,
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            text = buttonText,
            onClick = onClickFunction,
            modifier = GlanceModifier
                .size(size)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = ColorProvider(color),
            ),
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
            ),
        )

        Spacer(modifier = GlanceModifier.height(8.dp))

        Button(
            text = title,
            onClick = { /*NONE*/ },
            modifier = GlanceModifier
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = ColorProvider(color),
            ),
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}