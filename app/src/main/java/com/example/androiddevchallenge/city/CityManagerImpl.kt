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
package com.example.androiddevchallenge.city

import android.content.SharedPreferences

class CityManagerImpl(
    private val sharedPreferences: SharedPreferences
) : CityManager {

    private val listeners = ArrayList<CityManager.Listener>()
    private var city = "Paris, France"
    private var loaded = false

    override fun getCity(): String {
        loadIfNeeded()
        return city
    }

    override fun setCity(city: String) {
        loadIfNeeded()
        if (this.city == city) {
            return
        }
        this.city = city
        save()
        for (listener in listeners) {
            listener.onChanged()
        }
    }

    override fun addListener(listener: CityManager.Listener) {
        if (listeners.contains(listener)) {
            return
        }
        listeners.add(listener)
    }

    override fun removeListener(listener: CityManager.Listener) {
        listeners.remove(listener)
    }

    private fun loadIfNeeded() {
        if (loaded) {
            return
        }
        city = sharedPreferences.getString("city", city) ?: "city"
        loaded = true
    }

    private fun save() {
        sharedPreferences.edit().putString("city", city).apply()
    }

    companion object {

        const val PREFERENCE_NAME = "weather_current_city"
    }
}
