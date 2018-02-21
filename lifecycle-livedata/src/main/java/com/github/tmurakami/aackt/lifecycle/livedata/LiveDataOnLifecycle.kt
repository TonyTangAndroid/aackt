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

package com.github.tmurakami.aackt.lifecycle.livedata

import android.arch.lifecycle.MediatorLiveData
import java.util.LinkedList

internal typealias LifecycleListener = () -> Unit

internal class LiveDataOnLifecycle<T> : MediatorLiveData<T>() {

    @JvmField // Not to increase method count
    val onActiveListeners = LinkedList<LifecycleListener>()

    @JvmField // Not to increase method count
    val onInactiveListeners = LinkedList<LifecycleListener>()

    override fun onActive() {
        super.onActive()
        for (listener in onActiveListeners) listener()
    }

    override fun onInactive() {
        super.onInactive()
        for (listener in onInactiveListeners) listener()
    }
}