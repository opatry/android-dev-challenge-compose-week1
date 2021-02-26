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
package net.opatry.adoptacat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import net.opatry.adoptacat.R
import net.opatry.adoptacat.model.CatModel

@Composable
fun CatsScreen(viewModel: CatsViewModel) {
    // Scaffold (
    //     drawerContent = { /*...*/ },
    //     topBar = { /*...*/ },
    //     bodyContent = { /*...*/ }
    // )

    val state: State<CatsScreenState?> = viewModel.catsState.observeAsState(CatsScreenState.Loading)
    when (val uiState = state.value) {
        CatsScreenState.Loading -> LoadingCatsContent()
        is CatsScreenState.Error -> ErrorCatsContent(uiState.cause)
        CatsScreenState.Empty -> EmptyCatsContent()
        is CatsScreenState.Loaded -> LoadedCatsContent(uiState.cats)
    }
}

@Composable
fun LoadingCatsContent() {
    // FIXME why not Box? What is the simplest way to center a single item in parent layout
    CircularProgressIndicator()
}

@Composable
fun ErrorCatsContent(cause: Exception) {
    Text(stringResource(R.string.cats_list_error, cause.message ?: "?"))
}

@Composable
fun EmptyCatsContent() {
    Text(stringResource(R.string.cats_list_empty))
}

@Composable
fun LoadedCatsContent(cats: List<CatModel>) {
    Column {
        cats.forEach { cat ->
            Text("A CAT ${cat.name}")
        }
    }
}
