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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import dev.chrisbanes.accompanist.coil.CoilImage
import net.opatry.adoptacat.R
import net.opatry.adoptacat.model.CatBreed
import net.opatry.adoptacat.model.CatModel
import net.opatry.adoptacat.ui.theme.typography

@Composable
fun CatDetailsScreen(cat: CatModel?, popBack: () -> Boolean) {
    // TODO error state if cat == null Or Empty state in multi column mode
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
        content = { CatDetailsContent(cat) }
    )
}

@Composable
fun CatDetailsDispatcher(cat: CatModel?) {
    if (cat == null) {
        CatDetailsNone()
    } else {
        CatDetailsContent(cat)
    }
}

@Composable
fun CatDetailsNone() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            stringResource(R.string.cat_details_no_cat_selected),
            style = typography.body1
        )
        Text(
            stringResource(R.string.cat_details_no_cat_selected_explanation),
            Modifier.padding(16.dp),
            style = typography.caption
        )
    }
}

@Composable
fun CatDetailsContent(cat: CatModel) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CatPictureView(cat.pictureUrl, compact = false)
            Text(cat.name, style = typography.h3)
            Icon(
                cat.gender.imageVector,
                stringResource(cat.gender.stringRes),
                Modifier
                    .padding(start = 12.dp)
                    .size(36.dp),
                cat.gender.color,
            )
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            BreedView(cat.breed, Modifier.padding(vertical = 8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(cat.pillColor)
                        .padding(vertical = 8.dp)
                )
                CaptionWithIcon(
                    Icons.Outlined.Event,
                    stringResource(R.string.cat_details_birthdate_desc),
                    DateUtils.getRelativeTimeSpanString(cat.birthdate, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString()
                )
                CaptionWithIcon(
                    Icons.Outlined.MonitorWeight,
                    stringResource(R.string.cat_details_weight_desc),
                    stringResource(R.string.cat_details_weight_kg, cat.weightInGram / 1000f)
                )
            }

            if (cat.description.isNotEmpty()) {
                Text(
                    cat.description,
                    Modifier.padding(vertical = 16.dp),
                    lineHeight = 1.5.em,
                    style = typography.body2,
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(top = 48.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            Text(stringResource(R.string.cat_details_adopt_me))
        }
    }
}

@Composable
fun CaptionWithIcon(imageVector: ImageVector, contentDescription: String, label: String) {
    Icon(
        imageVector,
        contentDescription,
        Modifier.padding(horizontal = 8.dp).size(16.dp),
        MaterialTheme.colors.onSurface.copy(alpha = .4f),
    )
    Text(label, style = typography.caption)
}

@Composable
fun CatPictureView(pictureUrl: String, compact: Boolean) {
    val pictureSize = if (compact) 48.dp else 128.dp
    val picturePadding = if (compact) 8.dp else 24.dp
    // TODO elevation + border if !compact
    CoilImage(
        pictureUrl,
        null,
        Modifier
            .padding(picturePadding)
            .size(pictureSize)
            .fillMaxHeight(),
        requestBuilder = {
            transformations(
                if (compact) {
                    RoundedCornersTransformation(
                        topLeft = 16.dp.value,
                        topRight = 16.dp.value,
                        bottomLeft = 16.dp.value,
                        bottomRight = 16.dp.value,
                    )
                } else {
                    CircleCropTransformation()
                }
            )
        },
        fadeIn = true,
        // FIXME needs fine tuning
        // loading = {
        //     Box(Modifier.matchParentSize()) {
        //         CircularProgressIndicator(Modifier.align(Alignment.Center))
        //     }
        // },
        // error = {
        //     Image(
        //         painterResource(R.drawable.ic_android),
        //         null,
        //         Modifier
        //             .padding(picturePadding)
        //             .size(pictureSize)
        //             .fillMaxHeight(),
        //         colorFilter = ColorFilter.tint(MaterialTheme.colors.error),
        //     )
        // }
    )
}

@Composable
fun BreedView(breed: CatBreed, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            Icons.Outlined.Pets,
            null,
            Modifier
                .padding(end = 8.dp)
                .size(16.dp)
                .rotate(45f),
            MaterialTheme.colors.secondary,
        )
        Text(stringResource(breed.stringRes), style = typography.caption)
    }
}
