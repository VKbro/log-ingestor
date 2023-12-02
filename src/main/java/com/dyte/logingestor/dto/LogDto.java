package com.dyte.logingestor.dto;

import com.dyte.logingestor.entity.Log;
import jakarta.validation.constraints.NotBlank;
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
public class LogDto {
    @NotBlank(message = "Commit cannot be empty")
    private String commit;

    @NotBlank(message = "Span Id should be a valid identifier")
    private String spanId;

    @NotBlank(message = "Log level cannot be blank")
    private String level;

    @NotBlank(message = "Message cannot be empty")
    private String message;

    @NotBlank(message = "Trace Id should be a valid string")
    private String traceId;

    @NotBlank(message = "Resource Id should be a valid string")
    private String resourceId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timestamp;

    private Map<String, String> metadata;

    public static LogDto toDto(Log log) {
        return LogDto.builder()
                .commit(log.getCommit())
                .message(log.getMessage())
                .level(log.getLevel())
                .spanId(log.getSpanId())
                .metadata(log.getMetadata())
                .resourceId(log.getResourceId())
                .timestamp(log.getTimestamp())
                .traceId(log.getTraceId())
                .build();
    }

    public Log toEntity() {
        return Log.builder()
                .commit(this.getCommit())
                .message(this.getMessage())
                .level(this.getLevel())
                .spanId(this.getSpanId())
                .metadata(this.getMetadata())
                .resourceId(this.getResourceId())
                .timestamp(this.getTimestamp())
                .traceId(this.getTraceId())
                .build();
    }
}
