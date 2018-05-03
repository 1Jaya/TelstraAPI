package com.api.triangle.services;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.api.triangle.exceptions.CheckNumberException;

@Service
public class TriangleServiceImpl implements TriangleService{

	public String getTriangleType(String a,String b,String c) throws CheckNumberException {
		String type="";
		if(Pattern.matches("(-)*[0-9]+", a)==false||Pattern.matches("(-)*[0-9]+", b)==false||Pattern.matches("(-)*[0-9]+", c)==false) {
			throw new CheckNumberException("Please input valid number");
		}
		int num1=Integer.parseInt(a);
		int num2=Integer.parseInt(b);
		int num3=Integer.parseInt(c);
		if(num1==num2&&num2==num3) {
			type="Equilateral";
		}
		else if(num1==num2 || num2==num3 || num1==num3) {
			type="Isosceles";
		}
		else {
			type="Scalene";
		}
		return type;
	}
}
