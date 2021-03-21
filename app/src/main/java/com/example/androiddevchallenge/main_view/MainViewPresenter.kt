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
package com.example.androiddevchallenge.main_view

import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.weather.Weather
import com.example.androiddevchallenge.weather.WeatherManager

class MainViewPresenter(
    private val screen: MainViewContract.Screen,
    private val weatherManager: WeatherManager
) : MainViewContract.UserAction {

    private var weathers = MutableLiveData(createWeathers())
    private var error = MutableLiveData(createError())

    init {
        // Leak?
        weatherManager.addListener(createWeatherListener())
    }

    override fun getWeathers(): MutableLiveData<List<Weather>> {
        return weathers
    }

    override fun getError(): MutableLiveData<Boolean> {
        return error
    }

    private fun updateWeathers() {
        weathers.value = createWeathers()
    }

    private fun updateError(){
        error.value = createError()
    }

    private fun createWeathers(): List<Weather> {
        return weatherManager.getWeathers()
    }

    private fun createError(): Boolean{
        return weatherManager.getWeathers().size < 4
    }

    private fun createWeatherListener() = object : WeatherManager.Listener {
        override fun onChanged() {
            updateWeathers()
            updateError()
        }

        override fun onFailed() {
            updateError()
        }
    }
}
