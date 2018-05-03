package com.api.makeArray.service;

import java.util.Map;

import com.api.makeArray.exceptions.ArrayNotFoundException;

public interface ArrayService {

	public Map<String,Integer[]> makeArray(Map<String,Integer[]> arrayMap) throws ArrayNotFoundException;
}
