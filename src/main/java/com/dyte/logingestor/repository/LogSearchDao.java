package com.dyte.logingestor.repository;

import com.dyte.logingestor.dto.LogSearchDto;
import com.dyte.logingestor.entity.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for searching and filtering logs.
 */
@Repository
@RequiredArgsConstructor
public class LogSearchDao {

    private final EntityManager entityManager;

    /**
     * Filters logs based on the provided search criteria.
     *
     * @param logSearchDto The criteria for log search.
     * @return List of filtered logs.
     */
    public List<Log> filterLogByPredicate(LogSearchDto logSearchDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Log> criteriaQuery = criteriaBuilder.createQuery(Log.class);
        Root<Log> root = criteriaQuery.from(Log.class);

        // Create a list to hold the predicates
        List<Predicate> predicates = new ArrayList<>();

        // Extract search criteria
        String commit = logSearchDto.getCommit();
        String traceId = logSearchDto.getTraceId();
        String resourceId = logSearchDto.getResourceId();
        String spanId = logSearchDto.getSpanId();
        String message = logSearchDto.getMessage();
        String level = logSearchDto.getLevel();
        LocalDateTime from = logSearchDto.getTimestampFrom();
        LocalDateTime to = logSearchDto.getTimestampTo();

        // Add predicates based on the provided search criteria
        if (commit != null) {
            predicates.add(criteriaBuilder.like(root.get("commit"), "%" + commit + "%"));
        }
        if (traceId != null) {
            predicates.add(criteriaBuilder.like(root.get("traceId"), "%" + traceId + "%"));
        }
        if (resourceId != null) {
            predicates.add(criteriaBuilder.like(root.get("resourceId"), "%" + resourceId + "%"));
        }
        if (spanId != null) {
            predicates.add(criteriaBuilder.like(root.get("spanId"), "%" + spanId + "%"));
        }
        if (message != null) {
            predicates.add(criteriaBuilder.like(root.get("message"), "%" + message + "%"));
        }
        if (from != null) {
            if (to == null) {
                to = LocalDateTime.now();
            }
            predicates.add(criteriaBuilder.between(root.get("timestamp"), from, to));
        }
        if (to != null) {
            if (from == null) {
                from = LocalDateTime.MIN;
            }
            predicates.add(criteriaBuilder.between(root.get("timestamp"), from, to));
        }
        if (level != null) {
            predicates.add(criteriaBuilder.like(root.get("level"), "%" + level + "%"));
        }

        // Combine all predicates with AND
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Execute the query
        TypedQuery<Log> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
