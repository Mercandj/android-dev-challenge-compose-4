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

import com.example.androiddevchallenge.network.NetworkManager
import com.example.androiddevchallenge.weather.Weather
import com.example.androiddevchallenge.weather_unit.WeatherUnit
import org.json.JSONException
import org.json.JSONObject

class WeatherApiManagerImpl(
    private val networkManager: NetworkManager
) : WeatherApiManager {

    override fun getWeather(city: String, weatherUnit: WeatherUnit): Weather {
        val response = try {
            networkManager.get(
                url = createWeatherUrl(city, weatherUnit)
            )
        } catch (e: IllegalStateException) {
            throw WeatherApiException("Fail to http GET", e)
        }
        val body = response ?: throw WeatherApiException("Empty body. $response")
        val bodyJson = try {
            JSONObject(body)
        } catch (e: JSONException) {
            throw WeatherApiException("Body not a json. $body", e)
        }
        if (bodyJson.has("cod") &&
            bodyJson.getInt("cod") == 404 &&
            bodyJson.has("message") &&
            bodyJson.getString("message") == "city not found"
        ) {
            throw WeatherApiException("city not found")
        }
        val weatherJson = bodyJson.getJSONArray("weather").getJSONObject(0)
        return Weather(
            city = city,
            weatherUnit = weatherUnit,
            type = createWeatherType(
                main = weatherJson.getString("main"),
                description = weatherJson.getString("description")
            ),
            temperature = bodyJson.getJSONObject("main").getDouble("temp").toFloat(),
            humidity = bodyJson.getJSONObject("main").getInt("humidity"),
            pressure = bodyJson.getJSONObject("main").getInt("pressure"),
            offsetDayFromToday = 0
        )
    }

    override fun getWeatherForecastDaily(
        city: String,
        weatherUnit: WeatherUnit,
        numberOfDays: Int
    ): List<Weather> {
        val response = try {
            networkManager.get(
                url = getWeatherForecastDailyUrl(city, weatherUnit, numberOfDays)
            )
        } catch (e: IllegalStateException) {
            throw WeatherApiException("Fail to http GET", e)
        }
        val body = response ?: throw WeatherApiException("Empty body. $response")
        val bodyJson = try {
            JSONObject(body)
        } catch (e: JSONException) {
            throw WeatherApiException("Body not a json. $body", e)
        }
        if (bodyJson.has("cod") &&
            bodyJson.getInt("cod") == 404 &&
            bodyJson.has("message") &&
            bodyJson.getString("message") == "city not found"
        ) {
            throw WeatherApiException("city not found")
        }
        val weathers = ArrayList<Weather>()
        if (!bodyJson.has("list")) {
            throw WeatherApiException("Does not contains list: $body")
        }
        val arrayList = bodyJson.getJSONArray("list")
        for (i in 0 until arrayList.length()) {
            val json = arrayList.getJSONObject(i)
            val weatherJson = json.getJSONArray("weather").getJSONObject(0)
            val weather = Weather(
                city = city,
                weatherUnit = weatherUnit,
                type = createWeatherType(
                    main = weatherJson.getString("main"),
                    description = weatherJson.getString("description")
                ),
                temperature = json.getJSONObject("temp").getDouble("day").toFloat(),
                humidity = json.getInt("humidity"),
                pressure = json.getInt("pressure"),
                offsetDayFromToday = i + 1
            )
            weathers.add(weather)
        }
        return weathers
    }

    // https://openweathermap.org/weather-conditions
    private fun createWeatherType(main: String, description: String): Weather.Type {
        return when (main) {
            "Clear" -> Weather.Type.CLEAR
            "Thunderstorm" -> Weather.Type.THUNDERSTORM
            "Drizzle" -> Weather.Type.DRIZZLE
            "Clouds" -> {
                when (description) {
                    "scattered clouds" -> Weather.Type.CLOUDS_SCATTERED_CLOUDS
                    "broken clouds" -> Weather.Type.CLOUDS_BROKEN_CLOUDS
                    "few clouds" -> Weather.Type.CLOUDS_FEW_CLOUDS
                    else -> Weather.Type.CLOUDS
                }
            }
            "Rain" -> {
                when (description) {
                    "freezing rain" -> Weather.Type.RAIN_FREEZING_RAIN
                    "very heavy rain" -> Weather.Type.RAIN_VERY_HEAVY_RAIN
                    "heavy intensity rain" -> Weather.Type.RAIN_HEAVY_INTENSITY
                    "moderate rain" -> Weather.Type.RAIN_MODERATE_RAIN
                    "light rain" -> Weather.Type.RAIN_LIGHT_RAIN
                    else -> Weather.Type.RAIN
                }
            }
            "Snow" -> {
                when (description) {
                    "light snow" -> Weather.Type.SNOW_LIGHT_SNOW
                    else -> Weather.Type.SNOW
                }
            }
            "Mist" -> Weather.Type.MIST
            else -> throw WeatherApiException("Unknown weather. $main | $description")
        }
    }

    private fun createWeatherUrl(city: String, weatherUnit: WeatherUnit): String {
        val unit = when (weatherUnit) {
            WeatherUnit.STANDARD -> "standard"
            WeatherUnit.METRIC -> "metric"
            WeatherUnit.IMPERIAL -> "imperial"
        }
        return "$DOMAIN/weather?appid=$A_T&q=$city&units=$unit"
    }

    private fun getWeatherForecastDailyUrl(
        city: String,
        weatherUnit: WeatherUnit,
        numberOfDays: Int
    ): String {
        val unit = when (weatherUnit) {
            WeatherUnit.STANDARD -> "standard"
            WeatherUnit.METRIC -> "metric"
            WeatherUnit.IMPERIAL -> "imperial"
        }
        return "$DOMAIN/forecast/daily?appid=$A_T&q=$city&units=$unit&cnt=$numberOfDays"
    }

    companion object {

        private const val A_T = "886705b4c1182eb1c69f28eb8c520e20"
        private const val DOMAIN = "http://api.openweathermap.org/data/2.5"
    }
}
