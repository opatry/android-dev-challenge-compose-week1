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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import net.opatry.adoptacat.data.CatRepository
import net.opatry.adoptacat.model.CatModel

sealed class CatsScreenState {
    object Loading : CatsScreenState()
    data class Error(val cause: Exception) : CatsScreenState()
    object Empty : CatsScreenState()
    data class Loaded(val cats: List<CatModel>) : CatsScreenState()
}

class CatsViewModel(private val repository: CatRepository) : ViewModel() {

    val catsState = liveData {
        emit(CatsScreenState.Loading)
        val state = try {
            val cats = repository.loadCats()
            if (cats.isEmpty())
                CatsScreenState.Empty
            else
                CatsScreenState.Loaded(cats)
        } catch (e: Exception) {
            CatsScreenState.Error(e)
        }
        emit(state)
    }
}

class CatsViewModelFactory(private val repository: CatRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CatsViewModel::class.java) -> {
                CatsViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel $modelClass")
        }
    }
}
