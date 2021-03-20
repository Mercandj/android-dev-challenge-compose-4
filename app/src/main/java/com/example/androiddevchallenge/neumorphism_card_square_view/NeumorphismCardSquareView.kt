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
package com.example.androiddevchallenge.neumorphism_card_square_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.main_view.MainViewBackgroundView
import com.example.androiddevchallenge.theme.MainTheme

@Composable
fun NeumorphismCardSquareView(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(
                if (MaterialTheme.colors.isLight) {
                    R.drawable.test_light
                } else {
                    R.drawable.test_dark
                }
            ),
            contentDescription = "Weather",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .scale(
                    if (MaterialTheme.colors.isLight) {
                        1.0f
                    } else {
                        1.5f
                    }
                )
        )
        content()
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun NeumorphismCardSquareViewLightPreview() {
    MainTheme {
        MainViewBackgroundView {
            NeumorphismCardSquareView(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(160.dp)
                    .height(160.dp)
            ) {
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun NeumorphismCardSquareViewDarkPreview() {
    MainTheme(darkTheme = true) {
        MainViewBackgroundView {
            NeumorphismCardSquareView(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(160.dp)
                    .height(160.dp)
            ) {
            }
        }
    }
}
