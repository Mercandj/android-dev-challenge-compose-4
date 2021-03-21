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
package com.example.androiddevchallenge.error_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.theme.getTextPrimaryColor
import com.example.androiddevchallenge.theme.getTextSecondaryColor
import com.example.androiddevchallenge.theme.getTextThirdColor

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.error_view_title),
            fontSize = 28.sp,
            color = MaterialTheme.colors.getTextPrimaryColor(),
            fontWeight = FontWeight(900),
        )
        Spacer(modifier = Modifier.height(14.dp))
        Image(
            painter = painterResource(R.drawable.main_weather_animated_view_figure_7),
            contentDescription = stringResource(R.string.error_view_image_content_description),
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = stringResource(R.string.error_view_subtitle),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 22.sp,
            color = MaterialTheme.colors.getTextSecondaryColor(),
            fontWeight = FontWeight(700),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.error_view_point_1),
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.error_view_point_2),
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.error_view_point_3),
            fontSize = 16.sp,
            color = MaterialTheme.colors.getTextThirdColor(),
            fontWeight = FontWeight(600),
        )
    }
}
