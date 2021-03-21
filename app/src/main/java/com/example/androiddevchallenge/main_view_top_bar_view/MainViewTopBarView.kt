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

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.graph.WeatherGraph
import com.example.androiddevchallenge.neumorphism_card_square_view.NeumorphismCardSquareView
import com.example.androiddevchallenge.theme.MainTheme
import com.example.androiddevchallenge.theme.getMainViewBottomBackgroundColor
import com.example.androiddevchallenge.theme.getMainViewTopBackgroundColor
import com.example.androiddevchallenge.theme.getMainViewTopBarViewDateTextColor
import com.example.androiddevchallenge.theme.getMainViewTopBarViewTextColor
import com.example.androiddevchallenge.theme.getMainViewTopViewCardColor
import com.example.androiddevchallenge.weather.Weather
import com.example.androiddevchallenge.weather_unit.WeatherUnit

@Composable
fun MainViewTopBarView(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val textColor = MaterialTheme.colors.getMainViewTopBarViewTextColor()
    val userAction = Mvp(preview).createUserAction()
    val cityState by userAction.getCity().observeAsState()
    val dateState by userAction.getDate().observeAsState()
    val temperatureState by userAction.getTemperature().observeAsState()
    val weatherTypeState by userAction.getWeatherType().observeAsState()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .align(Alignment.Top)
                .padding(start = 24.dp, top = 20.dp, end = 24.dp)
                .clickable { userAction.onCityClicked() }
        ) {
            Text(
                text = cityState!!,
                fontSize = 22.sp,
                color = textColor,
                fontWeight = FontWeight(900),
                modifier = Modifier
                    .wrapContentHeight()
            )
            Text(
                text = dateState!!,
                fontSize = 22.sp,
                color = MaterialTheme.colors.getMainViewTopBarViewDateTextColor(
                    weatherTypeState!!
                ),
                fontWeight = FontWeight(900),
                modifier = Modifier
                    .wrapContentHeight()
            )
        }
        var temperatureContainerStatus by remember {
            mutableStateOf(
                if (preview) TemperatureContainerStatus.EXPANDED else TemperatureContainerStatus.COLLAPSED
            )
        }
        val temperatureContainerTransition =
            updateTransition(targetState = temperatureContainerStatus)
        val temperatureContainerWidth by temperatureContainerTransition.animateDp {
            when (it) {
                TemperatureContainerStatus.COLLAPSED -> 130.dp
                TemperatureContainerStatus.EXPANDED -> 200.dp
            }
        }
        val temperatureContainerHeight by temperatureContainerTransition.animateDp {
            when (it) {
                TemperatureContainerStatus.COLLAPSED -> 110.dp
                TemperatureContainerStatus.EXPANDED -> 250.dp
            }
        }
        NeumorphismCardSquareView(
            modifier = Modifier
                .width(temperatureContainerWidth)
                .height(temperatureContainerHeight),
            backgroundColor = MaterialTheme.colors.getMainViewTopViewCardColor(),
            onClick = {
                userAction.onTemperatureClick()
                temperatureContainerStatus = when (temperatureContainerStatus) {
                    TemperatureContainerStatus.COLLAPSED -> TemperatureContainerStatus.EXPANDED
                    TemperatureContainerStatus.EXPANDED -> TemperatureContainerStatus.COLLAPSED
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(110.dp)
                    .align(Alignment.TopEnd)
                    .zIndex(2f)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(2f)
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    text = temperatureState!!,
                    fontSize = 40.sp,
                    fontWeight = FontWeight(900),
                    color = textColor
                )
                if (temperatureState == "") {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .align(Alignment.Center)
                            .zIndex(2f),
                        color = textColor
                    )
                }
            }
            val temperatureUnityAlpha by temperatureContainerTransition.animateFloat {
                when (it) {
                    TemperatureContainerStatus.COLLAPSED -> 0f
                    TemperatureContainerStatus.EXPANDED -> 1f
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 32.dp)
                    .alpha(temperatureUnityAlpha)
            ) {
                Spacer(modifier = Modifier.height(90.dp))
                Text(
                    text = "Temperature",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = textColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.clickable {
                        userAction.onTemperatureUnitClicked(WeatherUnit.STANDARD)
                    }
                ) {
                    RadioButton(
                        selected = userAction.onTemperatureUnitDisplay(WeatherUnit.STANDARD),
                        onClick = {
                            userAction.onTemperatureUnitClicked(WeatherUnit.STANDARD)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Standard",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = textColor
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.clickable {
                        userAction.onTemperatureUnitClicked(WeatherUnit.METRIC)
                    }
                ) {
                    RadioButton(
                        selected = userAction.onTemperatureUnitDisplay(WeatherUnit.METRIC),
                        onClick = {
                            userAction.onTemperatureUnitClicked(WeatherUnit.METRIC)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Metric",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = textColor
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.clickable {
                        userAction.onTemperatureUnitClicked(WeatherUnit.IMPERIAL)
                    }
                ) {
                    RadioButton(
                        selected = userAction.onTemperatureUnitDisplay(WeatherUnit.IMPERIAL),
                        onClick = {
                            userAction.onTemperatureUnitClicked(WeatherUnit.IMPERIAL)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Imperial",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = textColor
                    )
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewTopBarViewLightPreview() {
    MainTheme {
        Surface {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.getMainViewTopBackgroundColor(),
                            MaterialTheme.colors.getMainViewBottomBackgroundColor()
                        )
                    )
                )
            ) {
                MainViewTopBarView(preview = true)
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewTopBarViewDarkPreview() {
    MainTheme(darkTheme = true) {
        Surface {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.getMainViewTopBackgroundColor(),
                            MaterialTheme.colors.getMainViewBottomBackgroundColor()
                        )
                    )
                )
            ) {
                MainViewTopBarView(preview = true)
            }
        }
    }
}

private enum class TemperatureContainerStatus {
    COLLAPSED,
    EXPANDED
}

private class Mvp(
    private val preview: Boolean
) {

    private fun createScreen() = object : MainViewTopBarViewContract.Screen {
    }

    fun createUserAction(): MainViewTopBarViewContract.UserAction {
        if (preview) {
            return object : MainViewTopBarViewContract.UserAction {
                private var city = MutableLiveData("Paris, France")
                private var date = MutableLiveData("Mon 18")
                private var temperature = MutableLiveData("28°")
                private var weatherType = MutableLiveData(Weather.Type.SNOW)
                override fun getCity(): MutableLiveData<String> = city
                override fun getDate(): MutableLiveData<String> = date
                override fun getTemperature(): MutableLiveData<String> = temperature
                override fun getWeatherType(): MutableLiveData<Weather.Type> = weatherType
                override fun onTemperatureClick() {}
                override fun onCityClicked() {}
                override fun onTemperatureUnitClicked(weatherUnit: WeatherUnit) {}
                override fun onTemperatureUnitDisplay(metric: WeatherUnit) = false
            }
        }
        return MainViewTopBarViewPresenter(
            createScreen(),
            WeatherGraph.getCityManager(),
            WeatherGraph.getDateManager(),
            WeatherGraph.getWeatherManager(),
            WeatherGraph.getWeatherUnitManager()
        )
    }
}
