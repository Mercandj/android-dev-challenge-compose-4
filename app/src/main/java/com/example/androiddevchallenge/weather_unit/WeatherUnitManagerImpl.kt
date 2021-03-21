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
package com.example.androiddevchallenge.weather_unit

import android.content.SharedPreferences

class WeatherUnitManagerImpl(
    private val sharedPreferences: SharedPreferences
) : WeatherUnitManager {

    private val listeners = ArrayList<WeatherUnitManager.Listener>()
    private var weatherUnit = WeatherUnit.METRIC
    private var loaded = false

    override fun getWeatherUnit(): WeatherUnit {
        loadIfNeeded()
        return weatherUnit
    }

    override fun setWeatherUnit(weatherUnit: WeatherUnit) {
        loadIfNeeded()
        if (this.weatherUnit == weatherUnit) {
            return
        }
        this.weatherUnit = weatherUnit
        save()
        for (listener in listeners) {
            listener.onChanged()
        }
    }

    override fun addListener(listener: WeatherUnitManager.Listener) {
        if (listeners.contains(listener)) {
            return
        }
        listeners.add(listener)
    }

    override fun removeListener(listener: WeatherUnitManager.Listener) {
        listeners.remove(listener)
    }

    private fun loadIfNeeded() {
        if (loaded) {
            return
        }
        weatherUnit = when (sharedPreferences.getString("unit", "metric")) {
            "standard" -> WeatherUnit.STANDARD
            "metric" -> WeatherUnit.METRIC
            "imperial" -> WeatherUnit.IMPERIAL
            else -> WeatherUnit.METRIC
        }
        loaded = true
    }

    private fun save() {
        sharedPreferences.edit()
            .putString(
                "unit",
                when (weatherUnit) {
                    WeatherUnit.STANDARD -> "standard"
                    WeatherUnit.METRIC -> "metric"
                    WeatherUnit.IMPERIAL -> "imperial"
                }
            ).apply()
    }

    companion object {

        const val PREFERENCE_NAME = "weather_unit"
    }
}
