package me.champeau.jmh

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.withLock
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class SynchronizedInMemoryMapRepositoryBenchmark {

    @Benchmark
    fun synchronizedAddingCopyingTest(bh: Blackhole, repo: SynchronizedInMemoryMapRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.set("key$it", it)
            }
            massiveRun(1) {
                bh.consume(repo.getAll())
            }
        }

    @Benchmark
    fun singleSynchronizedAddingCopyingTest(bh: Blackhole, repo: SynchronizedInMemoryMapRepository) =
        runBlocking {
            repeat(1_000_000) {
                repo.set("key$it", it)
            }
            repeat(10_000) {
                bh.consume(repo.getAll())
            }
        }

    @State(Scope.Thread)
    open class SynchronizedInMemoryMapRepository {
        private val values = mutableMapOf<String, Int>()

        fun getAll() = synchronized(this) {
            values.values.toList()
        }

        fun set(key: String, value: Int) = synchronized(this) {
            values[key] = value
        }
    }

    @Benchmark
    fun mutexAddingCopyingTest(bh: Blackhole, repo: MutexInMemoryIntRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.set("key$it", it)
            }
            massiveRun(1) {
                bh.consume(repo.getAll())
            }
        }

    @Benchmark
    fun singleMutexAddingCopyingTest(bh: Blackhole, repo: MutexInMemoryIntRepository) =
        runBlocking {
            repeat(1_000_000) {
                repo.set("key$it", it)
            }
            repeat(10_000) {
                bh.consume(repo.getAll())
            }
        }

    @State(Scope.Thread)
    open class MutexInMemoryIntRepository {
        private val values = mutableMapOf<String, Int>()
        private val mutex = kotlinx.coroutines.sync.Mutex()

        suspend fun getAll() = mutex.withLock {
            values.values.toList()
        }

        suspend fun set(key: String, value: Int) = mutex.withLock {
            values[key] = value
        }
    }

    @Benchmark
    fun singleThreadDispatcherAddingCopyingTest(bh: Blackhole, repo: SingleThreadDispatcherInMemoryIntRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.set("key$it", it)
            }
            massiveRun(1) {
                bh.consume(repo.getAll())
            }
        }

    @Benchmark
    fun singleSingleThreadDispatcherAddingCopyingTest(bh: Blackhole, repo: SingleThreadDispatcherInMemoryIntRepository) =
        runBlocking {
            repeat(1_000_000) {
                repo.set("key$it", it)
            }
            repeat(10_000) {
                bh.consume(repo.getAll())
            }
        }

    @State(Scope.Thread)
    open class SingleThreadDispatcherInMemoryIntRepository {
        private val values = mutableMapOf<String, Int>()
        private val dispatcher = Dispatchers.Default.limitedParallelism(1)

        suspend fun getAll() = withContext(dispatcher) {
            values.values.toList()
        }

        suspend fun set(key: String, value: Int) = withContext(dispatcher) {
            values[key] = value
        }
    }

    @Benchmark
    fun concurrentListAddingCopyingTest(bh: Blackhole, repo: ConcurrentListInMemoryIntRepository) =
        runBlocking(Dispatchers.Default) {
            massiveRun(100) {
                repo.set("key$it", it)
            }
            massiveRun(1) {
                bh.consume(repo.getAll())
            }
        }

    @Benchmark
    fun singleConcurrentListAddingCopyingTest(bh: Blackhole, repo: ConcurrentListInMemoryIntRepository) =
        runBlocking {
            repeat(1_000_000) {
                repo.set("key$it", it)
            }
            repeat(10_000) {
                bh.consume(repo.getAll())
            }
        }

    @State(Scope.Thread)
    open class ConcurrentListInMemoryIntRepository {
        private val values = ConcurrentHashMap<String, Int>()

        fun getAll() {
            values.values.toList()
        }

        fun set(key: String, value: Int) {
            values[key] = value
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
