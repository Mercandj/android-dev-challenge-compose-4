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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.MutableLiveData
import com.example.androiddevchallenge.graph.WeatherGraph
import com.example.androiddevchallenge.neumorphism_card_square_view.NeumorphismCardSquareView
import com.example.androiddevchallenge.theme.MainTheme
import com.example.androiddevchallenge.theme.getMainViewBottomBackgroundColor
import com.example.androiddevchallenge.theme.getMainViewTopBackgroundColor
import com.example.androiddevchallenge.theme.getMainViewTopBarViewTextColor

@Composable
fun MainViewTopBarView(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val textColor = MaterialTheme.colors.getMainViewTopBarViewTextColor()
    val userAction = Mvp(preview).createUserAction()
    val temperatureState by userAction.getTemperature().observeAsState()
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 24.dp, end = 24.dp)
                .clickable { userAction.onCityClicked() }
        ) {
            Text(
                text = "Paris, France",
                fontSize = 22.sp,
                color = textColor,
                fontWeight = FontWeight(900),
                modifier = Modifier
                    .wrapContentHeight()
            )
            Text(
                text = "Electric",
                fontSize = 22.sp,
                color = Color(0xFFFDE807),
                fontWeight = FontWeight(900),
                modifier = Modifier
                    .wrapContentHeight()
            )
        }
        NeumorphismCardSquareView(
            modifier = Modifier
                .height(160.dp)
                .width(160.dp)
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(2f)
                    .height(90.dp)
                    .width(90.dp)
                    .clickable(
                        onClick = {
                            userAction.onTemperatureClick()
                        }
                    )
            ) {

                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(2f)
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    text = temperatureState!!,
                    fontSize = 40.sp,
                    fontWeight = FontWeight(900),
                    color = textColor
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewTopBarViewLightPreview() {
    MainTheme {
        Surface {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.getMainViewTopBackgroundColor(),
                            MaterialTheme.colors.getMainViewBottomBackgroundColor()
                        )
                    )
                )
            ) {
                MainViewTopBarView(preview = true)
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainViewTopBarViewDarkPreview() {
    MainTheme(darkTheme = true) {
        Surface {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.getMainViewTopBackgroundColor(),
                            MaterialTheme.colors.getMainViewBottomBackgroundColor()
                        )
                    )
                )
            ) {
                MainViewTopBarView(preview = true)
            }
        }
    }
}

private class Mvp(
    private val preview: Boolean
) {

    private fun createScreen() = object : MainViewTopBarViewContract.Screen {
    }

    fun createUserAction(): MainViewTopBarViewContract.UserAction {
        if (preview) {
            return object : MainViewTopBarViewContract.UserAction {
                private var temperature = MutableLiveData("28°")
                override fun getTemperature(): MutableLiveData<String> = temperature
                override fun onTemperatureClick() {}
                override fun onCityClicked() {}
            }
        }
        return MainViewTopBarViewPresenter(
            createScreen(),
            WeatherGraph.getWeatherManager()
        )
    }
}
