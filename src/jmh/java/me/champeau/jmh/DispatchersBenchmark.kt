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
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class DispatchersBenchmark {

    private val orders: List<Order> = List(100) { Order("Customer$it") }

    @Benchmark
    fun defaultCpu1(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.Default, ::cpu1))
    }

    @Benchmark
    fun defaultCpu2(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.Default, ::cpu2))
    }

    @Benchmark
    fun defaultMemory(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.Default, ::memory))
    }

    @Benchmark
    fun defaultBlocking(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.Default, ::blocking))
    }

    @Benchmark
    fun defaultSuspending(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.Default, ::suspending))
    }

    @Benchmark
    fun e100ThreadsCpu1(bh: Blackhole, htd: HundredThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, htd.dispather, ::cpu1))
    }

    @Benchmark
    fun e100ThreadsCpu2(bh: Blackhole, htd: HundredThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, htd.dispather, ::cpu2))
    }

    @Benchmark
    fun e100ThreadsMemory(bh: Blackhole, htd: HundredThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, htd.dispather, ::memory))
    }

    @Benchmark
    fun e100ThreadsBlocking(bh: Blackhole, htd: HundredThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, htd.dispather, ::blocking))
    }

    @Benchmark
    fun e100ThreadsSuspending(bh: Blackhole, htd: HundredThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, htd.dispather, ::suspending))
    }

    @Benchmark
    fun singleThreadCpu1(bh: Blackhole, std: SingleThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, std.dispather, ::cpu1))
    }

    @Benchmark
    fun singleThreadCpu2(bh: Blackhole, std: SingleThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, std.dispather, ::cpu2))
    }

    @Benchmark
    fun singleThreadMemory(bh: Blackhole, std: SingleThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, std.dispather, ::memory))
    }

    @Benchmark
    fun singleThreadBlocking(bh: Blackhole, std: SingleThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, std.dispather, ::blocking))
    }

    @Benchmark
    fun singleThreadSuspending(bh: Blackhole, std: SingleThreadDispatcher) = runBlocking {
        bh.consume(makeCoffee(orders, std.dispather, ::suspending))
    }

    @Benchmark
    fun ioCpu1(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.IO, ::cpu1))
    }

    @Benchmark
    fun ioCpu2(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.IO, ::cpu2))
    }

    @Benchmark
    fun ioMemory(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.IO, ::memory))
    }

    @Benchmark
    fun ioBlocking(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.IO, ::blocking))
    }

    @Benchmark
    fun ioSuspending(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.IO, ::suspending))
    }

    @State(Scope.Thread)
    open class SingleThreadDispatcher {
        lateinit var dispather: CoroutineDispatcher
        lateinit var executor: ExecutorService

        @Setup(Level.Trial)
        fun doSetup() {
            executor = Executors.newSingleThreadExecutor()
            dispather = executor.asCoroutineDispatcher()
        }

        @TearDown(Level.Trial)
        fun doTearDown() {
            executor.shutdown()
            dispather.cancel()
        }
    }

    @State(Scope.Thread)
    open class HundredThreadDispatcher {
        lateinit var dispather: CoroutineDispatcher
        lateinit var executor: ExecutorService

        @Setup(Level.Trial)
        fun doSetup() {
            executor = Executors.newFixedThreadPool(100)
            dispather = executor.asCoroutineDispatcher()
        }

        @TearDown(Level.Trial)
        fun doTearDown() {
            executor.shutdown()
            dispather.cancel()
        }
    }
}

data class Order(val customer: String)
data class Coffee(val order: Order)

suspend fun makeCoffee(
    orders: List<Order>,
    dispatcher: CoroutineDispatcher,
    operation: suspend (Order) -> Coffee
) = withContext(dispatcher) {
    orders.map { async { operation(it) } }
        .map { it.join() }
}

fun cpu1(order: Order): Coffee {
    var i = Int.MAX_VALUE
    while (i > 0) {
        i -= if (i % 2 == 0) 1 else 2
    }
    return Coffee(order.copy(customer = order.customer + i))
}

fun cpu2(order: Order): Coffee {
    var isPrime = true
    for (numberToCheck in 1..23774) {
        isPrime = true
        for (i in 1..numberToCheck) {
            if (numberToCheck % i == 0) isPrime = false
        }
    }
    return Coffee(order.copy(customer = order.customer + isPrime))
}

fun memory(order: Order): Coffee {
    val list = List(1_000) { it }
    val list2 = List(1_000) { list }
    val list3 = List(1_000) { list2 }
    return Coffee(order.copy(customer = order.customer + list3.hashCode()))
}

fun blocking(order: Order): Coffee {
    Thread.sleep(1000)
    return Coffee(order)
}

suspend fun suspending(order: Order): Coffee {
    delay(1000)
    return Coffee(order)
}