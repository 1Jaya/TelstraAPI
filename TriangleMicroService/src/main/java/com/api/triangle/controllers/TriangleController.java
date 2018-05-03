package com.api.triangle.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.triangle.exceptions.CheckNumberException;
import com.api.triangle.services.TriangleServiceImpl;

@RestController
public class TriangleController {
	
	@Autowired
	TriangleServiceImpl triangeService;
	
	@GetMapping(value="/api/TriangleType")
	public ResponseEntity<String> getTriangleType(@RequestParam String a,@RequestParam String b,@RequestParam String c,HttpServletResponse response) throws CheckNumberException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma","no-cache");
	    response.setHeader("Expires", "-1");
		return new ResponseEntity<String>(triangeService.getTriangleType(a, b, c),HttpStatus.OK);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    return new ResponseEntity<String>(name + " parameter is missing",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CheckNumberException.class)
	public ResponseEntity<String> handleNumberException(Exception e){
		
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
