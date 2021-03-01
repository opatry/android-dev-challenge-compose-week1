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

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch
import net.opatry.adoptacat.R
import net.opatry.adoptacat.data.CatRepository
import net.opatry.adoptacat.model.CatModel
import net.opatry.adoptacat.ui.theme.typography

val CatModel.pillColor: Color
    get() = when (color) {
        // TODO
        "" -> Color.Cyan
        else -> Color.Green
    }

@Composable
fun CatDetailsScreen(cat: CatModel?, popBack: () -> Boolean) {
    // TODO error state if cat == null
    if (cat == null) return
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = stringResource(R.string.navigation_back))
                    }
                },
                title = { Text(cat.name) },
            )
        },
        content = {
            Column {
                Text(cat.name, style = typography.h3)
                CoilImage(
                    data = cat.pictureUrl,
                    contentDescription = null,
                )
                Text(cat.breed, style = typography.caption)
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(cat.pillColor)
                )
                Text(DateUtils.formatElapsedTime(cat.birthdate), style = typography.caption)
                Text(stringResource(R.string.cat_weight_kg, cat.weightInGram), style = typography.caption)
                Text(cat.description, style = typography.body1)
            }
        }
    )
}
