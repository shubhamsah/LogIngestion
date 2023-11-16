package com.example.logsingestor.repository;

import com.example.logsingestor.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}
