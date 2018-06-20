/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.koin.androidx.scope

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import org.koin.core.Koin
import org.koin.standalone.KoinComponent
import org.koin.standalone.release

/**
 * Observe a LifecycleOwner
 *
 * @author Arnaud Giuliani
 *
 * on ON_DESTROY drop given module path
 */
class ScopeObserver(val className: String, val module: Array<out String>) : LifecycleObserver, KoinComponent {

    /**
     * Handle ON_DESTROY to release Koin modules
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Koin.logger.log("received ON_DESTROY for $className")
        module.forEach { release(it) }
    }
}