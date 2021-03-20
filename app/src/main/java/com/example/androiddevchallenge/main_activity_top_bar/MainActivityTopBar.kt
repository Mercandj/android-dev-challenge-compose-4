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
package com.example.androiddevchallenge.main_activity_top_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.theme.MyTheme
import com.example.androiddevchallenge.theme.getMainActivityTopBarViewTextColor

@Composable
fun MainActivityTopBar(
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colors.getMainActivityTopBarViewTextColor()
    Row(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .height(50.dp)
                .weight(1f)
        ) {
            Text(
                text = "Paris, France",
                fontSize = 20.sp,
                color = textColor,
                modifier = Modifier
                    .height(25.dp)
            )
            Text(
                text = "Electric",
                fontSize = 20.sp,
                color = textColor,
                modifier = Modifier
                    .height(25.dp)
            )
        }
        Text(
            modifier = Modifier
                .wrapContentWidth(),
            text = "28°",
            fontSize = 42.sp,
            color = textColor
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainActivityTopBarViewLightPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainActivityTopBar()
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainActivityTopBarViewDarkPreview() {
    MyTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            MainActivityTopBar()
        }
    }
}
