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

import androidx.annotation.ColorRes

interface MainActivityContract {

    interface UserAction {

        fun onCreate()
    }

    interface Screen {

        fun setStatusBarTheme(@ColorRes colorRes: Int, themeDark: Boolean)

        fun setNavigationBarTheme(@ColorRes colorRes: Int, themeDark: Boolean)
    }
}
