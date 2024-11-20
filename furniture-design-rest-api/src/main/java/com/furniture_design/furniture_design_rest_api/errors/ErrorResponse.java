package com.furniture_design.furniture_design_rest_api.errors;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
  private String message;
  private HttpStatus status;
}
