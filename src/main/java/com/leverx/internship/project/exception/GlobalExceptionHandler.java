package com.leverx.internship.project.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex) {
    ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND,
        String.format("Not found entity by id '%s'", ex.getMessage()));

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> constraintException(DataIntegrityViolationException ex) {
    ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST,
        String.format("Error because of constraint '%s'", ex.getMessage()));

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
