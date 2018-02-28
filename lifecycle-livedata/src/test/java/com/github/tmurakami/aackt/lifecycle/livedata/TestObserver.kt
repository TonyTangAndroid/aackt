/*
 * Copyright 2018 Tsuyoshi Murakami
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package com.github.tmurakami.aackt.lifecycle.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import org.junit.Assert

class TestObserver<T> : Observer<T> {

    private val actual = ArrayList<T?>()

    override fun onChanged(t: T?) = actual.plusAssign(t)
    fun assertValues(vararg expected: T) = Assert.assertEquals(expected.toList(), actual)
}

inline fun <T> LiveData<T>.test(): TestObserver<T> = TestObserver<T>().also { observeForever(it) }
