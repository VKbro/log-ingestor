package com.dyte.logingestor.service;

import com.dyte.logingestor.dto.LogDto;
import com.dyte.logingestor.dto.LogSearchDto;
import com.dyte.logingestor.repository.LogRepositoryInterface;
import com.dyte.logingestor.repository.LogSearchDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling log-related operations.
 */
@Service
@RequiredArgsConstructor
public class LogService implements LogServiceInterface {

    private final LogSearchDao logSearchDao;
    private final LogRepositoryInterface logRepository;

    /**
     * Retrieves all log data.
     *
     * @return List of log data.
     */
    @Override
    public List<LogDto> getAllLogData() {
        return logRepository.findAll().stream().map(LogDto::toDto).toList();
    }

    /**
     * Adds a new log entry.
     *
     * @param logDto The log data to be added.
     * @return The added log data.
     */
    @Override
    public LogDto add(LogDto logDto) {
        var entity = logDto.toEntity();
        logRepository.saveAndFlush(entity);
        return logDto;
    }

    /**
     * Searches for logs based on the provided search criteria.
     *
     * @param logSearchDto The criteria for log search.
     * @return List of filtered log data.
     */
    @Override
    public List<LogDto> search(LogSearchDto logSearchDto) {
        return logSearchDao.filterLogByPredicate(logSearchDto).stream().map(LogDto::toDto).toList();
    }

}
