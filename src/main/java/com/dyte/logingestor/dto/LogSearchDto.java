package com.dyte.logingestor.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogSearchDto {
    @Size(min = 2)
    private String commit;

    @Size(min = 2)
    private String spanId;

    @Size(min = 2)
    private String level;

    @Size(min = 2)
    private String message;

    @Size(min = 2)
    private String traceId;

    @Size(min = 2)
    private String resourceId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestampTo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestampFrom;

    private Map<String, String> metadata;
}
