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

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androiddevchallenge.graph.WeatherGraph
import com.example.androiddevchallenge.main_view.MainView
import com.example.androiddevchallenge.theme.MainTheme

class MainActivity : AppCompatActivity() {

    private val userAction by lazy { createUserAction() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                MainView()
            }
        }
        userAction.onCreate(savedInstanceState == null)
    }

    private fun createScreen() = object : MainActivityContract.Screen {

        @Suppress("DEPRECATION")
        override fun setStatusBarTheme(@ColorRes colorRes: Int, themeDark: Boolean) {
            val color = ContextCompat.getColor(this@MainActivity, colorRes)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController!!.setSystemBarsAppearance(
                    if (themeDark) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
                window.statusBarColor = color
            } else {
                window.statusBarColor = color
                val flags = window.decorView.systemUiVisibility
                window.decorView.systemUiVisibility = if (themeDark) {
                    flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                } else {
                    flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
        }

        override fun setNavigationBarTheme(@ColorRes colorRes: Int, themeDark: Boolean) {
            val color = ContextCompat.getColor(this@MainActivity, colorRes)
            window.navigationBarColor = if (color == Color.WHITE) {
                // Not supported nicely as buttons will stay white, so use black color instead.
                Color.BLACK
            } else {
                color
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController!!.setSystemBarsAppearance(
                    if (themeDark) 0 else WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                setNavigationBarButtonColor(window, !themeDark)
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private fun setNavigationBarButtonColor(window: Window, darkButton: Boolean) {
            val flags = window.decorView.systemUiVisibility
            window.decorView.systemUiVisibility = if (darkButton) {
                flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
        }
    }

    private fun createUserAction(): MainActivityContract.UserAction {
        return MainActivityPresenter(
            createScreen(),
            WeatherGraph.getThemeManager()
        )
    }
}
