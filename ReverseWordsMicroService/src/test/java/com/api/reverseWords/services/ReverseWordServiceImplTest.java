package com.api.reverseWords.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ReverseWordServiceImplTest {

	@InjectMocks
	ReverseWordServiceImpl reverseWordService;
	
	private String sentence;

	@Before
	public void setup() {
		sentence="What is your name";
	}
	
	@Test
	public void getFibonacciNumberTest() {
		String newSentence=reverseWordService.reverseWord(sentence);
		assertEquals("tahW si ruoy eman",newSentence);
	}
}
