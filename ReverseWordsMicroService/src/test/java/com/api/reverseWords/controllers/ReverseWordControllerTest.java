package com.api.reverseWords.controllers;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.api.reverseWords.controllers.ReverseWordController;
import com.api.reverseWords.services.ReverseWordService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ReverseWordController.class)
public class ReverseWordControllerTest {

	@MockBean
	private ReverseWordService reverseWordService;
	
	@Autowired
	MockMvc mockmvc;

	private String sentence;

	@Before
	public void setup() {
		sentence="How are you";
	}

	@Test
	public void getTriangleTypeTest() throws Exception{
		Mockito.when(reverseWordService.reverseWord(Mockito.anyString())).thenReturn("woH era uoy");
	
		this.mockmvc.perform(get("/api/ReverseWords")
				.param("sentence",sentence)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("woH era uoy"))
		;

	}
	
	@Test
	public void testParameters(){
		try {
		this.mockmvc.perform(get("/api/ReverseWords")
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		.andExpect(content().string("sentence parameter is missing"))
		.andDo(print())
		;
		}
		catch(Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void reverseWordCheckCacheHeaders() throws Exception{
		Mockito.when(reverseWordService.reverseWord(Mockito.anyString())).thenReturn("woH era uoy");
		
		this.mockmvc.perform(get("/api/ReverseWords")
				.param("sentence",sentence)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(header().stringValues("no-cache,no-store,must-revalidate,private,max-age=0"))
		.andExpect(header().string("Pragma", "no-cache"));

	}
}
