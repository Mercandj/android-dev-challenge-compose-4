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
package com.example.androiddevchallenge.main_activity

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.theme.ThemeManager

class MainActivityPresenter(
    private val screen: MainActivityContract.Screen,
    private val themeManager: ThemeManager
) : MainActivityContract.UserAction {

    override fun onCreate(savedInstanceStateNull: Boolean) {
        updateScreen()
    }

    private fun updateScreen() {
        updateTheme()
    }

    private fun updateTheme() {
        val light = themeManager.isLight()
        screen.setStatusBarTheme(
            if (light) {
                R.color.main_activity_status_bar_light_theme_color
            } else {
                R.color.main_activity_status_bar_dark_theme_color
            },
            themeDark = !light
        )
        screen.setNavigationBarTheme(
            if (light) {
                R.color.main_activity_navigation_bar_light_theme_color
            } else {
                R.color.main_activity_navigation_bar_dark_theme_color
            },
            themeDark = !light
        )
    }
}
