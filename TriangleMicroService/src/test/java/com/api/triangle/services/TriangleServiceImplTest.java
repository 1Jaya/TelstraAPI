package com.api.triangle.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.triangle.exceptions.CheckNumberException;

@RunWith(SpringRunner.class)
public class TriangleServiceImplTest {

	@InjectMocks
	TriangleServiceImpl triangleService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	
	private String a;
	private String b;
	private String c;
	
	@Test
	public void getFibonacciNumberTest() throws CheckNumberException {
		a="10";
		b="20";
		c="30";
		String triangleType=triangleService.getTriangleType(a, b, c);
		assertEquals("Scalene",triangleType);
	}
	
	@Test
	public void checkInputNumber() throws CheckNumberException  {
		a="ab";
		b="20";
		c="30";
		thrown.expect(Exception.class);
		thrown.expectMessage(is("Please input valid number"));
		triangleService.getTriangleType(a, b, c);
	}
}
