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

import com.example.androiddevchallenge.city.CityManager
import com.example.androiddevchallenge.date.DateManager
import com.example.androiddevchallenge.weather_api.WeatherApiException
import com.example.androiddevchallenge.weather_api.WeatherApiManager
import com.example.androiddevchallenge.weather_repository.WeatherRepository
import com.example.androiddevchallenge.weather_unit.WeatherUnitManager

class WeatherManagerImpl(
    private val weatherApiManager: WeatherApiManager,
    private val cityManager: CityManager,
    private val dateManager: DateManager,
    private val weatherRepository: WeatherRepository,
    private val weatherUnitManager: WeatherUnitManager,
    private val addOn: AddOn
) : WeatherManager {

    private val listeners = ArrayList<WeatherManager.Listener>()

    override fun load() {
        val city = cityManager.getCity()
        val weatherUnit = weatherUnitManager.getWeatherUnit()
        addOn.postWorkerThread {
            val weather = try {
                weatherApiManager.getWeather(
                    city = city,
                    weatherUnit = weatherUnit
                )
            } catch (e: WeatherApiException) {
                null
            }
            val weatherForecastDaily = try {
                weatherApiManager.getWeatherForecastDaily(
                    city = city,
                    weatherUnit = weatherUnit,
                    numberOfDays = 7
                )
            } catch (e: WeatherApiException) {
                emptyList()
            }
            addOn.postMainThread {
                if (weather != null) {
                    weatherRepository.setWeather(weather)
                }
                if (weatherForecastDaily.isNotEmpty()) {
                    weatherRepository.setWeatherForecastDaily(weatherForecastDaily)
                }
                for (listener in listeners) {
                    listener.onChanged()
                }
            }
        }
    }

    override fun getWeathers(): List<Weather> {
        val weathers = ArrayList<Weather>()
        val weather = weatherRepository.getWeather() ?: return weathers
        weathers.add(weather)
        weathers.addAll(
            weatherRepository.getWeatherForecastDaily().filter {
                dateManager.convertTimestampToSpecificFormat1(it.timestampSecond) !=
                    dateManager.convertTimestampToSpecificFormat1(weather.timestampSecond)
            }
        )
        return weathers
    }

    override fun clearCache() {
        weatherRepository.setWeather(null)
        weatherRepository.setWeatherForecastDaily(emptyList())
        for (listener in listeners) {
            listener.onChanged()
        }
    }

    override fun addListener(listener: WeatherManager.Listener) {
        if (listeners.contains(listener)) {
            return
        }
        listeners.add(listener)
    }

    override fun removeListener(listener: WeatherManager.Listener) {
        listeners.remove(listener)
    }

    interface AddOn {

        fun postWorkerThread(runnable: Runnable)

        fun postMainThread(runnable: Runnable)
    }
}
