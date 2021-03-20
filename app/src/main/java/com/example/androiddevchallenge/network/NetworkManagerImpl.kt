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
package com.example.androiddevchallenge.network

import androidx.annotation.WorkerThread
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.Closeable
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetworkManagerImpl : NetworkManager {

    @WorkerThread
    override fun get(url: String): String? {
        val okHttpRequestBuilder = okhttp3.Request.Builder()
            .url(url)
            .get()
        var okHttpResponse: Response? = null
        var body: ResponseBody? = null
        return try {
            okHttpResponse = okHttpClient.value.newCall(okHttpRequestBuilder.build()).execute()
            body = okHttpResponse.body()
            body.toString()
        } catch (e: IOException) {
            null
        } finally {
            closeSilently(body, okHttpResponse)
        }
    }

    companion object {

        private val okHttpClient = lazy {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(15, TimeUnit.SECONDS)
            builder.build()
        }

        private fun closeSilently(vararg xs: Closeable?) {
            for (x in xs) {
                try {
                    x?.close()
                } catch (ignored: Throwable) {
                }
            }
        }
    }
}
