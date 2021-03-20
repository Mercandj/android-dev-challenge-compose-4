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

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.example.androiddevchallenge.main_view.MainViewBackgroundView
import com.example.androiddevchallenge.theme.MainTheme
import com.example.androiddevchallenge.weather.Weather
import com.example.androiddevchallenge.weather_icon_view.WeatherIconView
import soup.neumorphism.NeumorphImageButton
import soup.neumorphism.ShapeType

@Composable
fun ForecastCellView(
    weather: Weather,
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val height = 130.dp
    Box(
        modifier = modifier
    ) {
        val light = MaterialTheme.colors.isLight
        val topStartShadowColor = if (light) "#C6CEDA" else "#101010"
        val bottomEndShadowColor = if (light) "#FEFEFF" else "#262C37"
        AndroidView(
            modifier = modifier
                .fillMaxWidth()
                .height(height),
            factory = { context ->
                NeumorphImageButton(context).apply {
                    setShadowColorLight(Color.parseColor(bottomEndShadowColor))
                    setShadowColorDark(Color.parseColor(topStartShadowColor))
                    setShapeType(ShapeType.DEFAULT)
                }
            }
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(height)
                .padding(14.dp)
        ) {
            WeatherIconView(
                weatherType = weather.type,
                modifier = Modifier.weight(2.2f)
            )
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Text(
                    text = "Mon " + (18 + weather.offsetDayFromToday),
                    fontSize = 13.sp,
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
                    preview = true,
                    modifier = Modifier.align(Alignment.BottomStart),
                    weather = Weather.fakeWeathers[0]
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
                    preview = true,
                    modifier = Modifier.align(Alignment.BottomStart),
                    weather = Weather.fakeWeathers[0]
                )
            }
        }
    }
}
