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
package com.example.androiddevchallenge.city_edit_view

import com.example.androiddevchallenge.city.CityManager
import java.util.Locale

class CityEditViewPresenter(
    private val screen: CityEditViewContract.Screen,
    private val cityManager: CityManager
) : CityEditViewContract.UserAction {

    override fun onCityValidated(text: String) {
        cityManager.setCity(text.capitalize(Locale.getDefault()))
    }

    override fun onInfoClicked() {
    }
}
