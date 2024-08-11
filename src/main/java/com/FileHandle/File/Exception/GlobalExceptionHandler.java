package com.FileHandle.File.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
		
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

		@ExceptionHandler(ApplicantNotFoundException.class)
		public ResponseEntity<ErrorStatus> applicantNotFoundException(ApplicantNotFoundException exception, WebRequest request ){
			
			ErrorStatus status = new ErrorStatus();
			status.setStatus(HttpStatus.NOT_FOUND);
			status.setMessage(exception.getLocalizedMessage());
			status.setDetails(request.getDescription(false));
			logger.info(exception.getLocalizedMessage());
			return new ResponseEntity<ErrorStatus>(status,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(ValidationViolationException.class)
		public ResponseEntity<ErrorStatus> validationException(ValidationViolationException exception, WebRequest request ){
			
			ErrorStatus status = new ErrorStatus();
			status.setStatus(HttpStatus.BAD_REQUEST);
			status.setMessage(exception.getLocalizedMessage());
			status.setDetails(request.getDescription(false));
			logger.info(exception.getLocalizedMessage());

			return new ResponseEntity<ErrorStatus>(status,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<ErrorStatus> handleGlobalException(Exception exception, WebRequest request ){
			
			ErrorStatus status = new ErrorStatus();
			status.setStatus(HttpStatus.BAD_REQUEST);
			status.setMessage(exception.getLocalizedMessage());
			status.setDetails(request.getDescription(false));
			logger.info(exception.getLocalizedMessage());
			return new ResponseEntity<ErrorStatus>(status,HttpStatus.NOT_FOUND);
		}
}
