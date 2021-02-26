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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.opatry.adoptacat.data.CatRepository
import net.opatry.adoptacat.data.FakeCatDataSource
import net.opatry.adoptacat.ui.CatsScreen
import net.opatry.adoptacat.ui.CatsViewModel
import net.opatry.adoptacat.ui.CatsViewModelFactory
import net.opatry.adoptacat.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    private val catRepository: CatRepository
        get() = (application as AdoptACatApplication).catRepository

    private val catsViewModel by viewModels<CatsViewModel> {
        CatsViewModelFactory(catRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                AdoptACatApp(catsViewModel)
            }
        }
    }
}

// Start building your app here!
@Composable
fun AdoptACatApp(catsViewModel: CatsViewModel = CatsViewModel(CatRepository((FakeCatDataSource())))) {
    Surface(color = MaterialTheme.colors.background) {
        CatsScreen(catsViewModel)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        AdoptACatApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        AdoptACatApp()
    }
}
