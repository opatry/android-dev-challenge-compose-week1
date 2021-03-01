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

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.Male
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import net.opatry.adoptacat.R
import net.opatry.adoptacat.model.CatBreed
import net.opatry.adoptacat.model.CatModel
import net.opatry.adoptacat.model.Gender
import net.opatry.adoptacat.ui.theme.typography

@Composable
fun CatsScreen(viewModel: CatsViewModel, onCatSelected: (CatModel) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { Icon(Icons.Outlined.Pets, contentDescription = null,
                    Modifier
                        .size(48.dp)
                        .padding(12.dp)) },
                title = { Text(stringResource(R.string.app_name)) },
            )
        },
        content = {
            val state by viewModel.catsState.observeAsState(CatsScreenState.Loading)
            CatsStateDispatcher(uiState = state, onCatSelected)
        }
    )
}

@Composable
fun CatsStateDispatcher(uiState: CatsScreenState, onCatSelected: (CatModel) -> Unit) {
    when (uiState) {
        CatsScreenState.Loading -> LoadingCatsContent()
        is CatsScreenState.Error -> ErrorCatsContent(uiState.cause)
        CatsScreenState.Empty -> EmptyCatsContent()
        is CatsScreenState.Loaded -> LoadedCatsContent(uiState.cats, onCatSelected)
    }
}

@Composable
fun LoadingCatsContent() {
    Box {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
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
fun LoadedCatsContent(cats: List<CatModel>, onCatSelected: (CatModel) -> Unit) {
    LazyColumn(modifier = Modifier
        // .background(Color.Blue)
    ) {
        items(cats) { cat ->
            CatCard(cat, onCatSelected)
        }
    }
}

val CatModel.cardColor: Color
    get() = when (breed) {
        // TODO
        "" -> Color.Cyan
        else -> Color.Green
    }

fun Gender.imageVector() = when(this) {
    Gender.Male -> Icons.Outlined.Male
    Gender.Female -> Icons.Outlined.Female
}

fun Gender.color() = when(this) {
    Gender.Male -> Color(0xFFB8F5FD)
    Gender.Female -> Color(0xFFE3BAFF)
}

@Composable
fun CatCard(cat: CatModel, onCatSelected: (CatModel) -> Unit) {
    val pictureSize = 48.dp
    Card(modifier = Modifier
        .padding(16.dp)
        .clickable(onClick = { onCatSelected(cat) })
        // .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                // .background(cat.cardColor)
                // .padding(16.dp)
                // .fillMaxWidth()
        ) {
            CoilImage(
                data = cat.pictureUrl,
                contentDescription = null,
                modifier = Modifier
                    // .padding(8.dp)
                    // .size(pictureSize)
                    .fillMaxHeight()
                ,
                // requestBuilder = {
                //     transformations(CircleCropTransformation())
                // },
                fadeIn = true,
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                error = {
                    Image(
                        painter = painterResource(R.drawable.ic_android),
                        contentDescription = null,
                        modifier = Modifier
                            // .size(pictureSize)
                            .fillMaxHeight()
                        ,
                    )
                }
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ) {
                Row {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            // .alignByBaseline()
                        ,
                        imageVector = Icons.Outlined.Pets,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground,
                    )
                    Text(cat.name, style = typography.h5, modifier = Modifier/*.alignByBaseline()*/)
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            // .alignByBaseline()
                        ,
                        imageVector = cat.gender.imageVector(),
                        contentDescription = null,
                        tint = cat.gender.color(),
                    )
                }
                Text(stringResource(cat.breed.stringRes), style = typography.caption)
            }
        }
    }
}
