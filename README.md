# Template repository

Template repository for the Jetpack Compose [#AndroidDevChallenge](https://developer.android.com/dev-challenge).

## Mercandj's notes

This is the first time I'm trying Jetpack Compose.
Even if the spirit seems to be reactive paradigm, I will try to use it on a more "classic" way.

**Why?**
- Because, if one day I'm using this techno in production, I will need this "transition" to not move all my code to "react"
- Because stay "non reactive" is easier for the reactive-beginner that I am
- Because stay "non reactive" allow you to produce almost the same code on any platform easily (Unity, iOS, server...)

**My goals will be:**
- Discover Jetpack Compose
- Split as much as possible logic from the UI with the good old "MVP"
- Great UI / Great app quality (app we can publish on Google Play)

**Requirement if you a coming from "stable Android Studio" on MacOs**
- Download Java 11 here https://jdk.java.net/archive/
- Add it like that: `/Users/jonathan/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk`
- If cannot open the jdk: https://superuser.com/a/1537706

## Getting started
Copy this repository by pressing the "Use this template" button in Github.
Clone your repository and open it in the latest [Android Studio (Canary build)](https://developer.android.com/studio/preview).

## Submission requirements
- Follow the challenge description on the project website: [developer.android.com/dev-challenge](https://developer.android.com/dev-challenge)
- All UI should be written using Jetpack Compose
- The Github Actions workflow should complete successfully
- Include two screenshots of your submission in the [results](results) folder. The names should be
  screenshot_1.png and screenshot_2.png.
- Include a screen record of your submission in the [results](results) folder. The name should be
  video.mp4
- Replace the contents of [README.md](README.md) with the contents of [README-template.md](README-template.md) and fill out the template.

## Code formatting
The CI uses [Spotless](https://github.com/diffplug/spotless) to check if your code is formatted correctly and contains the right licenses.
Internally, Spotless uses [ktlint](https://github.com/pinterest/ktlint) to check the formatting of your code.
To set up ktlint correctly with Android Studio, follow one of the [listed setup options](https://github.com/pinterest/ktlint#-with-intellij-idea).

Before committing your code, run `./gradlew app:spotlessApply` to automatically format your code.

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```