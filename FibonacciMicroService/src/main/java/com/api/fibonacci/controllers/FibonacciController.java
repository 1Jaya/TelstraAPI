package com.api.fibonacci.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.fibonacci.exceptions.CheckNumberException;
import com.api.fibonacci.exceptions.NegativeNumberException;
import com.api.fibonacci.services.FibonacciService;

@RestController
public class FibonacciController {
	
	@Autowired
	FibonacciService fibonacciService;
	
	@GetMapping(value="/api/Fibonacci")
	public ResponseEntity<String> printFibonacci(@RequestParam String n,HttpServletResponse response) throws Exception {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma","no-cache");
	    response.setHeader("Expires", "-1");
		return new ResponseEntity<String>(fibonacciService.getFibonacciNumber(n),HttpStatus.OK);
	}
	
	@ExceptionHandler(NegativeNumberException.class)
	public ResponseEntity<String> throwNegativeNumberException(Exception e){
		
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CheckNumberException.class)
	public ResponseEntity<String> throwNumberException(Exception e){
		
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    return new ResponseEntity<String>(name + " parameter is missing",HttpStatus.BAD_REQUEST);
	}

}
