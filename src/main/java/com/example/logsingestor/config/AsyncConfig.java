package com.example.logsingestor.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "logTaskExecutor")
    public TaskExecutor logTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // Set core pool size
        executor.setMaxPoolSize(20); // Set max pool size
        executor.setQueueCapacity(500); // Set queue capacity
        executor.setThreadNamePrefix("LogIngestionProcessor");
        executor.initialize();
        return executor;
    }
}

