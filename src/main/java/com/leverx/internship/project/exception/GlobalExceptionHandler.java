package com.leverx.internship.project.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = EntityNotFoundException.class)
  public String entityNotFound(EntityNotFoundException e) {
      return e.toString();
  }
}
