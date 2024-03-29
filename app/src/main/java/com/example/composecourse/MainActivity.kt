package com.example.composecourse

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                var name by remember {
                    mutableStateOf("")
                }
                var nameList by remember {
                    mutableStateOf(listOf<String>())
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { text ->
                                name = text
                            },
                            modifier = Modifier
                                .weight(1f)
                        )

                        Spacer(modifier = Modifier
                            .width(16.dp)
                        )

                        Button(onClick = {
                            if (name.isNotBlank()) {
                                nameList += name
                                name = ""
                            }
                        }) {
                            Text(text = "Add")
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    NameList(
                        nameList,
                        Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                            .padding(10.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun NameList(
    nameList: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier) {
        items (nameList) { currentName ->
            Text(
                text = currentName,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Divider()
        }
    }
}