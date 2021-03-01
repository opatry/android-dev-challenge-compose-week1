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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.opatry.adoptacat.R
import net.opatry.adoptacat.model.CatModel
import net.opatry.adoptacat.ui.theme.typography

@Composable
fun CatsScreen(viewModel: CatsViewModel, selectedCat: CatModel?, onCatSelected: (CatModel) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { Icon(Icons.Outlined.Pets, contentDescription = null,
                    Modifier
                        .size(48.dp)
                        .rotate(45f)
                        .padding(12.dp)) },
                title = { Text(stringResource(R.string.app_name)) },
            )
        },
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

@Composable
fun LoadingCatsContent() {
    Box(Modifier.fillMaxWidth().fillMaxHeight(.6f),
        contentAlignment = Alignment.Center) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
fun ErrorCatsContent(cause: Exception) {
    Box(Modifier
            .fillMaxWidth()
            .fillMaxHeight(.6f)
            .padding(8.dp)
        ,
        contentAlignment = Alignment.Center) {
        Text(
            stringResource(R.string.cats_list_error, cause.message ?: ""),
            color = MaterialTheme.colors.error,
            style = typography.body1,
        )
    }
}

@Composable
fun EmptyCatsContent() {
    Box(Modifier.fillMaxWidth().fillMaxHeight(.6f),
        contentAlignment = Alignment.Center) {
        Text(
            stringResource(R.string.cats_list_empty),
            style = typography.body1,
        )
    }
}

@Composable
fun LoadedCatsContent(cats: List<CatModel>, selectedCat: CatModel?, onCatSelected: (CatModel) -> Unit) {
    LazyColumn {
        items(cats) { cat ->
            CatView(cat, selectedCat == cat, onCatSelected)
        }
    }
}

@Composable
fun CatView(cat: CatModel, isSelected: Boolean, onCatSelected: (CatModel) -> Unit) {
    Row(modifier = Modifier
        .clickable { onCatSelected(cat) }
        .background(if (isSelected) MaterialTheme.colors.secondary.copy(alpha = .2f) else Color.Transparent)
        .padding(8.dp)
    ) {
        CatPictureView(cat.pictureUrl, compact = true)
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(cat.name, style = typography.h5)
                Icon(
                    cat.gender.imageVector,
                    stringResource(cat.gender.stringRes),
                    Modifier
                        .padding(start = 8.dp)
                        .size(24.dp)
                    ,
                    cat.gender.color,
                )
            }
            BreedView(cat.breed)
        }
    }

    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
    )
}
