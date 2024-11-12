package me.champeau.jmh

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class ReflectionBenchmark {
    class Counter {
        var value = 0

        fun increment() {
            value++
        }

        fun decrement() {
            value--
        }

        fun get() = value
    }

    private val counter = Counter()

    @Benchmark
    fun simpleKotlinReflectionCall(bh: Blackhole) {
        repeat(1_000_000) {
            (Counter::increment)(counter)
            (Counter::decrement)(counter)
            (Counter::increment)(counter)
            bh.consume((Counter::get)(counter))
        }
    }

    @Benchmark
    fun regularCall(bh: Blackhole) {
        repeat(1_000_000) {
            counter.increment()
            counter.decrement()
            counter.increment()
            bh.consume(counter.get())
        }
    }

    @Benchmark
    fun javaReflectionCall(bh: Blackhole) {
        val increment = Counter::class.java.getDeclaredMethod("increment")
        val decrement = Counter::class.java.getDeclaredMethod("decrement")
        val get = Counter::class.java.getDeclaredMethod("get")
        repeat(1_000_000) {
            increment.invoke(counter)
            decrement.invoke(counter)
            increment.invoke(counter)
            bh.consume(get.invoke(counter))
        }
    }

    @Benchmark
    fun kotlinReflectionCall(bh: Blackhole) {
        val increment = Counter::class.members.first { it.name == "increment" }
        val decrement = Counter::class.members.first { it.name == "decrement" }
        val get = Counter::class.members.first { it.name == "get" }
        repeat(1_000_000) {
            increment.call(counter)
            decrement.call(counter)
            increment.call(counter)
            bh.consume(get.call(counter))
        }
    }

    @Benchmark
    fun kotlinReflectionCallWithFinding(bh: Blackhole) {
        repeat(1_000_000) {
            Counter::class.members.first { it.name == "increment" }.call(counter)
            Counter::class.members.first { it.name == "decrement" }.call(counter)
            Counter::class.members.first { it.name == "increment" }.call(counter)
            bh.consume(Counter::class.members.first { it.name == "get" }.call(counter))
        }
    }
}
