package me.champeau.jmh

import kotlinx.coroutines.runBlocking
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class ReflectionBenchmark {
    @State(Scope.Thread)
    open class Counter {
        var value = 0

        fun increment() {
            value++
        }

        fun decrement() {
            value--
        }

        fun get() = value
    }
    
    @Benchmark
    fun simpleKotlinReflectionCall(bh: Blackhole, counter: Counter) {
        repeat(1_000_000) {
            (Counter::increment)(counter)
            (Counter::decrement)(counter)
            (Counter::increment)(counter)
            bh.consume((Counter::get)(counter))
        }
    }

    @Benchmark
    fun creatingSomeObjects(bh: Blackhole, counter: Counter) {
        repeat(1_000_000) {
            bh.consume(Any())
            bh.consume(Any())
            bh.consume(Any())
            bh.consume(Any())
        }
    }

    @Benchmark
    fun rawValueIncrement(bh: Blackhole, counter: Counter) {
        var counter = 0
        repeat(1_000_000) {
            counter++
            counter--
            counter++
            bh.consume(counter)
        }
    }

    @Benchmark
    fun nullableValueIncrement(bh: Blackhole, counter: Counter) {
        var counter: Int? = 0
        repeat(1_000_000) {
            counter = counter?.inc()
            counter = counter?.dec()
            counter = counter?.inc()
            bh.consume(counter)
        }
    }

    @Benchmark
    fun regularCall(bh: Blackhole, counter: Counter) {
        repeat(1_000_000) {
            counter.increment()
            counter.decrement()
            counter.increment()
            bh.consume(counter.get())
        }
    }

    @Benchmark
    fun javaReflectionCall(bh: Blackhole, counter: Counter) {
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
    fun kotlinReflectionCall(bh: Blackhole, counter: Counter) {
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
    fun kotlinReflectionCallWithFinding(bh: Blackhole, counter: Counter) {
        repeat(1_000_000) {
            Counter::class.members.first { it.name == "increment" }.call(counter)
            Counter::class.members.first { it.name == "decrement" }.call(counter)
            Counter::class.members.first { it.name == "increment" }.call(counter)
            bh.consume(Counter::class.members.first { it.name == "get" }.call(counter))
        }
    }
    
    @State(Scope.Thread)
    open class SynchronizedCounter {
        var value = 0

        fun increment() = synchronized(this) {
            value++
        }

        fun decrement() = synchronized(this) {
            value--
        }

        fun get() = synchronized(this) { value }
    }
    
    @Benchmark
    fun synchronizedCounterCall(bh: Blackhole, counter: SynchronizedCounter) {
        repeat(1_000_000) {
            counter.increment()
            counter.decrement()
            counter.increment()
            bh.consume(counter.get())
        }
    }
    
    @State(Scope.Thread)
    open class AtomicCounter {
        var value = AtomicInteger(0)

        fun increment() {
            value.incrementAndGet()
        }

        fun decrement() {
            value.decrementAndGet()
        }

        fun get() = value
    }
    
    @Benchmark
    fun atomicCounterCall(bh: Blackhole, counter: AtomicCounter) {
        repeat(1_000_000) {
            counter.increment()
            counter.decrement()
            counter.increment()
            bh.consume(counter.get())
        }
    }
    
    @State(Scope.Thread)
    open class PrintingCounter {
        var value = 0

        fun increment() {
            print("I")
            value++
        }

        fun decrement() {
            print("D")
            value--
        }

        fun get() {
            print("G")
            value
        }
    }
    
    @Benchmark
    fun printingCounterCall(bh: Blackhole, counter: PrintingCounter) {
        repeat(10_000) {
            counter.increment()
            counter.decrement()
            counter.increment()
            bh.consume(counter.get())
        }
    }
    
    @State(Scope.Thread)
    open class SuspendingCounter {
        var value = 0

        suspend fun increment() {
            value++
        }

        suspend fun decrement() {
            value--
        }

        suspend fun get() {
            value
        }
    }
    
    @Benchmark
    fun suspendingCounterCall(bh: Blackhole, counter: SuspendingCounter) = runBlocking {
        repeat(1_000_000) {
            counter.increment()
            counter.decrement()
            counter.increment()
            bh.consume(counter.get())
        }
    }
}
