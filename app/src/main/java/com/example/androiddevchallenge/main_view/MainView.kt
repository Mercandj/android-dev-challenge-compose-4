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
package com.example.androiddevchallenge.main_view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.city_edit_view.CityEditView
import com.example.androiddevchallenge.error_view.ErrorView
import com.example.androiddevchallenge.forecast_cell_view.ForecastCellView
import com.example.androiddevchallenge.graph.WeatherGraph
import com.example.androiddevchallenge.main_view_background_view.MainViewBackgroundView
import com.example.androiddevchallenge.main_view_top_bar_view.MainViewTopBarView
import com.example.androiddevchallenge.main_weather_animated_view.MainWeatherAnimatedView
import com.example.androiddevchallenge.theme.MainTheme
import com.example.androiddevchallenge.weather.Weather

@Composable
fun MainView(
    preview: Boolean = false
) {
    val userAction = Mvp(preview).createUserAction()
    val weathersState = userAction.getWeathers().observeAsState()
    val errorState by userAction.getError().observeAsState()
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        MainViewBackgroundView {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .zIndex(2f)
            ) {
                Spacer(modifier = Modifier.height(6.dp))
                CityEditView(
                    preview = preview,
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorState!!) {
                    ErrorView(preview = preview)
                } else {
                    MainViewTopBarView(
                        preview = preview
                    )
                }
            }
            if (!errorState!!) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(1f)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    MainWeatherAnimatedView(
                        preview = preview,
                        weather = weathersState.value?.getOrNull(0)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 6.dp, end = 6.dp)
                        .align(Alignment.BottomCenter)
                        .zIndex(4f)
                ) {
                    val weathers = weathersState.value!!
                    Row {
                        ForecastCellView(
                            weather = weathers.getOrNull(0),
                            modifier = Modifier.weight(1f),
                            preview = preview
                        )
                        ForecastCellView(
                            weather = weathers.getOrNull(1),
                            modifier = Modifier.weight(1f),
                            preview = preview
                        )
                        ForecastCellView(
                            weather = weathers.getOrNull(2),
                            modifier = Modifier.weight(1f),
                            preview = preview
                        )
                        ForecastCellView(
                            weather = weathers.getOrNull(3),
                            modifier = Modifier.weight(1f),
                            preview = preview
                        )
                    }
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewLightPreview() {
    MainTheme {
        MainView(preview = true)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewDarkPreview() {
    MainTheme(darkTheme = true) {
        MainView(preview = true)
    }
}

private class Mvp(
    private val preview: Boolean
) {

    private fun createScreen() = object : MainViewContract.Screen {
    }

    fun createUserAction(): MainViewContract.UserAction {
        if (preview) {
            return object : MainViewContract.UserAction {
                private var weathers = MutableLiveData(Weather.fakeWeathers)
                private var error = MutableLiveData(false)
                override fun getWeathers(): MutableLiveData<List<Weather>> = weathers
                override fun getError(): MutableLiveData<Boolean> = error
            }
        }
        return MainViewPresenter(
            createScreen(),
            WeatherGraph.getWeatherManager()
        )
    }
}
