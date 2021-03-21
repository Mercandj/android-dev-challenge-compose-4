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
package com.example.androiddevchallenge.main_weather_animated_view

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.main_view.MainViewBackgroundView
import com.example.androiddevchallenge.theme.MainTheme
import com.example.androiddevchallenge.weather.Weather
import com.example.androiddevchallenge.weather_icon_view.WeatherIconView

@Composable
fun MainWeatherAnimatedView(
    modifier: Modifier = Modifier,
    weather: Weather?,
    preview: Boolean = false
) {
    Box(
        modifier = modifier
            .width(280.dp)
            .height(280.dp)
    ) {
        val circleScale by rememberInfiniteTransition().animateFloat(
            initialValue = 2.3f,
            targetValue = 2.4f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            )
        )
        Image(
            painter = painterResource(R.drawable.main_weather_animated_view_circle_with_shadow),
            contentDescription = "Weather",
            colorFilter = ColorFilter.tint(getCircleColor(weather)),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .zIndex(0f)
                .scale(circleScale)
                .padding(
                    start = 0.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp
                )
                .align(Alignment.Center)
        )
        val circleGradientScale by rememberInfiniteTransition().animateFloat(
            initialValue = 0.8f,
            targetValue = 0.85f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            )
        )
        Image(
            painter = painterResource(R.drawable.main_weather_animated_view_circle_gradient_yellow),
            contentDescription = "Weather",
            colorFilter = ColorFilter.tint(getCircleColor(weather)),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .scale(circleGradientScale)
                .zIndex(1f)
                .align(Alignment.Center)
        )
        val figureScale by rememberInfiniteTransition().animateFloat(
            initialValue = 1.0f,
            targetValue = 1.12f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            )
        )
        Image(
            painter = painterResource(getFigureDrawableRes(weather = weather)),
            contentDescription = "Weather figure",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .scale(figureScale)
                .zIndex(2f)
                .align(Alignment.Center)
        )
        if (weather != null) {
            Box(
                modifier = Modifier
                    .zIndex(3f)
                    .align(Alignment.TopStart)
                    .offset(x = (-50).dp, y = (-50).dp)
            ) {
                WeatherIconView(
                    modifier = Modifier
                        .width(180.dp)
                        .height(180.dp),
                    weatherType = weather.type
                )
            }
            Box(
                modifier = Modifier
                    .zIndex(3f)
                    .align(Alignment.BottomEnd)
                    .offset(x = 50.dp, y = 50.dp)
            ) {
                WeatherIconView(
                    modifier = Modifier
                        .width(180.dp)
                        .height(180.dp),
                    weatherType = weather.type
                )
            }
        }
    }
}

@DrawableRes
private fun getFigureDrawableRes(weather: Weather?): Int {
    if (weather == null) {
        return R.drawable.main_weather_animated_view_figure_1
    }
    return when (weather.type) {
        Weather.Type.CLEAR -> R.drawable.main_weather_animated_view_figure_1
        Weather.Type.THUNDERSTORM -> R.drawable.main_weather_animated_view_figure_1
        Weather.Type.DRIZZLE -> R.drawable.main_weather_animated_view_figure_1
        Weather.Type.CLOUDS -> R.drawable.main_weather_animated_view_figure_2
        Weather.Type.CLOUDS_SCATTERED_CLOUDS -> R.drawable.main_weather_animated_view_figure_3
        Weather.Type.CLOUDS_BROKEN_CLOUDS -> R.drawable.main_weather_animated_view_figure_4
        Weather.Type.CLOUDS_FEW_CLOUDS -> R.drawable.main_weather_animated_view_figure_5
        Weather.Type.RAIN -> R.drawable.main_weather_animated_view_figure_6
        Weather.Type.RAIN_FREEZING_RAIN -> R.drawable.main_weather_animated_view_figure_7
        Weather.Type.RAIN_VERY_HEAVY_RAIN -> R.drawable.main_weather_animated_view_figure_1
        Weather.Type.RAIN_HEAVY_INTENSITY -> R.drawable.main_weather_animated_view_figure_2
        Weather.Type.RAIN_MODERATE_RAIN -> R.drawable.main_weather_animated_view_figure_3
        Weather.Type.RAIN_LIGHT_RAIN -> R.drawable.main_weather_animated_view_figure_4
        Weather.Type.SNOW -> R.drawable.main_weather_animated_view_figure_5
        Weather.Type.SNOW_LIGHT_SNOW -> R.drawable.main_weather_animated_view_figure_6
        Weather.Type.MIST -> R.drawable.main_weather_animated_view_figure_1
    }
}

@Composable
private fun getCircleColor(weather: Weather?): Color {
    if (weather == null) {
        return Color(0xFFFFEB3B)
    }
    return if (MaterialTheme.colors.isLight) {
        when (weather.type) {
            Weather.Type.CLEAR -> Color(0xFFDDC600)
            Weather.Type.THUNDERSTORM -> Color(0xFFDDC600)
            Weather.Type.DRIZZLE -> Color(0xFFDDC600)
            Weather.Type.CLOUDS -> Color(0xFF838383)
            Weather.Type.CLOUDS_SCATTERED_CLOUDS -> Color(0xFF838383)
            Weather.Type.CLOUDS_BROKEN_CLOUDS -> Color(0xFF838383)
            Weather.Type.CLOUDS_FEW_CLOUDS -> Color(0xFFDDC600)
            Weather.Type.RAIN -> Color(0xFF30B0D3)
            Weather.Type.RAIN_FREEZING_RAIN -> Color(0xFF30B0D3)
            Weather.Type.RAIN_VERY_HEAVY_RAIN -> Color(0xFF30B0D3)
            Weather.Type.RAIN_HEAVY_INTENSITY -> Color(0xFF30B0D3)
            Weather.Type.RAIN_MODERATE_RAIN -> Color(0xFF30B0D3)
            Weather.Type.RAIN_LIGHT_RAIN -> Color(0xFF30B0D3)
            Weather.Type.SNOW -> Color(0xFFC2C2C2)
            Weather.Type.SNOW_LIGHT_SNOW -> Color(0xFFC2C2C2)
            Weather.Type.MIST -> Color(0xFFC2C2C2)
        }
    } else {
        when (weather.type) {
            Weather.Type.CLEAR -> Color(0xFFFFEB3B)
            Weather.Type.THUNDERSTORM -> Color(0xFFFFEB3B)
            Weather.Type.DRIZZLE -> Color(0xFFFFEB3B)
            Weather.Type.CLOUDS -> Color(0xFF838383)
            Weather.Type.CLOUDS_SCATTERED_CLOUDS -> Color(0xFF838383)
            Weather.Type.CLOUDS_BROKEN_CLOUDS -> Color(0xFFFFEB3B)
            Weather.Type.CLOUDS_FEW_CLOUDS -> Color(0xFFFFEB3B)
            Weather.Type.RAIN -> Color(0xFF30B0D3)
            Weather.Type.RAIN_FREEZING_RAIN -> Color(0xFF30B0D3)
            Weather.Type.RAIN_VERY_HEAVY_RAIN -> Color(0xFF30B0D3)
            Weather.Type.RAIN_HEAVY_INTENSITY -> Color(0xFF30B0D3)
            Weather.Type.RAIN_MODERATE_RAIN -> Color(0xFF30B0D3)
            Weather.Type.RAIN_LIGHT_RAIN -> Color(0xFF30B0D3)
            Weather.Type.SNOW -> Color(0xFFC2C2C2)
            Weather.Type.SNOW_LIGHT_SNOW -> Color(0xFFC2C2C2)
            Weather.Type.MIST -> Color(0xFFC2C2C2)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainWeatherAnimatedViewLightPreview() {
    MainTheme {
        Surface {
            MainViewBackgroundView {
                MainWeatherAnimatedView(
                    preview = true,
                    modifier = Modifier.align(Alignment.Center),
                    weather = Weather.fakeWeathers[0]
                )
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainWeatherAnimatedViewDarkPreview() {
    MainTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            MainViewBackgroundView {
                MainWeatherAnimatedView(
                    preview = true,
                    modifier = Modifier.align(Alignment.Center),
                    weather = Weather.fakeWeathers[0]
                )
            }
        }
    }
}
