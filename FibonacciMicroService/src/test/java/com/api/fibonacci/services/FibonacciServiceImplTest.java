package com.api.fibonacci.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FibonacciServiceImplTest {

	@InjectMocks
	FibonacciServiceImpl fibonacciService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private String num;

	@Test
	public void getFibonacciNumberTest() throws Exception {
		num="10";
		String fib=fibonacciService.getFibonacciNumber(num);
		assertEquals("55", fib);
	}
	
	@Test
	public void checkInputNumber() throws Exception {
		num="ab";
		thrown.expect(Exception.class);
		thrown.expectMessage(is("Please input only number"));
		fibonacciService.getFibonacciNumber(num);
	}
	
	@Test
	public void checkNegativeNumber() throws Exception {
		num="-10";
		thrown.expect(Exception.class);
		thrown.expectMessage(is("Number entered cannot be negative"));
		fibonacciService.getFibonacciNumber(num);
	}
	
}
