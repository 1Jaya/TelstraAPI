package com.api.triangle.services;

import com.api.triangle.exceptions.CheckNumberException;

public interface TriangleService {

	public String getTriangleType(String a,String b,String c) throws CheckNumberException;
}
