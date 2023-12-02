package com.dyte.logingestor.repository;

import java.util.List;
import com.dyte.logingestor.entity.Log;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogRepositoryInterface extends JpaRepository<Log, Long>{

}