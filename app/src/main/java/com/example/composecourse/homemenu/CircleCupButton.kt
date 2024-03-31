package com.example.composecourse.homemenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleCupButton(
    title: String,
    buttonText: String,
    color: Color,
    onClickFunction: () -> Unit,
    size: Dp = 175.dp,
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = onClickFunction,
            modifier = Modifier
                .size(size)
                .padding(8.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
            ),
        ) {
            Text(
                text = buttonText,
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /*NONE*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
            ),
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
            )
        }
    }
}