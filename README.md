# 🐱 Kitty adoption app - Week #1

[![Workflow result](https://github.com/opatry/android-dev-challenge-compose-week1/workflows/Check/badge.svg)](https://github.com/opatry/android-dev-challenge-compose-week1/actions/workflows/Check.yaml)

## :scroll: Description

This is a simple master/detail app with a fake list of clickable data displaying more detailed information about it.

## :bulb: Motivation and Context

My goal was mainly to see how Jetpack Compose handles Navigation and Multiple form factor/layouts.

I'm proud of my work because I started late (was in holiday when challenge was first announced) and didn't spent a lot of time on this.  
Still, I managed to submit a working version on time implementing Jetpack navigation and dedicated support of tablet.

---

I used MVVM, Unidirectional Data Flow and UI State pattern.  
Each state has its own composable, see `CatsScreen.kt`.

<details>
<summary>Show me the code!</summary>

```kotlin
@Composable
fun CatsScreen(viewModel: CatsViewModel, selectedCat: CatModel?, onCatSelected: (CatModel) -> Unit) {
    Scaffold(
        topBar = { /* */ },
        content = {
            val state by viewModel.catsState.observeAsState(CatsScreenState.Loading)
            CatsStateDispatcher(uiState = state, selectedCat, onCatSelected)
        }
    )
}

@Composable
fun CatsStateDispatcher(uiState: CatsScreenState, selectedCat: CatModel?, onCatSelected: (CatModel) -> Unit) {
    when (uiState) {
        CatsScreenState.Loading -> LoadingCatsContent()
        is CatsScreenState.Error -> ErrorCatsContent(uiState.cause)
        CatsScreenState.Empty -> EmptyCatsContent()
        is CatsScreenState.Loaded -> LoadedCatsContent(uiState.cats, selectedCat, onCatSelected)
    }
}
```

</details>

---

I implemented a `MainLayout` composable to choose how to present the UI depending on device configuration, see `MainActivity.kt/MainLayout`.

<details>
<summary>Show me the code!</summary>

```kotlin
sealed class NavRoute(val path: String) {
    object CatsList : NavRoute("cats")
    object CatDetails : NavRoute("cat.details")
}

@Composable
fun MainLayout(catRepository: CatRepository = (CatRepository((FakeCatDataSource())))) {
    val catsViewModel = viewModel<CatsViewModel>(factory = CatsViewModelFactory(catRepository))
    Surface(color = MaterialTheme.colors.background) {
        if (booleanResource(R.bool.is_tablet)) {
            var selectedCatUUID by rememberSaveable { mutableStateOf<UUID?>(null) }
            val selectedCat = selectedCatUUID?.let { uuid ->
                catsViewModel.findCatByUUID(uuid)
            }
            if (booleanResource(R.bool.is_portrait)) {
                MainLayoutTabletPortrait(catsViewModel, selectedCat) { cat ->
                    selectedCatUUID = cat.uuid
                }
            } else {
                MainLayoutTabletLandscape(catsViewModel, selectedCat) { cat ->
                    selectedCatUUID = cat.uuid
                }
            }
        } else {
            val navController = rememberNavController()
            NavHost(navController, startDestination = NavRoute.CatsList.path) {
                composable(NavRoute.CatsList.path) {
                    CatsScreen(catsViewModel, null) { cat ->
                        navController.navigate("${NavRoute.CatDetails.path}/${cat.uuid}")
                    }
                }
                composable("${NavRoute.CatDetails.path}/{uuid}") { backStackEntry ->
                    val uuid = UUID.fromString(backStackEntry.arguments?.get("uuid") as String)
                    val cat = catsViewModel.findCatByUUID(uuid)
                    CatDetailsScreen(cat) { navController.popBackStack() }
                }
            }
        }
    }
}
```
</details>

I didn't see guidelines regarding how to handle tablet and side-by-side layouts, maybe there is something better 🤷‍♂️.

## :camera_flash: Screenshots

## 🌞 Light Mode
List | Details | Tablet
--- | --- | --- |
<img src="/results/screenshot_1.png" width="260"> | <img src="/results/screenshot_2.png" width="260"> | <img src="/results/screenshot_3.png" width="520">

<br />

## 🌚 Dark Mode
List | Details | Tablet
--- | --- | --- |
<img src="/results/screenshot_1_dark.png" width="260"> | <img src="/results/screenshot_2_dark.png" width="260"> | <img src="/results/screenshot_3_dark.png" width="520">

<br />


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
