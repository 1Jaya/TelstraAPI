package com.api.makeArray.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.api.makeArray.exceptions.ArrayNotFoundException;
import com.api.makeArray.service.ArrayService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ArrayController.class)
public class ArrayControllerTest {

	@MockBean
	private ArrayService arrayService;

	@Autowired
	MockMvc mockmvc;


	private Map<String,Integer[]> arrayMap=new HashMap<>();

	@Autowired
	ObjectMapper objectMapper;

	String mockArrayJson="{\"Array1\":[1,2,3,4],\"Array2\":[3,4,5,6],\"Array3\":[6,1,3,11]}";


	@SuppressWarnings("unchecked")
	@Test
	public void makeOneArrayTest() throws Exception{
		Integer[] arr= {1,2,3,4,5,6,11};
		arrayMap.put("Array", arr);

		Mockito.when(arrayService.makeArray(Mockito.anyMap())).thenReturn(arrayMap);

		this.mockmvc.perform(post("/api/makeonearray")
				.accept(MediaType.APPLICATION_JSON)
				.content(mockArrayJson)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		;

	}

	@SuppressWarnings("unchecked")
	@Test
	public void makeOneArrayNotFound() throws Exception {
		String mockArrayJson="{\"Array1\":null,\"Array2\":[3,4,5,6],\"Array3\":[6,1,3,11]}";
		System.out.println(mockArrayJson);
		Mockito.when(arrayService.makeArray(Mockito.anyMap())).thenThrow(ArrayNotFoundException.class);

		this.mockmvc.perform(post("/api/makeonearray")
				.accept(MediaType.APPLICATION_JSON)
				.content(mockArrayJson)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isNotFound())
		;
	}


	@SuppressWarnings("unchecked")
	@Test
	public void makeOneArrayCheckCacheHeaders() throws Exception {
		Mockito.when(arrayService.makeArray(Mockito.anyMap())).thenReturn(arrayMap);

		this.mockmvc.perform(post("/api/makeonearray")
				.accept(MediaType.APPLICATION_JSON)
				.content(mockArrayJson)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(header().stringValues("no-cache,no-store,must-revalidate,private,max-age=0"))
		.andExpect(header().string("Pragma", "no-cache"));
	}

}
