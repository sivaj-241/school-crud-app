package com.school.student.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());

	        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  
	   @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", ex.getMessage());

	        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	    }

}
