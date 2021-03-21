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
package com.example.androiddevchallenge.graph

import android.annotation.SuppressLint
import android.content.Context
import com.example.androiddevchallenge.city.CityModule
import com.example.androiddevchallenge.network.NetworkModule
import com.example.androiddevchallenge.theme.ThemeModule
import com.example.androiddevchallenge.weather.WeatherModule
import com.example.androiddevchallenge.weather_unit.WeatherUnitModule

class WeatherGraph private constructor(
    private val context: Context
) {

    private val cityManager by lazy { CityModule().createCityManager() }
    private val networkManager by lazy { NetworkModule().createNetworkManager() }
    private val themeManager by lazy { ThemeModule().createThemeManager() }
    private val weatherManager by lazy { WeatherModule().createWeatherManager() }
    private val weatherUnitManager by lazy { WeatherUnitModule().createWeatherUnitManager() }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var graph: WeatherGraph? = null

        fun initialize(context: Context) {
            if (graph != null) {
                return
            }
            graph = WeatherGraph(context.applicationContext)
        }

        fun getCityManager() = graph!!.cityManager
        fun getContext() = graph!!.context
        fun getNetworkManager() = graph!!.networkManager
        fun getThemeManager() = graph!!.themeManager
        fun getWeatherManager() = graph!!.weatherManager
        fun getWeatherUnitManager() = graph!!.weatherUnitManager
    }
}
