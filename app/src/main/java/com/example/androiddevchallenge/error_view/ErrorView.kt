package com.example.androiddevchallenge.error_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.theme.getTextPrimaryColor
import com.example.androiddevchallenge.theme.getTextSecondaryColor
import com.example.androiddevchallenge.theme.getTextThirdColor

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "No weather found",
            fontSize = 28.sp,
            color = MaterialTheme.colors.getTextPrimaryColor(),
            fontWeight = FontWeight(900),
        )
        Spacer(modifier = Modifier.height(14.dp))
        Image(
            painter = painterResource(R.drawable.main_weather_animated_view_figure_7),
            contentDescription = "No internet image",
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "Possible reasons:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 22.sp,
            color = MaterialTheme.colors.getTextSecondaryColor(),
            fontWeight = FontWeight(700),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "- The city is unknown",
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "- You do not have internet",
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "- The server is OFF",
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600),
        )
    }
}