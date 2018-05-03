package com.api.reverseWords.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.reverseWords.services.ReverseWordService;

@RestController
public class ReverseWordController {
	
	@Autowired
	ReverseWordService reverseWordservice;
	
	@GetMapping(value="/api/ReverseWords")
	public ResponseEntity<String> reverseWords(@RequestParam String sentence,HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma","no-cache");
	    response.setHeader("Expires", "-1");
		return new ResponseEntity<String>(reverseWordservice.reverseWord(sentence),HttpStatus.OK);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    return new ResponseEntity<String>(name + " parameter is missing",HttpStatus.BAD_REQUEST);
	}

}
