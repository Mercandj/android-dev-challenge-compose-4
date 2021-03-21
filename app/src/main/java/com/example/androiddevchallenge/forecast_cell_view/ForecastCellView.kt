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
package com.example.androiddevchallenge.forecast_cell_view

import android.animation.AnimatorInflater.loadStateListAnimator
import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.graph.WeatherGraph
import com.example.androiddevchallenge.main_view.MainViewBackgroundView
import com.example.androiddevchallenge.theme.MainTheme
import com.example.androiddevchallenge.theme.getForecastViewCardColor
import com.example.androiddevchallenge.theme.getTextPrimaryColor
import com.example.androiddevchallenge.weather.Weather
import com.example.androiddevchallenge.weather_icon_view.WeatherIconView
import soup.neumorphism.NeumorphImageButton
import soup.neumorphism.ShapeType

@Composable
fun ForecastCellView(
    weather: Weather?,
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val userAction = Mvp(preview = preview, weather = weather).createUserAction()
    Column(
        modifier = modifier
            .height(150.dp)
    ) {
        Box(
            modifier = modifier
                .height(130.dp)
        ) {
            val light = MaterialTheme.colors.isLight
            val topStartShadowColor = if (light) "#C6CEDA" else "#101010"
            val bottomEndShadowColor = if (light) "#FEFEFF" else "#262C37"
            val forecastViewCardColor = MaterialTheme.colors.getForecastViewCardColor()
            AndroidView(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                factory = { context ->
                    NeumorphImageButton(context).apply {
                        setShadowColorLight(Color.parseColor(bottomEndShadowColor))
                        setShadowColorDark(Color.parseColor(topStartShadowColor))
                        setShapeType(ShapeType.DEFAULT)
                        setBackgroundColor(
                            argb(
                                forecastViewCardColor.alpha,
                                forecastViewCardColor.red,
                                forecastViewCardColor.green,
                                forecastViewCardColor.blue
                            )
                        )
                        if (!preview) {
                            stateListAnimator = loadStateListAnimator(
                                context,
                                R.animator.button_state_list_anim_neumorph
                            )
                        }
                        setOnClickListener {
                        }
                    }
                }
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(14.dp)
            ) {
                if (weather == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                                .align(Alignment.Center)
                                .zIndex(2f),
                            color = MaterialTheme.colors.getTextPrimaryColor()
                        )
                    }
                } else {
                    WeatherIconView(
                        weatherType = weather.type,
                        modifier = Modifier.weight(2.2f)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "${weather.temperature.toInt()}°",
                            fontSize = 19.sp,
                            fontWeight = FontWeight(900),
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(bottom = 4.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (weather != null) {
                // To put in presenter the text creation
                Text(
                    text = userAction.onDisplayTextTemperature(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(900),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(bottom = 4.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun ForecastCellViewLightPreview() {
    MainTheme {
        Surface {
            MainViewBackgroundView {
                ForecastCellView(
                    modifier = Modifier.align(Alignment.BottomStart),
                    weather = Weather.fakeWeathers[0],
                    preview = true
                )
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun ForecastCellViewDarkPreview() {
    MainTheme(darkTheme = true) {
        Surface {
            MainViewBackgroundView {
                ForecastCellView(
                    modifier = Modifier.align(Alignment.BottomStart),
                    weather = Weather.fakeWeathers[0],
                    preview = true
                )
            }
        }
    }
}

private fun argb(alpha: Float, red: Float, green: Float, blue: Float): Int {
    return (alpha * 255.0f + 0.5f).toInt() shl 24 or
        ((red * 255.0f + 0.5f).toInt() shl 16) or
        ((green * 255.0f + 0.5f).toInt() shl 8) or
        (blue * 255.0f + 0.5f).toInt()
}

private class Mvp(
    private val preview: Boolean,
    private val weather: Weather?
) {

    private fun createScreen() = object : ForecastCellViewContract.Screen {
    }

    fun createUserAction(): ForecastCellViewContract.UserAction {
        if (preview) {
            return object : ForecastCellViewContract.UserAction {
                override fun onDisplayTextTemperature(): String = "28°"
            }
        }
        return ForecastCellViewPresenter(
            createScreen(),
            weather,
            WeatherGraph.getDateManager()
        )
    }
}
