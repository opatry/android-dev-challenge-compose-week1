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
package net.opatry.adoptacat.data

import kotlinx.coroutines.delay
import net.opatry.adoptacat.model.CatModel
import net.opatry.adoptacat.model.Gender
import java.util.UUID
import kotlin.random.Random

class FakeCatDataSource : CatDataSource {
    override suspend fun loadCats(): List<CatModel> {
        delay(500)
        return if (false && Random.nextBoolean())
            emptyList()
        else mutableListOf<CatModel>().apply {
            var index = 0
            repeat(40) {
                this += CatModel(
                    uuid = UUID.randomUUID(),
                    name = "Cat ${index++}",
                    breed = "Goutière",
                    birthdate = 0L,
                    gender = if (Random.nextBoolean()) Gender.Male else Gender.Female,
                    weightInGram = 0f,
                    color = "",
                    pictureUrl = if (Random.nextBoolean()) "https://cdn2.thecatapi.com/images/322.jpg" else "https://cdn2.thecatapi.com/images/a50.jpg",
                    adopted = false
                )
            }
        }
    }
}
