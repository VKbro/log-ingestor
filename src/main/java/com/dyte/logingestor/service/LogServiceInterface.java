package com.dyte.logingestor.service;

import com.dyte.logingestor.dto.LogDto;
import com.dyte.logingestor.dto.LogSearchDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LogServiceInterface {
    List<LogDto> getAllLogData();

    LogDto add(LogDto logDto);


    List<LogDto> search(LogSearchDto logSearchDto);
}