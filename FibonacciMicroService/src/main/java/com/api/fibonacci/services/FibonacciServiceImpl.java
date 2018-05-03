package com.api.fibonacci.services;


import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.api.fibonacci.exceptions.CheckNumberException;
import com.api.fibonacci.exceptions.NegativeNumberException;

@Service
public class FibonacciServiceImpl implements FibonacciService{

	@Override
	public String getFibonacciNumber(String num) throws NegativeNumberException, CheckNumberException{
		if(Pattern.matches("(-)*[0-9]+", num)==false) {
			throw new CheckNumberException("Please input only number");
		}
		int n=Integer.parseInt(num);
		
		if(n<0) {
			throw new NegativeNumberException("Number entered cannot be negative");
		}
		Integer fib=0;
		if(n==0) {
			fib=1;
			return String.valueOf(fib);
		}
		for(int i=1;i<=n;i++) {
			fib+=i;
		}
		System.out.println(String.valueOf(fib));
		return String.valueOf(fib);
	}
}
