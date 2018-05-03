package com.api.fibonacci.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.api.fibonacci.controllers.FibonacciController;
import com.api.fibonacci.exceptions.CheckNumberException;
import com.api.fibonacci.exceptions.NegativeNumberException;
import com.api.fibonacci.services.FibonacciService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=FibonacciController.class)
public class FibonacciControllerTest {

	@MockBean
	private FibonacciService fibonacciService;

	@Autowired
	MockMvc mockmvc;

	private String num;


	@Test
	public void getFibonacciNumberTest() throws Exception{
		num="10";
		Mockito.when(fibonacciService.getFibonacciNumber(num)).thenReturn("55");

		this.mockmvc.perform(get("/api/Fibonacci")
				.param("n",num)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("55"))
		;

	}

	@SuppressWarnings("unchecked")
	@Test
	public void checkNegativeNumber(){
		num="-1";
		try {
		Mockito.when(fibonacciService.getFibonacciNumber(num)).thenThrow(NegativeNumberException.class);
		this.mockmvc.perform(get("/api/Fibonacci")
				.param("n",num)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		.andDo(print())
		;
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void checkNumber(){
		num="abc";
		try {
		Mockito.when(fibonacciService.getFibonacciNumber(num)).thenThrow(CheckNumberException.class);
		this.mockmvc.perform(get("/api/Fibonacci")
				.param("n",num)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		.andDo(print())
		;
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testParameters(){
		num="abc";
		try {
		//Mockito.when(fibonacciService.getFibonacciNumber("")).thenThrow(MissingServletRequestParameterException.class);
		this.mockmvc.perform(get("/api/Fibonacci")
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		.andExpect(content().string("n parameter is missing"))
		.andDo(print())
		;
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void fibonacciCheckCacheHeaders() throws Exception{
		num="10";
		Mockito.when(fibonacciService.getFibonacciNumber(num)).thenReturn("55");

		this.mockmvc.perform(get("/api/Fibonacci")
				.param("n",num)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(header().stringValues("no-cache,no-store,must-revalidate,private,max-age=0"))
		.andExpect(header().string("Pragma", "no-cache"));

	}

}
