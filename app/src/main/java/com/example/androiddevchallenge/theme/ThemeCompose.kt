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
package com.example.androiddevchallenge.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.weather.Weather

private val DarkColorPalette = darkColors(
    primary = Color(0xFFFDE807),
    primaryVariant = Color(0xFFFDE807),
    secondary = Color(0xFF30B0D3),
    background = Color(0xFF0B222F)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF0B222F),
    primaryVariant = Color(0xFF0B222F),
    secondary = Color(0xFF30B0D3),
    background = Color(0xFFEEF0F5),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun Colors.getMainViewTopBackgroundColor(): Color {
    return if (isLight) {
        Color(0XFFEEF0F5)
    } else {
        Color(0XFF394253)
    }
}

@Composable
fun Colors.getMainViewBottomBackgroundColor(): Color {
    return if (isLight) {
        Color(0XFFEEF0F5)
    } else {
        Color(0XFF181C27)
    }
}

@Composable
fun Colors.getMainViewTopBarViewTextColor(): Color {
    return if (isLight) {
        Color.Black
    } else {
        Color.White
    }
}

@Composable
fun Colors.getMainViewTopBarViewDateTextColor(weatherType: Weather.Type): Color {
    when (weatherType) {
        Weather.Type.CLEAR -> if (isLight) {
            Color(0xFFEB8202)
        } else {
            Color(0xFFD89037)
        }
        Weather.Type.THUNDERSTORM -> {
        }
        Weather.Type.DRIZZLE -> {
        }
        Weather.Type.CLOUDS,
        Weather.Type.CLOUDS_SCATTERED_CLOUDS,
        Weather.Type.CLOUDS_BROKEN_CLOUDS,
        Weather.Type.CLOUDS_FEW_CLOUDS -> return if (isLight) {
            Color(0xFF575757)
        } else {
            Color(0xFFBBBBBB)
        }
        Weather.Type.RAIN,
        Weather.Type.RAIN_FREEZING_RAIN,
        Weather.Type.RAIN_VERY_HEAVY_RAIN,
        Weather.Type.RAIN_HEAVY_INTENSITY -> return if (isLight) {
            Color(0xFF00A1CE)
        } else {
            Color(0xFF30B0D3)
        }
        Weather.Type.RAIN_MODERATE_RAIN -> {
        }
        Weather.Type.RAIN_LIGHT_RAIN -> {
        }
        Weather.Type.SNOW -> {
        }
        Weather.Type.SNOW_LIGHT_SNOW -> {
        }
        Weather.Type.MIST -> {
        }
    }
    return if (isLight) {
        Color(0xFFEBCC02)
    } else {
        Color(0xFFFDE807)
    }
}

@Composable
fun Colors.getMainViewTopViewCardColor(): Color {
    return if (isLight) {
        Color(0XFFEEF0F5)
    } else {
        Color(0XFF394253)
    }
}

@Composable
fun Colors.getTextPrimaryColor(): Color {
    return if (isLight) {
        Color(0XFF000000)
    } else {
        Color(0XFFFFFFFF)
    }
}

@Composable
fun Colors.getTextSecondaryColor(): Color {
    return if (isLight) {
        Color(0XFF404040)
    } else {
        Color(0XFFC0C0C0)
    }
}

@Composable
fun Colors.getTextThirdColor(): Color {
    return if (isLight) {
        Color(0XFF808080)
    } else {
        Color(0XFFA0A0A0)
    }
}

@Composable
fun Colors.getTextHintColor(): Color {
    return if (isLight) {
        Color(0XFFB0B0B0)
    } else {
        Color(0XFF909090)
    }
}

@Composable
fun Colors.getForecastViewCardColor(): Color {
    return if (isLight) {
        Color(0XFFEEF0F5)
    } else {
        Color(0XFF181C27)
    }
}

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
