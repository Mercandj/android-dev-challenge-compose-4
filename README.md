# Weather

<!--- Replace <OWNER> with your Github Username and <REPOSITORY> with the name of your repository. -->
<!--- You can find both of these in the url bar when you open your repository in github. -->
![Workflow result](https://github.com/Mercandj/android-dev-challenge-compose-4/workflows/Check/badge.svg)

## :scroll: Description
<!--- Describe your app in one or two sentences -->

* Neumorphism simple weather app that support different temperature units and cities.
* Based on [Jetpack Compose](https://developer.android.com/jetpack/compose) and [openweathermap.org](https://openweathermap.org/), built for the [Jetpack Compose challenge](https://developer.android.com/dev-challenge) week 4.

## :bulb: Motivation and Context
<!--- Optionally point readers to interesting parts of your submission. -->
<!--- What are you especially proud of? -->

<img
    src="app/src/main/res/icon/mipmap-xxxhdpi/ic_launcher.png"
    align="left"
    width="190"
    hspace="10"
    vspace="10" />

**What am I proud of?**
- Split UI and logic. Move logic into Presenters / Managers
- Having built a "useful" app and not only a UI proto
   - Real data fetch + persistence
   - Change the City
   - Change temperature Unit
- The neumorphism UI

## :camera_flash: Screenshots
<!-- You can add more screenshots here if you like -->
<img src="/results/screenshot_1.png" width="260">&emsp;<img src="/results/screenshot_2.png" width="260">
&emsp;<img width="260" src="/results/vide_as_gif.gif"/>
&emsp;<img src="/results/screenshot_3.png" width="260">
&emsp;<img src="/results/screenshot_4.png" width="260">
&emsp;<img src="/results/screenshot_5.png" width="260">

## Side notes

This is the first time I'm trying Jetpack Compose.
Even if the spirit seems to be reactive paradigm, I will try to use it on a more "classic" way.

**Why?**
- Because, if one day I will use this techno in production, I will need a "transition" phase to not move all my code to the "reactive" way.
- Because stay "non reactive" is easier for the reactive-beginner that I am. I hope that my project demonstrates that Jatpack Compose work well without intensive use of ViewModel, LiveData...
- Because stay "non reactive" allow you to produce almost the same code on any platform easily (Unity, iOS, server...)

**My goals will be:**
- Discover Jetpack Compose
- Split as much as possible logic from the UI with the good old "MVP"
- Great UI / Great app quality (app we can publish on Google Play)

**Requirement if you a coming from "stable Android Studio" on MacOs**
- Download Java 11 (for example [here](https://jdk.java.net/archive/))
- Add it like that: `/Users/jonathan/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk`
- If cannot open the jdk due to mac os permission, [here](https://superuser.com/a/1537706) a tip)

**Thank you to Figma and this links:**
- [Edwin.P that introduce me to Figma](https://www.figma.com/file/K940hjRsZGY6LrOdvJeV28/Untitled?node-id=201%3A2)
- Designers on figma that provide images [here](https://www.figma.com/file/kCYEnx8j7LCxOiBcr2sjFM/Weatherly-3D-Icons-Demo-version-Community?node-id=0%3A10), [here](https://www.figma.com/community/file/890095002328610853) and [here](https://www.figma.com/file/LfxPlArXOlJ74YNfQwpz8s/SALY---3D-Illustration-Pack-(Community)?node-id=0%3A1)
- The neumorphism library [here](https://github.com/fornewid/neumorphism)

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