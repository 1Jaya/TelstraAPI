package com.api.triangle.controllers;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.api.triangle.exceptions.CheckNumberException;
import com.api.triangle.services.TriangleServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value=TriangleController.class)
public class TriangleControllerTest {

	@MockBean
	private TriangleServiceImpl triangleService;
	
	@InjectMocks
	private TriangleController triangleController;
	


	@Autowired
	MockMvc mockmvc;

	private String a;
	private String b;
	private String c;

	@Test
	public void getTriangleTypeTest() throws Exception{
		Mockito.when(triangleService.getTriangleType(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn("Equilateral");
		a="1";
		b="1";
		c="1";
		this.mockmvc.perform(get("/api/TriangleType")
				.param("a",a)
				.param("b",b)
				.param("c",c)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("Equilateral"))
		;

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void checkTriangleSizeTest() throws Exception{
		a="ab";
		b="1";
		c="1";
		Mockito.when(triangleService.getTriangleType(a,b,c)).thenThrow(CheckNumberException.class);
	
		this.mockmvc.perform(get("/api/TriangleType")
				.param("a",a)
				.param("b",b)
				.param("c",c)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isBadRequest())
		;

	}
	
	@Test
	public void testParameters(){
		b="2";
		c="2";
		try {
		this.mockmvc.perform(get("/api/TriangleType")
				.param("b",b)
				.param("c",c)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		.andExpect(content().string("a parameter is missing"))
		.andDo(print())
		;
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void triangleCheckCacheHeaders() throws Exception{
		Mockito.when(triangleService.getTriangleType(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn("Equilateral");
		a="1";
		b="1";
		c="1";
		this.mockmvc.perform(get("/api/TriangleType")
				.param("a",a)
				.param("b",b)
				.param("c",c)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(header().stringValues("no-cache,no-store,must-revalidate,private,max-age=0"))
		.andExpect(header().string("Pragma", "no-cache"));

	}

}
