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
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class IntensiveStateSynchronizationBenchmark {

    @Benchmark
    fun synchronizedTest(bh: Blackhole) = runBlocking(Dispatchers.Default) {
        var i = 0L
        val lock = Any()
        massiveRun {
            synchronized(lock) {
                i++
            }
        }
        require(i == 1000 * 10_000L)
        bh.consume(i)
    }

    @Benchmark
    fun mutexTest(bh: Blackhole, m: MutexWrapper) = runBlocking(Dispatchers.Default) {
        var i = 0L
        massiveRun {
            m.mutex.withLock {
                i++
            }
        }
        require(i == 1000 * 10_000L)
        bh.consume(i)
    }

    @Benchmark
    fun limitedDispatcherTest(bh: Blackhole, d: SingleThreadDispatcher) = runBlocking(d.dispather) {
        var i = 0L
        massiveRun {
            i++
        }
        require(i == 1000 * 10_000L)
        bh.consume(i)
    }

    @Benchmark
    fun limitedDispatcherSwitchingTest(bh: Blackhole, d: SingleThreadDispatcher) = runBlocking(Dispatchers.Default) {
        var i = 0L
        massiveRun {
            withContext(d.dispather) {
                i++
            }
        }
        require(i == 1000 * 10_000L)
        bh.consume(i)
    }


    @Benchmark
    fun atomicTest(bh: Blackhole, m: MutexWrapper) = runBlocking(Dispatchers.Default) {
        val i = AtomicLong()
        massiveRun {
            i.incrementAndGet()
        }
        require(i.get() == 1000 * 10_000L)
        bh.consume(i.get())
    }

    @Benchmark
    fun mutableListSynchronizedTest(bh: Blackhole) = runBlocking(Dispatchers.Default) {
        val list = mutableListOf<Int>()
        massiveRun {
            synchronized(this@IntensiveStateSynchronizationBenchmark) {
                list.add(it)
            }
        }
        require(list.size == 1000 * 10_000)
        bh.consume(list.toList())
    }

    @Benchmark
    fun mutableListLimitedDispatcherTest(bh: Blackhole, d: SingleThreadDispatcher) = runBlocking(d.dispather) {
        val list = mutableListOf<Int>()
        massiveRun {
            list.add(it)
        }
        require(list.size == 1000 * 10_000)
        bh.consume(list.toList())
    }

    @Benchmark
    fun mutableListLimitedDispatcherSwitchingTest(bh: Blackhole, d: SingleThreadDispatcher) =
        runBlocking(Dispatchers.Default) {
            val list = mutableListOf<Int>()
            massiveRun {
                withContext(d.dispather) {
                    list.add(it)
                }
            }
            require(list.size == 1000 * 10_000)
            bh.consume(list.toList())
        }

    @Benchmark
    fun mutableListMutexTest(bh: Blackhole, m: MutexWrapper) = runBlocking(Dispatchers.Default) {
        val list = mutableListOf<Int>()
        massiveRun {
            m.mutex.withLock {
                list.add(it)
            }
        }
        require(list.size == 1000 * 10_000)
        bh.consume(list.toList())
    }

    @Benchmark
    fun mutableConcurrentListTest(bh: Blackhole, m: MutexWrapper) = runBlocking(Dispatchers.Default) {
        val list = ConcurrentHashMap.newKeySet<Int>()
        massiveRun {
            list.add(it)
        }
        require(list.size == 1000 * 10_000)
        bh.consume(list.toList())
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

private val list = List(100) { it }.shuffled()

private fun intensiveComputation() = list.sorted()

private suspend fun massiveRun(repeats: Int = 10_000, action: suspend (Int) -> Unit) =
    coroutineScope {
        repeat(1000) { i ->
            intensiveComputation()
            launch {
                repeat(repeats) { j ->
                    intensiveComputation()
                    action(i * 10_000 + j)
                }
            }
        }
    }
