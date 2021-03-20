/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.main_view_top_bar_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.graph.WeatherGraph
import com.example.androiddevchallenge.theme.MainTheme
import com.example.androiddevchallenge.theme.getMainViewTopBarViewTextColor

@Composable
fun MainViewTopBar(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val textColor = MaterialTheme.colors.getMainViewTopBarViewTextColor()
    val userAction = Mvp(preview).createUserAction()
    val temperatureState by userAction.getTemperature().observeAsState()
    Row(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = "Paris, France",
                fontSize = 20.sp,
                color = textColor,
                modifier = Modifier
                    .height(25.dp)
            )
            Text(
                text = "Electric",
                fontSize = 20.sp,
                color = textColor,
                modifier = Modifier
                    .height(25.dp)
            )
        }
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .clickable { userAction.onTemperatureClick() },
            text = temperatureState!!,
            fontSize = 42.sp,
            color = textColor,
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewTopBarViewLightPreview() {
    MainTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainViewTopBar(preview = true)
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewTopBarViewDarkPreview() {
    MainTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            MainViewTopBar(preview = true)
        }
    }
}

private class Mvp(
    private val preview: Boolean
) {

    private fun createScreen() = object : MainViewTopBarViewContract.Screen {
    }

    fun createUserAction(): MainViewTopBarViewContract.UserAction {
        if (preview) {
            return object : MainViewTopBarViewContract.UserAction {
                private var temperature = MutableLiveData("28°C")
                override fun getTemperature(): MutableLiveData<String> = temperature
                override fun onTemperatureClick() {}
            }
        }
        return MainViewTopBarViewPresenter(
            createScreen(),
            WeatherGraph.getWeatherManager()
        )
    }
}
