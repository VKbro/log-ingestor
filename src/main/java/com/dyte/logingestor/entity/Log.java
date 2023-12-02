package com.dyte.logingestor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String commit;
    private String spanId;
    private String message;
    private String traceId;
    private String resourceId;
    private LocalDateTime timestamp;

    private String level;

    @ElementCollection
    private Map<String, String> metadata;
}
