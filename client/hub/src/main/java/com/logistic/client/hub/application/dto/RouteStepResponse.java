package com.logistic.client.hub.application.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteStepResponse {
  private Integer order;
  private UUID hubId;
  private String hubName;
  private double cumulativeDistanceKm;
  private double estimatedTimeHours;
}