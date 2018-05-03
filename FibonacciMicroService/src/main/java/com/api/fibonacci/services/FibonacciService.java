package com.api.fibonacci.services;

import com.api.fibonacci.exceptions.CheckNumberException;
import com.api.fibonacci.exceptions.NegativeNumberException;

public interface FibonacciService {

	public String getFibonacciNumber(String num) throws NegativeNumberException, CheckNumberException;
}
