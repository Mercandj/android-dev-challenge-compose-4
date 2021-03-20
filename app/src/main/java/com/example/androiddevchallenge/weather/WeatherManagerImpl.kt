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

import com.example.androiddevchallenge.weather_api.WeatherApiManager
import com.example.androiddevchallenge.weather_current_city.WeatherCurrentCityManager
import com.example.androiddevchallenge.weather_repository.WeatherRepository

class WeatherManagerImpl(
    private val weatherApiManager: WeatherApiManager,
    private val weatherCurrentCityManager: WeatherCurrentCityManager,
    private val weatherRepository: WeatherRepository,
    private val addOn: AddOn
) : WeatherManager {

    override fun load() {
    }

    interface AddOn {

        fun postWorkerThread(runnable: Runnable)

        fun postMainThread(runnable: Runnable)
    }
}
