Benchmark                                                                           Mode  Cnt       Score     Error  Units
DispatchersBenchmark.singleThreadCpu1                                               avgt    5   17154.691 ± 115.264  ms/op
DispatchersBenchmark.defaultCpu1                                                    avgt    5    2403.241 ±  33.099  ms/op
DispatchersBenchmark.ioCpu1                                                         avgt    5    2321.450 ±  15.039  ms/op
DispatchersBenchmark.ioCpu2                                                         avgt    5    3024.497 ±  27.987  ms/op

DispatchersBenchmark.singleThreadCpu2                                               avgt    5   19246.087 ±  53.942  ms/op
DispatchersBenchmark.defaultCpu2                                                    avgt    5    3085.273 ±  60.304  ms/op
DispatchersBenchmark.e100ThreadsCpu1                                                avgt    5    8751.729 ±  62.195  ms/op
DispatchersBenchmark.e100ThreadsCpu2                                                avgt    5    2786.834 ±  10.048  ms/op

DispatchersBenchmark.singleThreadMemory                                             avgt    5   91788.703 ± 149.017  ms/op
DispatchersBenchmark.defaultMemory                                                  avgt    5   11933.001 ± 242.976  ms/op
DispatchersBenchmark.ioMemory                                                       avgt    5   11792.620 ± 135.959  ms/op
DispatchersBenchmark.e100ThreadsMemory                                              avgt    5   11746.435 ±  32.588  ms/op

DispatchersBenchmark.singleThreadBlocking                                           avgt    5  100462.012 ± 553.411  ms/op
DispatchersBenchmark.defaultBlocking                                                avgt    5   10041.818 ±   9.982  ms/op
DispatchersBenchmark.ioBlocking                                                     avgt    5    2017.396 ±   2.695  ms/op
DispatchersBenchmark.e100ThreadsBlocking                                            avgt    5    1011.665 ±   1.129  ms/op

DispatchersBenchmark.singleThreadSuspending                                         avgt    5    1007.943 ±   3.300  ms/op
DispatchersBenchmark.defaultSuspending                                              avgt    5    1008.120 ±   4.856  ms/op
DispatchersBenchmark.ioSuspending                                                   avgt    5    1008.363 ±   1.711  ms/op
DispatchersBenchmark.e100ThreadsSuspending                                          avgt    5    1007.365 ±   3.836  ms/op


Benchmark                                                                           Mode  Cnt       Score     Error  Units
ReflectionBenchmark.javaReflectionCall                                              avgt    5      18.214 ±   0.143  ms/op
ReflectionBenchmark.kotlinReflectionCall                                            avgt    5      28.400 ±   0.067  ms/op
ReflectionBenchmark.kotlinReflectionCallWithFinding                                 avgt    5     172.664 ±   8.176  ms/op
ReflectionBenchmark.regularCall                                                     avgt    5       1.950 ±   0.023  ms/op
ReflectionBenchmark.simpleKotlinReflectionCall                                      avgt    5       1.939 ±   0.008  ms/op


Benchmark                                            Mode  Cnt    Score   Error  Units
ReflectionBenchmark.atomicCounterCall                avgt    5   11.434 ± 0.271  ms/op
ReflectionBenchmark.creatingSomeObjects              avgt    5   10.470 ± 0.076  ms/op
ReflectionBenchmark.javaReflectionCall               avgt    5   18.760 ± 0.088  ms/op
ReflectionBenchmark.kotlinReflectionCall             avgt    5   27.156 ± 0.084  ms/op
ReflectionBenchmark.kotlinReflectionCallWithFinding  avgt    5  168.951 ± 0.618  ms/op
ReflectionBenchmark.nullableValueIncrement           avgt    5    2.656 ± 0.014  ms/op
ReflectionBenchmark.printingCounterCall              avgt    5   33.832 ± 3.976  ms/op
ReflectionBenchmark.rawValueIncrement                avgt    5    1.190 ± 0.003  ms/op
ReflectionBenchmark.regularCall                      avgt    5    1.933 ± 0.004  ms/op
ReflectionBenchmark.simpleKotlinReflectionCall       avgt    5    1.932 ± 0.008  ms/op
ReflectionBenchmark.suspendingCounterCall            avgt    5    8.702 ± 0.051  ms/op
ReflectionBenchmark.synchronizedCounterCall          avgt    5    6.126 ± 0.015  ms/op

Benchmark                                                                                 Mode  Cnt       Score        Error  Units

StateSynchronizationBenchmark.atomicTest                                            avgt    5     660.510 ±  50.737  ms/op
StateSynchronizationBenchmark.limitedDispatcherSwitchingTest                        avgt    5    4742.988 ± 224.226  ms/op
StateSynchronizationBenchmark.limitedDispatcherTest                                 avgt    5      20.037 ±   0.121  ms/op
StateSynchronizationBenchmark.mutexTest                                             avgt    5    1532.737 ±  17.109  ms/op
StateSynchronizationBenchmark.synchronizedTest                                      avgt    5     272.305 ±   8.458  ms/op

StateSynchronizationBenchmark.mutableConcurrentListTest                             avgt    5     528.268 ± 140.613  ms/op
StateSynchronizationBenchmark.mutableListLimitedDispatcherSwitchingTest             avgt    5    4975.630 ± 279.247  ms/op
StateSynchronizationBenchmark.mutableListLimitedDispatcherTest                      avgt    5     217.192 ±  12.758  ms/op
StateSynchronizationBenchmark.mutableListMutexTest                                  avgt    5    1906.811 ±  54.272  ms/op
StateSynchronizationBenchmark.mutableListSynchronizedTest                           avgt    5     458.582 ± 128.718  ms/op

SynchronizedInMemoryIntRepositoryBenchmark.concurrentListAddingCopyingTest                avgt    5     106.754 ±      0.592  ms/op
SynchronizedInMemoryIntRepositoryBenchmark.mutexAddingCopyingTest                         avgt    5     339.988 ±      1.706  ms/op
SynchronizedInMemoryIntRepositoryBenchmark.synchronizedAddingCopyingTest                  avgt    5     345.740 ±      1.695  ms/op
SynchronizedInMemoryIntRepositoryBenchmark.singleThreadDispatcherAddingCopyingTest        avgt    5     494.463 ±      2.548  ms/op

SynchronizedInMemoryIntRepositoryBenchmark.singleConcurrentListAddingCopyingTest          avgt    5  121029.155 ±    677.007  ms/op
SynchronizedInMemoryIntRepositoryBenchmark.singleMutexAddingCopyingTest                   avgt    5  409722.384 ±  67867.809  ms/op
SynchronizedInMemoryIntRepositoryBenchmark.singleSynchronizedAddingCopyingTest            avgt    5  407318.674 ± 106984.981  ms/op
SynchronizedInMemoryIntRepositoryBenchmark.singleSingleThreadDispatcherAddingCopyingTest  avgt    5  368568.158 ± 519639.495  ms/op

