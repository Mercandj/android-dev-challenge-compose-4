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
import com.example.androiddevchallenge.weather_current_city.WeatherCurrentCityManager
import com.example.androiddevchallenge.weather_repository.WeatherRepository

class MainViewTopBarViewPresenter(
    private val screen: MainViewTopBarViewContract.Screen,
    private val weatherCurrentCityManager: WeatherCurrentCityManager,
    private val weatherRepository: WeatherRepository
) : MainViewTopBarViewContract.UserAction {

    private var city = MutableLiveData(createCity())
    private var temperature = MutableLiveData(createTemperature())

    init {
        // Require remove call?
        weatherCurrentCityManager.addListener(object : WeatherCurrentCityManager.Listener {
            override fun onChanged() {
                city.value = createCity()
            }
        })
    }

    override fun getCity(): MutableLiveData<String> {
        return city
    }

    override fun getTemperature(): MutableLiveData<String> {
        return temperature
    }

    override fun onTemperatureClick() {
    }

    override fun onCityClicked() {
    }

    private fun createCity(): String {
        return weatherCurrentCityManager.getCity()
    }

    private fun createTemperature(): String {
        val weather = weatherRepository.getWeather() ?: return "28°"
        val temperature = weather.temperature.toInt()
        return "$temperature°"
    }
}
