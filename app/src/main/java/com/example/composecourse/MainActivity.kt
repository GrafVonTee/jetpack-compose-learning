package com.example.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.composecourse.navigation.NavigationScreen
import com.example.composecourse.roomtable.RecordStateViewModel
import com.example.composecourse.roomtable.RecordDatabase
import com.example.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            RecordDatabase::class.java,
            "records.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourseTheme {
                @Suppress("UNCHECKED_CAST")
                val viewModel = viewModel<RecordStateViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return RecordStateViewModel(db.dao) as T
                        }
                    }
                )

                val state by viewModel.state.collectAsState()
                NavigationScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}