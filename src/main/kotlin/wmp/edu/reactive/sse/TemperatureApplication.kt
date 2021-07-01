package wmp.edu.reactive.sse

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@EnableAsync
@SpringBootApplication
class TemperatureApplication : AsyncConfigurer {
    override fun getAsyncExecutor(): Executor? {
        val executorService = ThreadPoolTaskExecutor()
        executorService.corePoolSize = 5
        executorService.maxPoolSize = 10
        executorService.setQueueCapacity(10)
        executorService.setThreadNamePrefix("sse-")
        executorService.initialize()
        return executorService
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
        return SimpleAsyncUncaughtExceptionHandler()
    }
}

fun main(args: Array<String>) {
    runApplication<TemperatureApplication>(*args)
}

