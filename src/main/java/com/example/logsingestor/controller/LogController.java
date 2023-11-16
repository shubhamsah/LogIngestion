package com.example.logsingestor.controller;

import com.example.logsingestor.model.Log;
import com.example.logsingestor.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogRepository logRepository;

    @Autowired
    private TaskExecutor logTaskExecutor;

    @Autowired
    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestLog(@RequestBody Log log){
        try{
            logRepository.save(log);
            return new ResponseEntity<>("Log ingested sucessfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to ingest log", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Async("logTaskExecutor") // Mark method as asynchronous
    public CompletableFuture<Void> processLogs(List<Log> logs) {
        try {
            logRepository.saveAll(logs);
            // Additional processing if needed
        } catch (Exception e) {
            // Handle exception
        }
        return CompletableFuture.completedFuture(null);
    }

    @PostMapping("/async-ingest") // Endpoint for asynchronous log ingestion
    public ResponseEntity<String> ingestLogsAsync(@RequestBody List<Log> logs) {
        processLogs(logs); // Start asynchronous processing
        return new ResponseEntity<>("Logs ingestion started asynchronously", HttpStatus.ACCEPTED);
    }
}