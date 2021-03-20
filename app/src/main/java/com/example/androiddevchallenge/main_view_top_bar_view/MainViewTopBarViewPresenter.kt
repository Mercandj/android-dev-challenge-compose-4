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
import com.example.androiddevchallenge.weather.WeatherManager

class MainViewTopBarViewPresenter(
    private val screen: MainViewTopBarViewContract.Screen,
    private val weatherManager: WeatherManager
) : MainViewTopBarViewContract.UserAction {

    private var temperature = MutableLiveData(createTemperature())

    init {
        // Require remove call?
        weatherManager.addListener(object : WeatherManager.Listener {
            override fun onChanged() {
                temperature.value = createTemperature()
            }
        })
    }

    override fun getTemperature(): MutableLiveData<String> {
        return temperature
    }

    override fun onTemperatureClick() {
        weatherManager.increase()
    }

    private fun createTemperature(): String {
        return "${weatherManager.getTemperature()}°C"
    }
}
