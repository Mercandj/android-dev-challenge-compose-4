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
package com.example.androiddevchallenge.weather

import com.example.androiddevchallenge.weather_unit.WeatherUnit
import java.util.concurrent.TimeUnit

data class Weather(
    val city: String,
    val weatherUnit: WeatherUnit,
    val type: Type,
    val temperature: Float,
    val humidity: Int,
    val pressure: Int,
    val timestampSecond: Long
) {

    init {
        if (timestampSecond < 10_000) {
            throw IllegalStateException("Timestamp < 10_000: $timestampSecond")
        }
    }

    enum class Type {
        CLEAR,
        THUNDERSTORM,
        DRIZZLE,
        CLOUDS,
        CLOUDS_SCATTERED_CLOUDS,
        CLOUDS_BROKEN_CLOUDS,
        CLOUDS_FEW_CLOUDS,
        RAIN,
        RAIN_FREEZING_RAIN,
        RAIN_VERY_HEAVY_RAIN,
        RAIN_HEAVY_INTENSITY,
        RAIN_MODERATE_RAIN,
        RAIN_LIGHT_RAIN,
        SNOW,
        SNOW_LIGHT_SNOW,
        MIST
    }

    companion object {

        val fakeWeathers = listOf(
            Weather(
                city = "Paris, France",
                weatherUnit = WeatherUnit.METRIC,
                type = Type.THUNDERSTORM,
                temperature = 19f,
                humidity = 90,
                pressure = 900,
                timestampSecond = 1616321094
            ),
            Weather(
                city = "Paris, France",
                weatherUnit = WeatherUnit.METRIC,
                type = Type.CLOUDS,
                temperature = 12f,
                humidity = 90,
                pressure = 900,
                timestampSecond = 1616321094 + TimeUnit.DAYS.toMillis(1)
            ),
            Weather(
                city = "Paris, France",
                weatherUnit = WeatherUnit.METRIC,
                type = Type.CLEAR,
                temperature = 30f,
                humidity = 90,
                pressure = 900,
                timestampSecond = 1616321094 + TimeUnit.DAYS.toMillis(2)
            ),
            Weather(
                city = "Paris, France",
                weatherUnit = WeatherUnit.METRIC,
                type = Type.SNOW,
                temperature = -2f,
                humidity = 90,
                pressure = 900,
                timestampSecond = 1616321094 + TimeUnit.DAYS.toMillis(3)
            )
        )

        fun List<Weather>.getOrNull(index: Int): Weather? {
            if (index >= size) {
                return null
            }
            return get(index)
        }
    }
}
