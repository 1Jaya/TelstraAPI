package com.api.makeArray.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.springframework.stereotype.Service;

import com.api.makeArray.exceptions.ArrayNotFoundException;

@Service
public class ArrayServiceImpl implements ArrayService{

	public Map<String,Integer[]> makeArray(Map<String,Integer[]> arrayMap) throws ArrayNotFoundException{
		Map<String,Integer[]> newArrayMap=new HashMap<>();
		Set<String> keyset=arrayMap.keySet();
		Set<Integer> intList=new ConcurrentSkipListSet<>();
		for(String key:keyset) {
			if(arrayMap.get(key)==null) {
				throw new ArrayNotFoundException("Array not found");
			}
			Integer[] arr=arrayMap.get(key);
			for(int i=0;i<arr.length;i++) {
				intList.add(arr[i]);
			}
		}
		Integer[] newArr=intList.toArray(new Integer[0]);
		newArrayMap.put("Array",newArr );
		return newArrayMap;
	}
}
