package com.yolo.api.test.suite.utils;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class RequestHeader {
	
	public static Headers getHeaders() {
		
		Header h1 = new Header(Constants.contentType,Constants.contentType_Value);		
		Headers header = new Headers(h1);
		return header;
	}

}
