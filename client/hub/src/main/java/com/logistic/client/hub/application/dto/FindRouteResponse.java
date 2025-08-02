package com.logistic.client.hub.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindRouteResponse {

  private List<RouteStepResponse> route;
  private double totalDistanceKm;
  private double totalEstimatedTimeHours;


}