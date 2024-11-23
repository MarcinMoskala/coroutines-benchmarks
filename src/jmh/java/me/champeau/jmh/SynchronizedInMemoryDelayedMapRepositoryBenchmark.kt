package me.champeau.jmh

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class SynchronizedInMemoryDelayedMapRepositoryBenchmark {

    @Benchmark
    fun parallelSynchronizedTest(bh: Blackhole, repo: SynchronizedInMemoryDelayedMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                val o = Any()
            }
        }

    @Benchmark
    fun singleSynchronizedTest(bh: Blackhole, repo: SynchronizedInMemoryDelayedMapRepository) =
        runBlocking {
            repeat(100_000) {
                repo.getOrAdd("key$it", it)
            }
        }

    @State(Scope.Thread)
    open class SynchronizedInMemoryDelayedMapRepository {
        private val values = mutableMapOf<String, Int>()
        private val lock = Any()

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        suspend fun getOrAdd(key: String, value: Int): List<Int> {
            synchronized(lock) { values[key] = value }
            delay(1)
            return synchronized(lock) { values.values.toList() }
        }
    }

    @Benchmark
    fun parallelMutexTest(bh: Blackhole, repo: MutexInMemoryDelayedMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.getOrAdd("key$it", it)
            }
        }

    @Benchmark
    fun singleMutexTest(bh: Blackhole, repo: MutexInMemoryDelayedMapRepository) =
        runBlocking {
            repeat(100_000) {
                repo.getOrAdd("key$it", it)
            }
        }

    @State(Scope.Thread)
    open class MutexInMemoryDelayedMapRepository {
        private val values = mutableMapOf<String, Int>()
        private val mutex = Mutex()

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        suspend fun getOrAdd(key: String, value: Int): List<Int> {
            mutex.withLock { values[key] = value }
            delay(1)
            return mutex.withLock { values.values.toList() }
        }
    }

    @Benchmark
    fun parallelSingleThreadDispatcherTest(bh: Blackhole, repo: SingleThreadDispatcherInMemoryDelayedMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.getOrAdd("key$it", it)
            }
        }

    @Benchmark
    fun singleSingleThreadDispatcherTest(bh: Blackhole, repo: SingleThreadDispatcherInMemoryDelayedMapRepository) =
        runBlocking {
            repeat(100_000) {
                repo.getOrAdd("key$it", it)
            }
        }

    @State(Scope.Thread)
    open class SingleThreadDispatcherInMemoryDelayedMapRepository {
        private val values = mutableMapOf<String, Int>()
        private val d = Dispatchers.Default.limitedParallelism(1)

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        suspend fun getOrAdd(key: String, value: Int): List<Int> = withContext(d) {
            values[key] = value
            delay(1)
            values.values.toList()
        }
    }

    @Benchmark
    fun parallelConcurrentListTest(bh: Blackhole, repo: ConcurrentListInMemoryDelayedMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.getOrAdd("key$it", it)
            }
        }

    @Benchmark
    fun singleConcurrentListTest(bh: Blackhole, repo: ConcurrentListInMemoryDelayedMapRepository) =
        runBlocking {
            repeat(100_000) {
                repo.getOrAdd("key$it", it)
            }
        }

    @State(Scope.Thread)
    open class ConcurrentListInMemoryDelayedMapRepository {
        private val values = ConcurrentHashMap<String, Int>()

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        suspend fun getOrAdd(key: String, value: Int): List<Int> {
            values[key] = value
            delay(1)
            return values.values.toList()
        }
    }
}

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class SynchronizedInMemoryYieldingMapRepositoryBenchmark {

    @Benchmark
    fun parallelSynchronizedTest(bh: Blackhole, repo: SynchronizedInMemoryDelayedMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.getOrAdd("key$it", it)
            }
        }

    @Benchmark
    fun singleSynchronizedTest(bh: Blackhole, repo: SynchronizedInMemoryDelayedMapRepository) =
        runBlocking {
            repeat(100_000) {
                repo.getOrAdd("key$it", it)
            }
        }

    @State(Scope.Thread)
    open class SynchronizedInMemoryDelayedMapRepository {
        private val values = mutableMapOf<String, Int>()
        private val lock = Any()

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        suspend fun getOrAdd(key: String, value: Int): List<Int> {
            synchronized(lock) { values[key] = value }
            yield()
            return synchronized(lock) { values.values.toList() }
        }
    }

    @Benchmark
    fun parallelMutexTest(bh: Blackhole, repo: MutexInMemoryDelayedMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.getOrAdd("key$it", it)
            }
        }

    @Benchmark
    fun singleMutexTest(bh: Blackhole, repo: MutexInMemoryDelayedMapRepository) =
        runBlocking {
            repeat(100_000) {
                repo.getOrAdd("key$it", it)
            }
        }

    @State(Scope.Thread)
    open class MutexInMemoryDelayedMapRepository {
        private val values = mutableMapOf<String, Int>()
        private val mutex = Mutex()

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        suspend fun getOrAdd(key: String, value: Int): List<Int> {
            mutex.withLock { values[key] = value }
            yield()
            return mutex.withLock { values.values.toList() }
        }
    }

    @Benchmark
    fun parallelSingleThreadDispatcherTest(bh: Blackhole, repo: SingleThreadDispatcherInMemoryDelayedMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.getOrAdd("key$it", it)
            }
        }

    @Benchmark
    fun singleSingleThreadDispatcherTest(bh: Blackhole, repo: SingleThreadDispatcherInMemoryDelayedMapRepository) =
        runBlocking {
            repeat(100_000) {
                repo.getOrAdd("key$it", it)
            }
        }

    @State(Scope.Thread)
    open class SingleThreadDispatcherInMemoryDelayedMapRepository {
        private val values = mutableMapOf<String, Int>()
        private val d = Dispatchers.Default.limitedParallelism(1)

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        suspend fun getOrAdd(key: String, value: Int): List<Int> = withContext(d) {
            values[key] = value
            yield()
            values.values.toList()
        }
    }

    @Benchmark
    fun parallelConcurrentListTest(bh: Blackhole, repo: ConcurrentListInMemoryDelayedMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.getOrAdd("key$it", it)
            }
        }

    @Benchmark
    fun singleConcurrentListTest(bh: Blackhole, repo: ConcurrentListInMemoryDelayedMapRepository) =
        runBlocking {
            repeat(100_000) {
                repo.getOrAdd("key$it", it)
            }
        }

    @State(Scope.Thread)
    open class ConcurrentListInMemoryDelayedMapRepository {
        private val values = ConcurrentHashMap<String, Int>()

        @CompilerControl(CompilerControl.Mode.DONT_INLINE)
        suspend fun getOrAdd(key: String, value: Int): List<Int> {
            values[key] = value
            yield()
            return values.values.toList()
        }
    }
}

suspend private fun massiveRun(repeats: Int = 10_000, action: suspend (Int) -> Unit) =
    coroutineScope {
        repeat(1000) { i ->
            launch {
                repeat(repeats) { j -> action(i * 10_000 + j) }
            }
        }
    }
