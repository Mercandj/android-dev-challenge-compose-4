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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.main_view.MainViewBackgroundView
import com.example.androiddevchallenge.theme.MainTheme
import com.example.androiddevchallenge.theme.getTextHintColor
import com.example.androiddevchallenge.theme.getTextPrimaryColor
import soup.neumorphism.NeumorphImageButton
import soup.neumorphism.ShapeType

@Composable
fun CityEditView(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    val height = 90.dp
    Box(
        Modifier.padding(start = 12.dp, end = 12.dp)
    ) {
        val light = MaterialTheme.colors.isLight
        val topStartShadowColor = if (light) "#C6CEDA" else "#2C3341"
        val bottomEndShadowColor = if (light) "#FEFEFF" else "#404B5F"
        AndroidView(
            modifier = modifier
                .fillMaxWidth()
                .height(height),
            factory = { context ->
                NeumorphImageButton(context).apply {
                    setShadowColorLight(android.graphics.Color.parseColor(bottomEndShadowColor))
                    setShadowColorDark(android.graphics.Color.parseColor(topStartShadowColor))
                    setShapeType(ShapeType.PRESSED)
                }
            },
            update = { view ->
                view.invalidate()
            }
        )
        var text by remember { mutableStateOf(TextFieldValue("")) }
        var hintVisible by remember { mutableStateOf(true) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    hintVisible = it.text.isEmpty()
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.city_edit_view_loop),
                        contentDescription = "search"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                ),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.getTextPrimaryColor(),
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(start = 24.dp, end = 24.dp)
            )
            if (hintVisible) {
                Text(
                    text = "City",
                    color = MaterialTheme.colors.getTextHintColor(),
                    fontWeight = FontWeight(900),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 74.dp, end = 74.dp)
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun CityEditViewLightPreview() {
    MainTheme {
        Surface {
            MainViewBackgroundView {
                CityEditView(preview = true)
            }
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun CityEditViewDarkPreview() {
    MainTheme(darkTheme = true) {
        Surface {
            MainViewBackgroundView {
                CityEditView(preview = true)
            }
        }
    }
}
