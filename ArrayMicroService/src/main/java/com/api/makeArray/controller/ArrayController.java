package com.api.makeArray.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.makeArray.exceptions.ArrayNotFoundException;
import com.api.makeArray.service.ArrayService;

@RestController
public class ArrayController {
	
	@Autowired
	ArrayService arrayService;
	
	@PostMapping(value="/api/makeonearray",produces="application/json")
	public ResponseEntity<Map<String,Integer[]>> printFibonacci(@RequestBody Map<String,Integer[]> arrayMap,final HttpServletResponse response) throws ArrayNotFoundException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma","no-cache");
	    response.setHeader("Expires", "-1");
		return new ResponseEntity<>(arrayService.makeArray(arrayMap),HttpStatus.OK);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    return new ResponseEntity<String>(name + " parameter is missing",HttpStatus.BAD_REQUEST);
	}
	
	 
	@ExceptionHandler(ArrayNotFoundException.class)
	private ResponseEntity<String> arrayNotFound(ArrayNotFoundException ex){
		return new ResponseEntity<>("Array not found",HttpStatus.NOT_FOUND);
	}
	

}
