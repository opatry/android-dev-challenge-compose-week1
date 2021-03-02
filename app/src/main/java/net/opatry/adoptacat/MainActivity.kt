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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import net.opatry.adoptacat.data.CatRepository
import net.opatry.adoptacat.data.FakeCatDataSource
import net.opatry.adoptacat.model.CatModel
import net.opatry.adoptacat.ui.CatDetailsDispatcher
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
            MainLayoutTabletPhone(catsViewModel)
        }
    }
}

@Composable
fun MainLayoutTabletPortrait(catsViewModel: CatsViewModel, selectedCat: CatModel?, onCatSelected: (CatModel) -> Unit) {
    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
        ) {
            CatsScreen(catsViewModel, selectedCat) { onCatSelected(it) }
        }

        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
        )

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
        ) {
            CatDetailsDispatcher(selectedCat)
        }
    }
}

@Composable
fun MainLayoutTabletLandscape(catsViewModel: CatsViewModel, selectedCat: CatModel?, onCatSelected: (CatModel) -> Unit) {
    Row {
        Box(
            Modifier
                .width(dimensionResource(R.dimen.cat_list_width_side_by_side))
                .fillMaxHeight()
        ) {
            CatsScreen(catsViewModel, selectedCat) { onCatSelected(it) }
        }

        Divider(
            Modifier
                .width(1.dp)
                .fillMaxHeight()
        )

        Box(Modifier.fillMaxHeight()) {
            CatDetailsDispatcher(selectedCat)
        }
    }
}

@Composable
fun MainLayoutTabletPhone(catsViewModel: CatsViewModel) {
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
