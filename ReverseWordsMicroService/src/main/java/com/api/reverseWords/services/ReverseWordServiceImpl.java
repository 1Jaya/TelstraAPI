package com.api.reverseWords.services;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class ReverseWordServiceImpl implements ReverseWordService{

	public String reverseWord(String sentence) {
		String[] words=sentence.split(" ");
		String newSentence="";
		for(String word:words) {
			if(word.length()==1) {
				newSentence+=word;
			}
			else {
				String reverseWord="";
				char[] w=word.toCharArray();
				for(int i=w.length-1;i>=0;i--) {
					reverseWord+=w[i];
				}
				newSentence+=reverseWord;
			}
			newSentence+=" ";
		}
		return newSentence.trim();
	}
	
	
}
