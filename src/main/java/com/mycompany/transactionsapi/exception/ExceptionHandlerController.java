package com.mycompany.transactionsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundError(NotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ErrorResponse("Resource Not Found", ex.getMessage()));
    }

    public static class ErrorResponse{
        private final String message;

        private final String details;
      
        public ErrorResponse(String message, String details) {
          this.message = message;
          this.details = details;
        }
      
        public String getMessage() {
          return message;
        }
      
        public String getDetails() {
          return details;
        }
    }
    
}
