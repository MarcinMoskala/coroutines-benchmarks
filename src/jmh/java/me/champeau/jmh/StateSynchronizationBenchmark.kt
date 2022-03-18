/*
 * Copyright 2014-2021 the original author or authors.
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
package me.champeau.jmh

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class StateSynchronizationBenchmark {

    @Benchmark
    fun synchronizedTest(bh: Blackhole) = runBlocking {
        var i = 0L
        massiveRun {
            synchronized(this@StateSynchronizationBenchmark) {
                i++
            }
        }
        bh.consume(i)
    }

    @Benchmark
    fun limitedDispatcherTest(bh: Blackhole, d: SingleThreadDispatcher) = runBlocking(d.dispather) {
        var i = 0L
        massiveRun {
            i++
        }
        bh.consume(i)
    }

    @Benchmark
    fun mutexTest(bh: Blackhole, m: MutexWrapper) = runBlocking {
        var i = 0L
        massiveRun {
            m.mutex.withLock {
                i++
            }
        }
        bh.consume(i)
    }

    @Benchmark
    fun atomicTest(bh: Blackhole, m: MutexWrapper) = runBlocking {
        var i = AtomicInteger()
        massiveRun {
            i.incrementAndGet()
        }
        bh.consume(i)
    }

    @State(Scope.Thread)
    open class SingleThreadDispatcher {
        lateinit var dispather: CoroutineDispatcher

        @Setup(Level.Trial)
        fun doSetup() {
            dispather = Dispatchers.Default.limitedParallelism(1)
        }
    }

    @State(Scope.Thread)
    open class MutexWrapper {
        lateinit var mutex: Mutex

        @Setup(Level.Trial)
        fun doSetup() {
            mutex = Mutex()
        }
    }
}

private suspend fun massiveRun(action: suspend () -> Unit) =
    coroutineScope {
        repeat(10_000) {
            launch {
                repeat(10_000) { action() }
            }
        }
    }