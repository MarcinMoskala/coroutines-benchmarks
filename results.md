Benchmark                                                                                       Mode  Cnt       Score       Error  Units
CompanyDetailsRepositoryBenchmark.concurrentGetDetailSynchronized                               avgt    5       6.116 ±     0.835  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSynchronized                         avgt    5   1.512 ± 0.084  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSynchronized                              avgt    5    4417.392 ±   905.772  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSynchronizedReadOnlyMap                    avgt    5       1.972 ±     0.505  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSynchronizedReadOnlyMap              avgt    5   0.679 ± 0.049  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSynchronizedReadOnlyMap                   avgt    5       0.841 ±     0.094  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailMutex                                      avgt    5      11.646 ±     1.264  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullMutex                                avgt    5   3.193 ± 0.351  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsMutex                                     avgt    5    4193.619 ±  1151.660  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailMutexReadOnlyMap                           avgt    5       2.212 ±     0.376  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullMutexReadOnlyMap                     avgt    5   0.687 ± 0.043  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsMutexReadOnlyMap                          avgt    5       0.891 ±     0.118  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSingleThreadDispatcher                     avgt    5      19.679 ±     3.180  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSingleThreadDispatcher               avgt    5   5.252 ± 0.360  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSingleThreadDispatcher                    avgt    5    7925.860 ±  1392.910  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSingleThreadDispatcherReadOnlyMap          avgt    5      12.213 ±     0.939  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSingleThreadDispatcherReadOnlyMap    avgt    5   0.697 ± 0.062  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSingleThreadDispatcherReadOnlyMap         avgt    5        0.371 ±    0.034  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailConcurrentMap                              avgt    5       2.003 ±     0.379  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullConcurrentMap                        avgt    5   0.929 ± 0.191  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsConcurrentMap                             avgt    5    1260.971 ±   708.362  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSuspendedLazy                              avgt    5       2.418 ±     0.288  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSuspendedLazy                        avgt    5   1.678 ± 0.468  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSuspendedLazy                             avgt    5    3159.567 ±  2704.905  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailCached                                     avgt    5       2.737 ±     1.925  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullCached                               avgt    5   1.271 ± 0.068  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsCached                                    avgt    5    4970.666 ±  2484.428  ms/op



|                                         | getDetail | getDetailOrNull | getReadyDetails |
|-----------------------------------------|-----------|-----------------|-----------------|
| Synchronization block, mutable map      | 6         | 2               | 4417            |
| Synchronization block, read-only map    | 2         | 1               | 1               |
| Mutex, mutable map                      | 11        | 3               | 4193            |
| Mutex, read-only map                    | 2         | 1               | 1               |
| Single thread dispatcher, mutable map   | 19        | 5               | 7925            |
| Single thread dispatcher, read-only map | 12        | 1               | 1               |
| ConcurrentHashMap                       | 2         | 1               | 1260            |
| Suspended lazy map                      | 2         | 2               | 3159            |
| Cache                                   | 2         | 1               | 4970            |


CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSynchronized                       avgt    5       7.523 ±     2.403  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSynchronized                       avgt    5   0.441 ± 0.006  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSynchronized                            avgt    5    6670.039 ±  2218.823  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSynchronizedReadOnlyMap                  avgt    5       1.733 ±     0.232  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSynchronizedReadOnlyMap            avgt    5   0.331 ± 0.003  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSynchronizedReadOnlyMap                 avgt    5       0.186 ±     0.036  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailMutex                                    avgt    5       2.481 ±     0.371  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullMutex                              avgt    5   0.445 ± 0.005  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsMutex                                   avgt    5    6908.000 ±  2313.473  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailMutexReadOnlyMap                         avgt    5       1.686 ±     0.278  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullMutexReadOnlyMap                   avgt    5   0.332 ± 0.003  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsMutexReadOnlyMap                        avgt    5       0.181 ±     0.020  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSingleThreadDispatcher                   avgt    5     314.735 ±    64.833  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSingleThreadDispatcher             avgt    5  60.419 ± 0.437  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSingleThreadDispatcher                  avgt    5   14017.971 ±  4840.546  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSingleThreadDispatcherReadOnlyMap        avgt    5     277.466 ±    80.118  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSingleThreadDispatcherReadOnlyMap  avgt    5   0.328 ± 0.004  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSingleThreadDispatcherReadOnlyMap       avgt    5    6896.289 ±  2621.760  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailConcurrentMap                            avgt    5       1.926 ±     0.348  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullConcurrentMap                      avgt    5   0.419 ± 0.037  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsConcurrentMap                           avgt    5    7974.399 ±   494.942  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSuspendedLazy                            avgt    5       2.832 ±     0.403  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSuspendedLazy                      avgt    5   1.055 ± 0.013  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSuspendedLazy                           avgt    5   22988.299 ±  4933.471  ms/op

CompanyDetailsRepositoryBenchmark.singleThreadGetDetailCached                                   avgt    5       3.314 ±     0.462  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullCached                             avgt    5   0.884 ± 0.082  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsCached                                  avgt    5   30595.166 ±  3467.385  ms/op


|                                         | getDetail | getDetailOrNull | getReadyDetails |
|-----------------------------------------|-----------|-----------------|-----------------|
| Synchronization block, mutable map      | 7         | 1               | 6670            |
| Synchronization block, read-only map    | 2         | 1               | 1               |
| Mutex, mutable map                      | 2         | 1               | 6908            |
| Mutex, read-only map                    | 1         | 1               | 1               |
| Single thread dispatcher, mutable map   | 314       | 60              | 14017           |
| Single thread dispatcher, read-only map | 277       | 1               | 6896            |
| ConcurrentHashMap                       | 2         | 1               | 7974            |
| Suspended lazy map                      | 16        | 1               | 22988           |
| Cache                                   | 3         | 1               | 30595           |

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

SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelSynchronizedTest                avgt    5   45018.312 ±   91.968  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelMutexTest                       avgt    5   36966.543 ±  240.629  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelSingleThreadDispatcherTest      avgt    5   41197.159 ±  151.803  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelConcurrentListTest              avgt    5   19725.759 ±  406.884  ms/op

SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleSynchronizedTest                  avgt    5  351465.785 ± 2921.757  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleMutexTest                         avgt    5  348103.535 ± 4933.188  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleSingleThreadDispatcherTest        avgt    5  319620.289 ± 2866.382  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleConcurrentListTest                avgt    5  363380.422 ± 1070.525  ms/op

SynchronizedInMemoryMapRepositoryBenchmark.synchronizedAddingCopyingTest                  avgt    5     375.004 ±    3.667  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.mutexAddingCopyingTest                         avgt    5     452.598 ±    7.538  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleThreadDispatcherAddingCopyingTest        avgt    5     411.151 ±    4.970  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.concurrentListAddingCopyingTest                avgt    5     109.947 ±    2.248  ms/op

SynchronizedInMemoryMapRepositoryBenchmark.singleSynchronizedAddingCopyingTest            avgt    5     390.313 ±    6.254  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleMutexAddingCopyingTest                   avgt    5     351.759 ±    2.178  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleSingleThreadDispatcherAddingCopyingTest  avgt    5    1015.355 ±    7.195  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleConcurrentListAddingCopyingTest          avgt    5     740.964 ±    9.035  ms/op

SynchronizedInMemoryYieldingMapRepositoryBenchmark.parallelSynchronizedTest               avgt    5   44331.750 ±  124.005  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.parallelMutexTest                      avgt    5   36301.543 ±  211.052  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.parallelSingleThreadDispatcherTest     avgt    5   37621.205 ±  119.572  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.parallelConcurrentListTest             avgt    5   19505.236 ±  112.171  ms/op

SynchronizedInMemoryYieldingMapRepositoryBenchmark.singleSynchronizedTest                 avgt    5   32751.261 ±  229.005  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.singleMutexTest                        avgt    5   33189.603 ±  451.807  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.singleSingleThreadDispatcherTest       avgt    5   34340.421 ±  432.281  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.singleConcurrentListTest               avgt    5   82108.945 ±  203.940  ms/op

// *****

Benchmark                                                                                       Mode  Cnt       Score      Error  Units

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSynchronized                               avgt    5       1.419 ±    0.019  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSynchronized                         avgt    5       1.417 ±    0.013  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSynchronized                              avgt    5    1209.990 ±    4.692  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSynchronizedReadOnlyMap                    avgt    5       0.749 ±    0.005  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSynchronizedReadOnlyMap              avgt    5       0.665 ±    0.004  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSynchronizedReadOnlyMap                   avgt    5       0.350 ±    0.002  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailMutex                                      avgt    5       3.129 ±    0.010  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullMutex                                avgt    5       2.735 ±    0.016  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsMutex                                     avgt    5    1218.078 ±    7.058  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailMutexReadOnlyMap                           avgt    5       0.761 ±    0.008  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullMutexReadOnlyMap                     avgt    5       0.652 ±    0.003  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsMutexReadOnlyMap                          avgt    5       0.347 ±    0.002  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSingleThreadDispatcher                     avgt    5       7.266 ±    0.097  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSingleThreadDispatcher               avgt    5       4.970 ±    0.019  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSingleThreadDispatcher                    avgt    5    1290.269 ±   24.826  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSingleThreadDispatcherReadOnlyMap          avgt    5       4.842 ±    0.054  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSingleThreadDispatcherReadOnlyMap    avgt    5       0.665 ±    0.002  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSingleThreadDispatcherReadOnlyMap         avgt    5       0.366 ±    0.005  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailConcurrentMap                              avgt    5       0.811 ±    0.003  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullConcurrentMap                        avgt    5       0.828 ±    0.003  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsConcurrentMap                             avgt    5     223.974 ±    1.663  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailSuspendedLazy                              avgt    5       0.815 ±    0.004  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullSuspendedLazy                        avgt    5       1.518 ±    0.011  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsSuspendedLazy                             avgt    5     581.896 ±   21.670  ms/op

CompanyDetailsRepositoryBenchmark.concurrentGetDetailCached                                     avgt    5       0.822 ±    0.007  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailOrNullCached                               avgt    5       1.289 ±    0.004  ms/op
CompanyDetailsRepositoryBenchmark.concurrentGetDetailsCached                                    avgt    5     918.877 ±   32.760  ms/op




CompanyDetailsRepositoryBenchmark.singleThreadGetDetailCached                                   avgt    5       0.547 ±    0.003  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailConcurrentMap                            avgt    5       0.328 ±    0.002  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailMutex                                    avgt    5       0.429 ±    0.009  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailMutexReadOnlyMap                         avgt    5       0.320 ±    0.003  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullCached                             avgt    5       0.868 ±    0.015  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullConcurrentMap                      avgt    5       0.410 ±    0.003  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullMutex                              avgt    5       0.452 ±    0.029  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullMutexReadOnlyMap                   avgt    5       0.325 ±    0.005  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSingleThreadDispatcher             avgt    5      62.243 ±    0.805  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSingleThreadDispatcherReadOnlyMap  avgt    5       0.330 ±    0.006  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSuspendedLazy                      avgt    5       1.061 ±    0.004  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSynchronized                       avgt    5       0.436 ±    0.003  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailOrNullSynchronizedReadOnlyMap            avgt    5       0.336 ±    0.011  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSingleThreadDispatcher                   avgt    5      65.486 ±    0.328  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSingleThreadDispatcherReadOnlyMap        avgt    5      62.056 ±    0.687  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSuspendedLazy                            avgt    5       0.437 ±    0.014  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSynchronized                             avgt    5       0.360 ±    0.002  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailSynchronizedReadOnlyMap                  avgt    5       0.320 ±    0.004  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsCached                                  avgt    5    5495.149 ±   43.399  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsConcurrentMap                           avgt    5    1470.315 ±   14.664  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsMutex                                   avgt    5    1146.132 ±   13.598  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsMutexReadOnlyMap                        avgt    5       0.042 ±    0.001  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSingleThreadDispatcher                  avgt    5    1225.865 ±   12.474  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSingleThreadDispatcherReadOnlyMap       avgt    5       0.045 ±    0.001  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSuspendedLazy                           avgt    5    3617.523 ±  592.958  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSynchronized                            avgt    5    1295.016 ±    8.064  ms/op
CompanyDetailsRepositoryBenchmark.singleThreadGetDetailsSynchronizedReadOnlyMap                 avgt    5       0.046 ±    0.001  ms/op




ReflectionBenchmark.atomicCounterCall                                                           avgt    5      11.493 ±    0.143  ms/op
ReflectionBenchmark.javaReflectionCall                                                          avgt    5      19.927 ±    0.133  ms/op
ReflectionBenchmark.kotlinReflectionCall                                                        avgt    5      29.005 ±    0.135  ms/op
ReflectionBenchmark.kotlinReflectionCallWithFinding                                             avgt    5     173.445 ±    1.769  ms/op
ReflectionBenchmark.nullableValueIncrement                                                      avgt    5       5.815 ±    0.787  ms/op
ReflectionBenchmark.printingCounterCall                                                         avgt    5      38.934 ±    0.727  ms/op
ReflectionBenchmark.rawValueIncrement                                                           avgt    5      ≈ 10⁻⁶             ms/op
ReflectionBenchmark.regularCall                                                                 avgt    5      ≈ 10⁻⁶             ms/op
ReflectionBenchmark.simpleKotlinReflectionCall                                                  avgt    5      ≈ 10⁻⁶             ms/op
ReflectionBenchmark.suspendingCounterCall                                                       avgt    5       2.912 ±    0.050  ms/op
ReflectionBenchmark.synchronizedCounterCall                                                     avgt    5       5.067 ±    0.048  ms/op
SampleB.consumeNewObject                                                                        avgt    5       0.027 ±    0.001  ms/op
SampleB.consumeString                                                                           avgt    5       0.026 ±    0.001  ms/op
SampleB.measureIsValidIpAddress                                                                 avgt    5       6.965 ±    0.086  ms/op
SampleB.measureIsValidIpAddressOptimized                                                        avgt    5       0.959 ±    0.023  ms/op
SampleB.sampleA                                                                                 avgt    5       0.025 ±    0.001  ms/op
SampleB.sampleOptimized                                                                         avgt    5      ≈ 10⁻⁶             ms/op
StateSynchronizationBenchmark.atomicTest                                                        avgt    5     660.080 ±   54.964  ms/op
StateSynchronizationBenchmark.limitedDispatcherSwitchingTest                                    avgt    5    4535.922 ±   56.656  ms/op
StateSynchronizationBenchmark.limitedDispatcherTest                                             avgt    5      20.036 ±    0.188  ms/op
StateSynchronizationBenchmark.mutableConcurrentListTest                                         avgt    5     528.662 ±  125.718  ms/op
StateSynchronizationBenchmark.mutableListLimitedDispatcherSwitchingTest                         avgt    5    4862.101 ±  143.749  ms/op
StateSynchronizationBenchmark.mutableListLimitedDispatcherTest                                  avgt    5     192.919 ±   22.189  ms/op
StateSynchronizationBenchmark.mutableListMutexTest                                              avgt    5    1778.991 ±  166.889  ms/op
StateSynchronizationBenchmark.mutableListSynchronizedTest                                       avgt    5     464.897 ±   85.463  ms/op
StateSynchronizationBenchmark.mutexTest                                                         avgt    5    1419.260 ±   11.180  ms/op
StateSynchronizationBenchmark.synchronizedTest                                                  avgt    5     274.339 ±    5.146  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelConcurrentListTest                    avgt    5   19857.644 ±  968.383  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelMutexTest                             avgt    5   36566.579 ±  139.964  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelSingleThreadDispatcherTest            avgt    5   33699.268 ±  251.928  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.parallelSynchronizedTest                      avgt    5       0.379 ±    0.003  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleConcurrentListTest                      avgt    5  363566.378 ± 1306.083  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleMutexTest                               avgt    5  342313.281 ± 2600.233  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleSingleThreadDispatcherTest              avgt    5  320262.179 ± 1977.118  ms/op
SynchronizedInMemoryDelayedMapRepositoryBenchmark.singleSynchronizedTest                        avgt    5  335330.240 ± 1245.704  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.concurrentListAddingCopyingTest                      avgt    5     118.713 ±    1.575  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.mutexAddingCopyingTest                               avgt    5     442.687 ±    8.433  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleConcurrentListAddingCopyingTest                avgt    5     738.830 ±   11.562  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleMutexAddingCopyingTest                         avgt    5     356.949 ±    2.223  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleSingleThreadDispatcherAddingCopyingTest        avgt    5    1005.829 ±    5.827  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleSynchronizedAddingCopyingTest                  avgt    5     385.402 ±    1.716  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.singleThreadDispatcherAddingCopyingTest              avgt    5     500.784 ±    1.712  ms/op
SynchronizedInMemoryMapRepositoryBenchmark.synchronizedAddingCopyingTest                        avgt    5     371.087 ±    4.709  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.parallelConcurrentListTest                   avgt    5   19174.632 ±  186.961  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.parallelMutexTest                            avgt    5   36622.886 ±  342.561  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.parallelSingleThreadDispatcherTest           avgt    5   38706.956 ±   79.531  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.parallelSynchronizedTest                     avgt    5   44023.885 ±  113.103  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.singleConcurrentListTest                     avgt    5   80201.992 ±  358.586  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.singleMutexTest                              avgt    5   33036.107 ±  124.771  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.singleSingleThreadDispatcherTest             avgt    5   33718.782 ±  135.935  ms/op
SynchronizedInMemoryYieldingMapRepositoryBenchmark.singleSynchronizedTest                       avgt    5   32952.601 ±  111.840  ms/op
