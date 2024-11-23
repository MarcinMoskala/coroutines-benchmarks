package me.champeau.jmh

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@Threads(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class SampleB {

    @Benchmark
    fun sampleOptimized(bh: Blackhole) {
        repeat(10000) {
            val a = Any()
        }
    }

    @Benchmark
    fun consumeNewObject(bh: Blackhole) {
        repeat(10_000) {
            val a = Any()
            bh.consume(a)
        }
    }

    @Benchmark
    fun consumeSameObject(bh: Blackhole) {
        val a = Any()
        repeat(10_000) {
            bh.consume(a)
        }
    }
    
    @Benchmark
    fun sampleA(bh: Blackhole) {
        repeat(10_000) {
            bh.consume("a")
        }
    }
    
    private val IS_VALID_EMAIL_REGEX by lazy { "\\A(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\z".toRegex() }
    
    fun String.isValidIpAddressOptimized(): Boolean =
       matches(IS_VALID_EMAIL_REGEX)
    
    @Benchmark
    fun measureIsValidIpAddressOptimized(bh: Blackhole) {
        var count = 0
        repeat(10_000) {
            if("$it.$it.$it.$it".isValidIpAddressOptimized()) {
                count++
            }
        }
        bh.consume(count)
    }
    
    fun String.isValidIpAddress(): Boolean =
       matches("\\A(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\z".toRegex())
    
    @Benchmark
    fun measureIsValidIpAddress(bh: Blackhole) {
        var count = 0
        repeat(10_000) {
            if("$it.$it.$it.$it".isValidIpAddress()) {
                count++
            }
        }
        bh.consume(count)
    }
    
}
