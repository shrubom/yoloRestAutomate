package com.yolo.api.test.suite.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomNameGenerator {
	
public String randomAlphaGen() {
		
		String genratedAlphaString = RandomStringUtils.randomAlphabetic(6);
		
		return genratedAlphaString;
		
	}

}
