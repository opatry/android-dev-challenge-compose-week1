/*
 * Copyright (c) 2021 Olivier Patry
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.opatry.adoptacat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import net.opatry.adoptacat.data.CatRepository
import net.opatry.adoptacat.data.FakeCatDataSource
import net.opatry.adoptacat.ui.CatDetailsScreen
import net.opatry.adoptacat.ui.CatsScreen
import net.opatry.adoptacat.ui.CatsViewModel
import net.opatry.adoptacat.ui.CatsViewModelFactory
import net.opatry.adoptacat.ui.theme.AdoptACatTheme
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private val catRepository: CatRepository
        get() = (application as AdoptACatApplication).catRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdoptACatTheme {
                MainLayout(catRepository)
            }
        }
    }
}

sealed class NavRoute(val path: String) {
    object CatsList : NavRoute("cats")
    object CatDetails : NavRoute("cat.details")
}

@Composable
fun MainLayout(catRepository: CatRepository = (CatRepository((FakeCatDataSource())))) {
    val navController = rememberNavController()
    val catsViewModel = viewModel<CatsViewModel>(factory = CatsViewModelFactory(catRepository))
    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController, startDestination = NavRoute.CatsList.path) {
            composable(NavRoute.CatsList.path) {
                CatsScreen(catsViewModel,
                    onCatSelected = { cat ->
                        navController.navigate("${NavRoute.CatDetails.path}/${cat.uuid}")
                    }
                )
            }
            composable("${NavRoute.CatDetails.path}/{uuid}") { backStackEntry ->
                val uuid = UUID.fromString(backStackEntry.arguments?.get("uuid") as String)
                val cat = catsViewModel.findCatByUUID(uuid)
                CatDetailsScreen(cat) { navController.popBackStack() }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    AdoptACatTheme {
        MainLayout()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    AdoptACatTheme(darkTheme = true) {
        MainLayout()
    }
}
