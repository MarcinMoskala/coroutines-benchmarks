Benchmark                                                                                       Mode  Cnt       Score       Error  Units
CompanyDetailsRepositoryBenchmark.concurrentGetDetailSynchronized                               avgt    5       6.116 ±     0.835  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSynchronized                         avgt    5      12.340 ±     2.728  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSynchronized                              avgt    5    4417.392 ±   905.772  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSynchronizedReadOnlyMap                    avgt    5       1.972 ±     0.505  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSynchronizedReadOnlyMap              avgt    5    8037.048 ±  2130.869  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSynchronizedReadOnlyMap                   avgt    5       0.841 ±     0.094  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailMutex                                      avgt    5      11.646 ±     1.264  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullMutex                                avgt    5      28.432 ±    10.698  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsMutex                                     avgt    5    4193.619 ±  1151.660  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailMutexReadOnlyMap                           avgt    5       2.212 ±     0.376  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullMutexReadOnlyMap                     avgt    5    1054.680 ±   730.055  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsMutexReadOnlyMap                          avgt    5       0.891 ±     0.118  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSingleThreadDispatcher                     avgt    5      19.679 ±     3.180  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSingleThreadDispatcher               avgt    5      29.371 ±    11.110  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSingleThreadDispatcher                    avgt    5    7925.860 ±  1392.910  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSingleThreadDispatcherReadOnlyMap          avgt    5      12.213 ±     0.939  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSingleThreadDispatcherReadOnlyMap    avgt    5   13232.173 ±  2219.482  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSingleThreadDispatcherReadOnlyMap         avgt    5        0.371 ±    0.034  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailConcurrentMap                              avgt    5       2.003 ±     0.379  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullConcurrentMap                        avgt    5       6.792 ±     0.533  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsConcurrentMap                             avgt    5    1260.971 ±   708.362  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSuspendedLazy                              avgt    5       2.418 ±     0.288  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSuspendedLazy                        avgt    5      16.410 ±     5.788  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSuspendedLazy                             avgt    5    3159.567 ±  2704.905  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailCached                                     avgt    5       2.737 ±     1.925  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullCached                               avgt    5      19.052 ±     1.954  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsCached                                    avgt    5    4970.666 ±  2484.428  ms/op


|                                         | getDetail | getDetailOrNull | getReadyDetails |
|-----------------------------------------|-----------|-----------------|-----------------|
| Synchronization block, mutable map      | 6         | 12              | 4417            |
| Synchronization block, read-only map    | 2         | 8037            | 1               |
| Mutex, mutable map                      | 11        | 28              | 4193            |
| Mutex, read-only map                    | 2         | 1054            | 1               |
| Single thread dispatcher, mutable map   | 19        | 29              | 7925            |
| Single thread dispatcher, read-only map | 12        | 13232           | 1               |
| ConcurrentHashMap                       | 2         | 7               | 1260            |
| Suspended lazy map                      | 2         | 16              | 3159            |
| Cache                                   | 2         | 19              | 4970            |


CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSynchronized                       avgt    5       7.523 ±     2.403  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSynchronized                             avgt    5       2.211 ±     0.473  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSynchronized                            avgt    5    6670.039 ±  2218.823  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSynchronizedReadOnlyMap                  avgt    5       1.733 ±     0.232  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSynchronizedReadOnlyMap            avgt    5   11801.580 ±  2658.831  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSynchronizedReadOnlyMap                 avgt    5       0.186 ±     0.036  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailMutex                                    avgt    5       2.481 ±     0.371  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullMutex                              avgt    5       8.043 ±     1.517  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsMutex                                   avgt    5    6908.000 ±  2313.473  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailMutexReadOnlyMap                         avgt    5       1.686 ±     0.278  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullMutexReadOnlyMap                   avgt    5   11272.550 ±   881.794  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsMutexReadOnlyMap                        avgt    5       0.181 ±     0.020  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSingleThreadDispatcher                   avgt    5     314.735 ±    64.833  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSingleThreadDispatcher             avgt    5     327.948 ±    59.643  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSingleThreadDispatcher                  avgt    5   14017.971 ±  4840.546  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSingleThreadDispatcherReadOnlyMap        avgt    5     277.466 ±    80.118  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSingleThreadDispatcherReadOnlyMap  avgt    5   31112.306 ±  1868.579  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSingleThreadDispatcherReadOnlyMap       avgt    5    6896.289 ±  2621.760  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailConcurrentMap                            avgt    5       1.926 ±     0.348  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullConcurrentMap                      avgt    5       6.383 ±     1.841  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsConcurrentMap                           avgt    5    7974.399 ±   494.942  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSuspendedLazy                      avgt    5      16.955 ±     9.889  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSuspendedLazy                            avgt    5       2.832 ±     0.403  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSuspendedLazy                           avgt    5   22988.299 ±  4933.471  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailCached                                   avgt    5       3.314 ±     0.462  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullCached                             avgt    5      32.116 ±    11.812  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsCached                                  avgt    5   30595.166 ±  3467.385  ms/op


|                                         | getDetail | getDetailOrNull | getReadyDetails |
|-----------------------------------------|-----------|-----------------|-----------------|
| Synchronization block, mutable map      | 7         | 2               | 6670            |
| Synchronization block, read-only map    | 2         | 11801           | 1               |
| Mutex, mutable map                      | 2         | 8               | 6908            |
| Mutex, read-only map                    | 1         | 11272           | 1               |
| Single thread dispatcher, mutable map   | 314       | 327             | 14017           |
| Single thread dispatcher, read-only map | 277       | 31112           | 6896            |
| ConcurrentHashMap                       | 2         | 6               | 7974            |
| Suspended lazy map                      | 16        | 3               | 22988           |
| Cache                                   | 3         | 32              | 30595           |

SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelSynchronizedTest                      avgt    5   80944.637 ±  8133.756  ms/op\
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelMutexTest                             avgt    5   82312.224 ±  2597.247  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelSingleThreadDispatcherTest            avgt    5   78759.868 ±  4243.825  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelConcurrentListTest                    avgt    5   67019.492 ± 15060.832  ms/op

SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleSynchronizedTest                        avgt    5  342406.942 ±  3834.910  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleMutexTest                               avgt    5  346528.379 ±  3388.480  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleSingleThreadDispatcherTest              avgt    5  318263.436 ±   786.039  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleConcurrentListTest                      avgt    5  363804.991 ±   521.427  ms/op

|                          | Parallel | Single-thread |
|--------------------------|----------|---------------|
| Synchronized block       | 80,944   | 342,406       |
| Mutex                    | 82,312   | 346,528       |
| Single thread dispatcher | 78,759   | 318,263       |
| ConcurrentHashMap        | 67,019   | 363,804       |

SynchronizedInMemoryMapRepositoryBenchmark.synchronizedAddingCopyingTest                        avgt    5     378.055 ±     8.410  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.mutexAddingCopyingTest                               avgt    5     450.284 ±     9.686  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleThreadDispatcherAddingCopyingTest              avgt    5     474.028 ±     2.754  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.concurrentListAddingCopyingTest                      avgt    5     112.916 ±     2.205  ms/op

SynchronizedInMemoryMapRepositoryBenchmark.singleSynchronizedAddingCopyingTest                  avgt    5  416990.115 ± 87633.500  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleMutexAddingCopyingTest                         avgt    5  422826.311 ± 56003.777  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleSingleThreadDispatcherAddingCopyingTest        avgt    5  448121.437 ± 15493.419  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleConcurrentListAddingCopyingTest                avgt    5  119882.710 ±  1036.794  ms/op

|                          | Parallel | Single-thread |
|--------------------------|----------|---------------|
| Synchronized block       | 378      | 416,990       |
| Mutex                    | 450      | 422,826       |
| Single thread dispatcher | 474      | 448,121       |
| ConcurrentHashMap        | 112      | 119,882       |

StateSynchronizationBenchmark.atomicTest                                                        avgt    5     257.943 ±    26.463  ms/op
StateSynchronizationBenchmark.limitedDispatcherSwitchingTest                                    avgt    5    8952.662 ±   420.917  ms/op
StateSynchronizationBenchmark.limitedDispatcherTest                                             avgt    5      46.282 ±     4.315  ms/op
StateSynchronizationBenchmark.mutableConcurrentListTest                                         avgt    5    2680.584 ±  2183.221  ms/op
StateSynchronizationBenchmark.mutableListLimitedDispatcherSwitchingTest                         avgt    5   10177.967 ±  1910.568  ms/op
StateSynchronizationBenchmark.mutableListLimitedDispatcherTest                                  avgt    5     736.813 ±   194.455  ms/op
StateSynchronizationBenchmark.mutableListMutexTest                                              avgt    5    5197.003 ±   774.016  ms/op
StateSynchronizationBenchmark.mutableListSynchronizedTest                                       avgt    5    1021.044 ±   313.479  ms/op
StateSynchronizationBenchmark.mutexTest                                                         avgt    5    2870.236 ±   343.777  ms/op
StateSynchronizationBenchmark.synchronizedTest                                                  avgt    5     409.594 ±    53.840  ms/op

ReflectionBenchmark.atomicCounterCall                                                           avgt    5      50.218 ±     6.942  ms/op
ReflectionBenchmark.javaReflectionCall                                                          avgt    5     111.285 ±    14.988  ms/op
ReflectionBenchmark.kotlinReflectionCall                                                        avgt    5     166.662 ±    58.159  ms/op
ReflectionBenchmark.kotlinReflectionCallWithFinding                                             avgt    5     798.994 ±   125.760  ms/op
ReflectionBenchmark.nullableValueIncrement                                                      avgt    5      14.230 ±     2.059  ms/op
ReflectionBenchmark.printingCounterCall                                                         avgt    5     192.040 ±    17.942  ms/op
ReflectionBenchmark.rawValueIncrement                                                           avgt    5      ≈ 10⁻⁵              ms/op
ReflectionBenchmark.regularCall                                                                 avgt    5       0.500 ±     1.081  ms/op
ReflectionBenchmark.simpleKotlinReflectionCall                                                  avgt    5       0.654 ±     0.065  ms/op
ReflectionBenchmark.suspendingCounterCall                                                       avgt    5      13.037 ±     2.720  ms/op
ReflectionBenchmark.synchronizedCounterCall                                                     avgt    5      22.529 ±     1.616  ms/op
