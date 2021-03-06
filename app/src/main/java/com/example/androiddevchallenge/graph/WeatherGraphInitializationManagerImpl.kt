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

import com.example.androiddevchallenge.city.CityManager
import com.example.androiddevchallenge.weather.WeatherManager
import com.example.androiddevchallenge.weather_unit.WeatherUnitManager

class WeatherGraphInitializationManagerImpl(
    private val cityManager: CityManager,
    private val weatherManager: WeatherManager,
    private val weatherUnitManager: WeatherUnitManager
) : WeatherGraphInitializationManager {

    override fun initialize() {
        cityManager.addListener(createCityListener())
        weatherUnitManager.addListener(createWeatherUnitListener())
    }

    private fun createCityListener() = object : CityManager.Listener {
        override fun onChanged() {
            weatherManager.clearCache()
            weatherManager.load()
        }
    }

    private fun createWeatherUnitListener() = object : WeatherUnitManager.Listener {
        override fun onChanged() {
            weatherManager.clearCache()
            weatherManager.load()
        }
    }
}
