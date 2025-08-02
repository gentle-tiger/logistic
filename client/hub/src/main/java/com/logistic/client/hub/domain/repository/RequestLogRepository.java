package com.logistic.client.hub.domain.repository;

import com.logistic.client.hub.domain.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
}

