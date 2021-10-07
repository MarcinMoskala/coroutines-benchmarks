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
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
open class KotlinBenchmark {

    private val orders: List<Order> = List(100) { Order("Customer$it") }
    private val singleThread = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val e100Threads = Executors.newFixedThreadPool(100).asCoroutineDispatcher()

    @Benchmark
    fun defaultCpu1(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.Default, ::cpu1))
    }

    @Benchmark
    fun defaultCpu2(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.Default, ::cpu2))
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
    fun e100ThreadsCpu1(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, e100Threads, ::cpu1))
    }

    @Benchmark
    fun e100ThreadsCpu2(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, e100Threads, ::cpu2))
    }

    @Benchmark
    fun e100ThreadsBlocking(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, e100Threads, ::blocking))
    }

    @Benchmark
    fun e100ThreadsSuspending(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, e100Threads, ::suspending))
    }

    @Benchmark
    fun singleThreadCpu1(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, singleThread, ::cpu1))
    }

    @Benchmark
    fun singleThreadCpu2(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, singleThread, ::cpu2))
    }

    @Benchmark
    fun singleThreadBlocking(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, singleThread, ::blocking))
    }

    @Benchmark
    fun singleThreadSuspending(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, singleThread, ::suspending))
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
    fun ioBlocking(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.IO, ::blocking))
    }

    @Benchmark
    fun ioSuspending(bh: Blackhole) = runBlocking {
        bh.consume(makeCoffee(orders, Dispatchers.IO, ::suspending))
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
    while (i > 0) i -= 1
    return Coffee(order.copy(customer = order.customer + i))
}

fun cpu2(order: Order): Coffee {
    val numberToCheck = 231235534
    var isPrime = true
    for (i in 1..numberToCheck) {
        if (numberToCheck % i == 0) isPrime = false
    }
    return Coffee(order.copy(customer = order.customer + isPrime))
}

fun blocking(order: Order): Coffee {
    Thread.sleep(1000)
    return Coffee(order)
}

suspend fun suspending(order: Order): Coffee {
    delay(1000)
    return Coffee(order)
}