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
package com.example.androiddevchallenge.weather_api

import androidx.annotation.IntRange
import androidx.annotation.WorkerThread
import com.example.androiddevchallenge.weather.Weather
import com.example.androiddevchallenge.weather_unit.WeatherUnit

interface WeatherApiManager {

    /**
     * @throws WeatherApiException
     */
    @WorkerThread
    fun getWeather(
        city: String,
        weatherUnit: WeatherUnit
    ): Weather

    /**
     * @throws WeatherApiException
     */
    @WorkerThread
    fun getWeatherForecastDaily(
        city: String,
        weatherUnit: WeatherUnit,
        @IntRange(from = 1, to = 16)
        numberOfDays: Int
    ): List<Weather>
}
