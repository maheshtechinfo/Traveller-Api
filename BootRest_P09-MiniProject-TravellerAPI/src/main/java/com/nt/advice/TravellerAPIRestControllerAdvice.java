package com.nt.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.exception.TravellerNotFoundException;

@RestControllerAdvice
public class TravellerAPIRestControllerAdvice {

	@ExceptionHandler(TravellerNotFoundException.class)
	public ResponseEntity<String> handlerTNFE(TravellerNotFoundException tnfe) {
		System.out.println("TravellerAPIRestControllerAdvice.handlerTNFE()");
		return new ResponseEntity<String>(tnfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerAllExceptions(Exception e) {
		System.out.println("TravellerAPIRestControllerAdvice.handlerAllExceptions()");
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
