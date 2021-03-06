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

import android.animation.AnimatorInflater
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.main_view_background_view.MainViewBackgroundView
import com.example.androiddevchallenge.theme.MainTheme
import soup.neumorphism.NeumorphImageButton
import soup.neumorphism.ShapeType

@Composable
fun NeumorphismCardSquareView(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    @ShapeType shapeType: Int = ShapeType.DEFAULT,
    backgroundColor: Color = Color.Transparent,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {
        val light = MaterialTheme.colors.isLight
        val topStartShadowColor = if (light) "#C6CEDA" else "#101010"
        val bottomEndShadowColor = if (light) "#FEFEFF" else "#262C37"
        AndroidView(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            factory = { context ->
                NeumorphImageButton(context).apply {
                    setShadowColorLight(android.graphics.Color.parseColor(bottomEndShadowColor))
                    setShadowColorDark(android.graphics.Color.parseColor(topStartShadowColor))
                    setShapeType(shapeType)
                    setBackgroundColor(
                        argb(
                            backgroundColor.alpha,
                            backgroundColor.red,
                            backgroundColor.green,
                            backgroundColor.blue
                        )
                    )
                    stateListAnimator = AnimatorInflater.loadStateListAnimator(
                        context,
                        R.animator.button_state_list_anim_neumorph
                    )
                    if (onClick != null) {
                        setOnClickListener {
                            onClick()
                        }
                    }
                }
            }
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

private fun argb(alpha: Float, red: Float, green: Float, blue: Float): Int {
    return (alpha * 255.0f + 0.5f).toInt() shl 24 or
        ((red * 255.0f + 0.5f).toInt() shl 16) or
        ((green * 255.0f + 0.5f).toInt() shl 8) or
        (blue * 255.0f + 0.5f).toInt()
}
