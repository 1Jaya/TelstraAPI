package com.api.makeArray.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.makeArray.exceptions.ArrayNotFoundException;

@RunWith(SpringRunner.class)
public class ArrayServiceImplTest {

	@InjectMocks
	ArrayServiceImpl arrayService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	Map<String,Integer[]> mockArrayMap=new HashMap<>();

	private Map<String,Integer[]> arrayMap=new HashMap<>();
	
	Integer[] arr= {1,2,3,4,5,6,11};
	
	@Before
	public void setup() {
		Integer[] arr1= {1,2,3,4};
		Integer[] arr2= {3,4,5,6};
		Integer[] arr3= {6,1,3,11};
		mockArrayMap.put("Array1", arr1);
		mockArrayMap.put("Array2", arr2);
		mockArrayMap.put("Array3", arr3);
		
	}
	
	@Test
	public void getFibonacciNumberTest() throws ArrayNotFoundException {
		Map<String,Integer[]> newArrayMap=arrayService.makeArray(mockArrayMap);
		Set<String> keys=newArrayMap.keySet();
		Integer[] newArr = null;
		for(String key:keys) {
			newArr=newArrayMap.get(key);
		}
		assertArrayEquals(arr, newArr);
	}
	
	@Test
	public void getOneArray_notFound() throws ArrayNotFoundException {
		Integer[] arr1= null;
		Integer[] arr2= {3,4,5,6};
		Integer[] arr3= {6,1,3,11};
		mockArrayMap.put("Array1", arr1);
		mockArrayMap.put("Array2", arr2);
		mockArrayMap.put("Array3", arr3);
		thrown.expect(ArrayNotFoundException.class);
		thrown.expectMessage(is("Array not found"));
		arrayService.makeArray(mockArrayMap);
	}
}
