package com.dyte.logingestor.controller;

import com.dyte.logingestor.dto.LogDto;
import com.dyte.logingestor.dto.LogSearchDto;
import com.dyte.logingestor.service.LogServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling log-related HTTP requests.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping({"", "/"})
public class LogController {

    private final LogServiceInterface logService;

    /**
     * Retrieves all logs.
     *
     * @return ResponseEntity containing a list of all log data.
     */
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<LogDto>> getAllLog() {
        var logData = logService.getAllLogData();
        return ResponseEntity.ok(logData);
    }

    /**
     * Creates a new log entry.
     *
     * @param logDto The log data to be created.
     * @return ResponseEntity containing the created log data.
     */
    @PostMapping(produces = {"application/json"})
    public ResponseEntity<LogDto> createLog(
            @Valid @RequestBody LogDto logDto
    ) {
        var createdLog = logService.add(logDto);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    /**
     * Searches for logs based on the provided search criteria.
     *
     * @param logSearchDto The criteria for log search.
     * @return ResponseEntity containing a list of filtered log data.
     */
    @PostMapping(path = "find", produces = {"application/json"})
    public ResponseEntity<List<LogDto>> search(
            @Valid @RequestBody LogSearchDto logSearchDto
    ) {
        var filteredLogs = logService.search(logSearchDto);
        return ResponseEntity.ok(filteredLogs);
    }
}
