package com.logistic.client.hub.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "request_log")
public class RequestLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID departHubId;
  private UUID arriveHubId;

  private LocalDateTime requestedAt = LocalDateTime.now();

  protected RequestLog() {}

  public RequestLog(UUID departHubId, UUID arriveHubId) {
    this.departHubId = departHubId;
    this.arriveHubId = arriveHubId;
  }

  // Getter 생략
}

