package me.champeau.jmh

import com.sksamuel.aedile.core.cacheBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.math.BigDecimal
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit



@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class CompanyDetailsRepositoryBenchmark {
    private val prefilledDetails = List(10_000) { CompanyDetails("Company $it", "Address $it", BigDecimal(1000)) }
        .associateBy { Company("company${it.name}") }

    @Benchmark
    fun concurrentGetDetailSynchronized(bh: Blackhole, repo: SynchronizedCompanyDetailsRepository) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailSynchronized(bh: Blackhole, repo: SynchronizedCompanyDetailsRepository) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsSynchronized(bh: Blackhole, repo: SynchronizedCompanyDetailsRepository) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsSynchronized(bh: Blackhole, repo: SynchronizedCompanyDetailsRepository) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullSynchronized(bh: Blackhole, repo: SynchronizedCompanyDetailsRepository) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullSynchronized(bh: Blackhole, repo: SynchronizedCompanyDetailsRepository) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class SynchronizedCompanyDetailsRepository : CompanyDetailsRepository {
        private var details = mutableMapOf<Company, CompanyDetails>()
        private val lock = Any()

        override suspend fun getDetails(company: Company): CompanyDetails {
            val current = getDetailsOrNull(company)
            if (current == null) {
                val companyDetails = fetchDetails(company)
                synchronized(lock) {
                    details[company] = companyDetails
                }
                return companyDetails
            }
            return current
        }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? =
            synchronized(lock) {
                details[company]
            }

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> =
            synchronized(lock) {
                details.toMap()
            }

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) = synchronized(lock) {
            this.details = details.toMutableMap()
        }
    }

    @Benchmark
    fun concurrentGetDetailMutex(bh: Blackhole, repo: MutexCompanyDetailsRepository) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailMutex(bh: Blackhole, repo: MutexCompanyDetailsRepository) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsMutex(bh: Blackhole, repo: MutexCompanyDetailsRepository) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsMutex(bh: Blackhole, repo: MutexCompanyDetailsRepository) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullMutex(bh: Blackhole, repo: MutexCompanyDetailsRepository) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullMutex(bh: Blackhole, repo: MutexCompanyDetailsRepository) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class MutexCompanyDetailsRepository : CompanyDetailsRepository {
        private var details = mutableMapOf<Company, CompanyDetails>()
        private val mutex = Mutex()

        override suspend fun getDetails(company: Company): CompanyDetails {
            val current = getDetailsOrNull(company)
            if (current == null) {
                val companyDetails = fetchDetails(company)
                mutex.withLock {
                    details[company] = companyDetails
                }
                return companyDetails
            }
            return current
        }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? =
            mutex.withLock {
                details[company]
            }

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> =
            mutex.withLock {
                details.toMap()
            }

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) = mutex.withLock {
            this.details = details.toMutableMap()
        }
    }

    @Benchmark
    fun concurrentGetDetailSingleThreadDispatcher(bh: Blackhole, repo: SingleThreadDispatcherCompanyDetailsRepository) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailSingleThreadDispatcher(
        bh: Blackhole,
        repo: SingleThreadDispatcherCompanyDetailsRepository
    ) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsSingleThreadDispatcher(
        bh: Blackhole,
        repo: SingleThreadDispatcherCompanyDetailsRepository
    ) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsSingleThreadDispatcher(
        bh: Blackhole,
        repo: SingleThreadDispatcherCompanyDetailsRepository
    ) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullSingleThreadDispatcher(
        bh: Blackhole,
        repo: SingleThreadDispatcherCompanyDetailsRepository
    ) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullSingleThreadDispatcher(
        bh: Blackhole,
        repo: SingleThreadDispatcherCompanyDetailsRepository
    ) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class SingleThreadDispatcherCompanyDetailsRepository : CompanyDetailsRepository {
        private var details = mutableMapOf<Company, CompanyDetails>()
        private val dispatcher = Dispatchers.Default.limitedParallelism(1)

        override suspend fun getDetails(company: Company): CompanyDetails = withContext(dispatcher) {
            val current = getDetailsOrNull(company)
            if (current == null) {
                val companyDetails = fetchDetails(company)
                details[company] = companyDetails
                companyDetails
            } else {
                current
            }
        }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? = withContext(dispatcher) {
            details[company]
        }

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> = withContext(dispatcher) {
            details.toMap()
        }

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) = withContext(dispatcher) {
            this@SingleThreadDispatcherCompanyDetailsRepository.details = details.toMutableMap()
        }
    }

    @Benchmark
    fun concurrentGetDetailConcurrentMap(bh: Blackhole, repo: ConcurrentMapCompanyDetailsRepository) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailConcurrentMap(bh: Blackhole, repo: ConcurrentMapCompanyDetailsRepository) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsConcurrentMap(bh: Blackhole, repo: ConcurrentMapCompanyDetailsRepository) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsConcurrentMap(bh: Blackhole, repo: ConcurrentMapCompanyDetailsRepository) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullConcurrentMap(bh: Blackhole, repo: ConcurrentMapCompanyDetailsRepository) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullConcurrentMap(bh: Blackhole, repo: ConcurrentMapCompanyDetailsRepository) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class ConcurrentMapCompanyDetailsRepository : CompanyDetailsRepository {
        private var details = ConcurrentHashMap<Company, CompanyDetails>()
        private val lock = Any()

        override suspend fun getDetails(company: Company): CompanyDetails {
            val current = getDetailsOrNull(company)
            if (current == null) {
                val companyDetails = fetchDetails(company)
                details[company] = companyDetails
                return companyDetails
            }
            return current
        }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? = details[company]

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> =
            details.toMap()

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) = synchronized(lock) {
            this.details = ConcurrentHashMap(details)
        }

        fun clear() = synchronized(lock) {
            details.clear()
        }
    }

    @Benchmark
    fun concurrentGetDetailSuspendedLazy(bh: Blackhole, repo: SuspendedLazyCompanyDetailsRepository) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailSuspendedLazy(bh: Blackhole, repo: SuspendedLazyCompanyDetailsRepository) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsSuspendedLazy(bh: Blackhole, repo: SuspendedLazyCompanyDetailsRepository) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsSuspendedLazy(bh: Blackhole, repo: SuspendedLazyCompanyDetailsRepository) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullSuspendedLazy(bh: Blackhole, repo: SuspendedLazyCompanyDetailsRepository) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullSuspendedLazy(bh: Blackhole, repo: SuspendedLazyCompanyDetailsRepository) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class SuspendedLazyCompanyDetailsRepository : CompanyDetailsRepository {
        private var details =
            ConcurrentHashMap<Company, SuspendLazy<CompanyDetails>>()

        override suspend fun getDetails(company: Company): CompanyDetails {
            details.computeIfAbsent(company) {
                suspendLazy { fetchDetails(company) }
            }
            return details[company]!!.invoke()
        }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? =
            details[company]?.valueOrNull()

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> = details
            .toMap()
            .mapNotNull { (k, v) -> v.valueOrNull()?.let { k to it } }
            .toMap()

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) {
            this.details = ConcurrentHashMap(details.mapValues { (_, v) -> suspendLazy { v } })
            this.details.forEach { (_, v) -> v() }
        }
    }

    @Benchmark
    fun concurrentGetDetailCached(bh: Blackhole, repo: CachedCompanyDetailsRepository) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailCached(bh: Blackhole, repo: CachedCompanyDetailsRepository) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsCached(bh: Blackhole, repo: CachedCompanyDetailsRepository) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsCached(bh: Blackhole, repo: CachedCompanyDetailsRepository) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullCached(bh: Blackhole, repo: CachedCompanyDetailsRepository) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullCached(bh: Blackhole, repo: CachedCompanyDetailsRepository) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class CachedCompanyDetailsRepository : CompanyDetailsRepository {
        private var cache = cacheBuilder<Company, CompanyDetails>().build()

        override suspend fun getDetails(company: Company): CompanyDetails =
            cache.get(company) { fetchDetails(company) }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? =
            cache.getOrNull(company)

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> =
            cache.asDeferredMap()
                .filter { it.value.isCompleted }
                .mapValues { it.value.getCompleted() }

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) {
            cache = cacheBuilder<Company, CompanyDetails>().build()
            details.forEach { (k, v) -> cache.put(k, v) }
        }
    }

    @Benchmark
    fun concurrentGetDetailSynchronizedReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSynchronizedCompanyDetailsRepository
    ) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailSynchronizedReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSynchronizedCompanyDetailsRepository
    ) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsSynchronizedReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSynchronizedCompanyDetailsRepository
    ) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsSynchronizedReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSynchronizedCompanyDetailsRepository
    ) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullSynchronizedReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSynchronizedCompanyDetailsRepository
    ) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullSynchronizedReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSynchronizedCompanyDetailsRepository
    ) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class ReadOnlyMapSynchronizedCompanyDetailsRepository : CompanyDetailsRepository {
        private var details = mapOf<Company, CompanyDetails>()
        private val lock = Any()

        override suspend fun getDetails(company: Company): CompanyDetails {
            val current = getDetailsOrNull(company)
            if (current == null) {
                val companyDetails = fetchDetails(company)
                synchronized(lock) {
                    details += company to companyDetails
                }
                return companyDetails
            }
            return current
        }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? = details[company]

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> = details

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) = synchronized(lock) {
            this.details = details
        }
    }

    @Benchmark
    fun concurrentGetDetailMutexReadOnlyMap(bh: Blackhole, repo: ReadOnlyMapMutexCompanyDetailsRepository) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailMutexReadOnlyMap(bh: Blackhole, repo: ReadOnlyMapMutexCompanyDetailsRepository) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsMutexReadOnlyMap(bh: Blackhole, repo: ReadOnlyMapMutexCompanyDetailsRepository) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsMutexReadOnlyMap(bh: Blackhole, repo: ReadOnlyMapMutexCompanyDetailsRepository) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullMutexReadOnlyMap(bh: Blackhole, repo: ReadOnlyMapMutexCompanyDetailsRepository) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullMutexReadOnlyMap(bh: Blackhole, repo: ReadOnlyMapMutexCompanyDetailsRepository) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class ReadOnlyMapMutexCompanyDetailsRepository : CompanyDetailsRepository {
        private var details = mapOf<Company, CompanyDetails>()
        private val mutex = Mutex()

        override suspend fun getDetails(company: Company): CompanyDetails {
            val current = getDetailsOrNull(company)
            if (current == null) {
                val companyDetails = fetchDetails(company)
                details += company to companyDetails
                return companyDetails
            }
            return current
        }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? =
                details[company]

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> =
                details

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) = mutex.withLock {
            this.details = details
        }
    }

    @Benchmark
    fun concurrentGetDetailSingleThreadDispatcherReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSingleThreadDispatcherCompanyDetailsRepository
    ) =
        concurrentGetDetail(bh, repo)

    @Benchmark
    fun singleThreadGetDetailSingleThreadDispatcherReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSingleThreadDispatcherCompanyDetailsRepository
    ) =
        singleThreadGetDetail(bh, repo)

    @Benchmark
    fun concurrentGetDetailsSingleThreadDispatcherReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSingleThreadDispatcherCompanyDetailsRepository
    ) =
        concurrentGetReadyDetails(bh, repo)

    @Benchmark
    fun singleThreadGetDetailsSingleThreadDispatcherReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSingleThreadDispatcherCompanyDetailsRepository
    ) =
        singleThreadGetReadyDetails(bh, repo)

    @Benchmark
    fun concurrentGetDetailOrNullSingleThreadDispatcherReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSingleThreadDispatcherCompanyDetailsRepository
    ) =
        concurrentGetDetailOrNull(bh, repo)

    @Benchmark
    fun singleThreadGetDetailOrNullSingleThreadDispatcherReadOnlyMap(
        bh: Blackhole,
        repo: ReadOnlyMapSingleThreadDispatcherCompanyDetailsRepository
    ) =
        singleThreadGetDetailOrNull(bh, repo)

    @State(Scope.Thread)
    open class ReadOnlyMapSingleThreadDispatcherCompanyDetailsRepository : CompanyDetailsRepository {
        private var details = mapOf<Company, CompanyDetails>()
        private val dispatcher = Dispatchers.Default.limitedParallelism(1)

        override suspend fun getDetails(company: Company): CompanyDetails = withContext(dispatcher) {
            val current = getDetailsOrNull(company)
            if (current == null) {
                val companyDetails = fetchDetails(company)
                details += company to companyDetails
                companyDetails
            } else {
                current
            }
        }

        override suspend fun getDetailsOrNull(company: Company): CompanyDetails? = details[company]

        override suspend fun getReadyDetails(): Map<Company, CompanyDetails> = details.toMap()

        override suspend fun setAllDetails(details: Map<Company, CompanyDetails>) {
            this.details = details
        }
    }

    fun singleThreadGetDetail(bh: Blackhole, repo: CompanyDetailsRepository) = runBlocking {
        repeat(10_000) {
            bh.consume(repo.getDetails(Company("company$it")))
        }
    }

    fun concurrentGetDetail(bh: Blackhole, repo: CompanyDetailsRepository) = runBlocking(Dispatchers.Default) {
        massiveRun(10) {
            repo.getDetails(Company("company$it"))
        }
    }

    fun singleThreadGetReadyDetails(bh: Blackhole, repo: CompanyDetailsRepository) = runBlocking {
        repo.setAllDetails(prefilledDetails)
        repeat(10_000) {
            bh.consume(repo.getReadyDetails())
        }
    }

    fun concurrentGetReadyDetails(bh: Blackhole, repo: CompanyDetailsRepository) = runBlocking(Dispatchers.Default) {
        repo.setAllDetails(prefilledDetails)
        massiveRun(10) {
            bh.consume(repo.getReadyDetails())
        }
    }

    fun singleThreadGetDetailOrNull(bh: Blackhole, repo: CompanyDetailsRepository) = runBlocking {
        repo.setAllDetails(prefilledDetails)
        repeat(10_000) {
            bh.consume(repo.getDetails(Company("company$it")))
        }
    }

    fun concurrentGetDetailOrNull(bh: Blackhole, repo: CompanyDetailsRepository) = runBlocking(Dispatchers.Default) {
        repo.setAllDetails(prefilledDetails)
        massiveRun(10) {
            bh.consume(repo.getDetails(Company("company$it")))
        }
    }

    interface CompanyDetailsRepository {
        suspend fun getDetails(company: Company): CompanyDetails
        suspend fun getDetailsOrNull(company: Company): CompanyDetails?
        suspend fun getReadyDetails(): Map<Company, CompanyDetails>
        suspend fun setAllDetails(details: Map<Company, CompanyDetails>)

        suspend fun fetchDetails(company: Company): CompanyDetails {
            yield()
            return CompanyDetails("Company ${company.id}", "Address ${company.id}", BigDecimal(1000))
        }
    }

    data class CompanyDetails(val name: String, val address: String, val revenue: BigDecimal)
    data class Company(val id: String)
}

suspend private fun massiveRun(repeats: Int = 10_000, action: suspend (Int) -> Unit) =
    coroutineScope {
        repeat(1000) { i ->
            launch {
                repeat(repeats) { j -> action(i * 10_000 + j) }
            }
        }
    }

fun <T> suspendLazy(initializer: suspend () -> T): SuspendLazy<T> {
    var innerInitializer: (suspend () -> T)? = initializer
    val mutex = Mutex()
    var holder: Any? = Any()

    return object : SuspendLazy<T> {
        override val isInitialized: Boolean
            get() = innerInitializer == null

        override fun valueOrNull(): T? =
            if (isInitialized) holder as T else null

        @Suppress("UNCHECKED_CAST")
        override suspend fun invoke(): T =
            if (isInitialized) holder as T
            else mutex.withLock {
                innerInitializer?.let {
                    holder = it()
                    innerInitializer = null
                }
                holder as T
            }
    }
}

interface SuspendLazy<T> : suspend () -> T {
    val isInitialized: Boolean
    fun valueOrNull(): T?
    override suspend operator fun invoke(): T
}
