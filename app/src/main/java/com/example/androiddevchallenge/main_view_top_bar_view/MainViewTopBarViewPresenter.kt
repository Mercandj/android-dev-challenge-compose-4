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

import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.city.CityManager
import com.example.androiddevchallenge.date.DateManager
import com.example.androiddevchallenge.weather.Weather
import com.example.androiddevchallenge.weather.WeatherManager
import com.example.androiddevchallenge.weather_unit.WeatherUnit
import com.example.androiddevchallenge.weather_unit.WeatherUnitManager

class MainViewTopBarViewPresenter(
    private val screen: MainViewTopBarViewContract.Screen,
    private val cityManager: CityManager,
    private val dateManager: DateManager,
    private val weatherManager: WeatherManager,
    private val weatherUnitManager: WeatherUnitManager
) : MainViewTopBarViewContract.UserAction {

    private var city = MutableLiveData(createCity())
    private var date = MutableLiveData(createDate())
    private var temperature = MutableLiveData(createTemperature())
    private var weatherType = MutableLiveData(Weather.Type.SNOW)

    init {
        // Require remove call?
        cityManager.addListener(createCityListener())
        weatherManager.addListener(createWeatherListener())
    }

    override fun getCity(): MutableLiveData<String> {
        return city
    }

    override fun getDate(): MutableLiveData<String> {
        return date
    }

    override fun getTemperature(): MutableLiveData<String> {
        return temperature
    }

    override fun getWeatherType(): MutableLiveData<Weather.Type> {
        return weatherType
    }

    override fun onTemperatureClick() {
    }

    override fun onCityClicked() {
    }

    override fun onTemperatureUnitClicked(weatherUnit: WeatherUnit) {
        weatherUnitManager.setWeatherUnit(weatherUnit)
    }

    override fun onTemperatureUnitDisplay(metric: WeatherUnit): Boolean {
        return weatherUnitManager.getWeatherUnit() == metric
    }

    private fun updateCity() {
        city.value = createCity()
    }

    private fun updateDate() {
        date.value = createDate()
    }

    private fun updateTemperature() {
        temperature.value = createTemperature()
    }

    private fun updateWeatherType() {
        weatherType.value = createWeatherType()
    }

    private fun createCity(): String {
        return cityManager.getCity()
    }

    private fun createDate(): String {
        val weather = weatherManager.getWeathers().firstOrNull() ?: return ""
        return dateManager.convertTimestampToSpecificFormat1(weather.timestampSecond)
    }

    private fun createTemperature(): String {
        val weather = weatherManager.getWeathers().firstOrNull() ?: return ""
        val temperature = weather.temperature.toInt()
        return "$temperature°"
    }

    private fun createWeatherType(): Weather.Type {
        val weather = weatherManager.getWeathers().firstOrNull() ?: return Weather.Type.SNOW
        return weather.type
    }

    private fun createCityListener() = object : CityManager.Listener {
        override fun onChanged() {
            updateCity()
        }
    }

    private fun createWeatherListener() = object : WeatherManager.Listener {
        override fun onChanged() {
            updateDate()
            updateTemperature()
            updateWeatherType()
        }

        override fun onFailed() {
        }
    }
}
