package com.yolo.api.test.suite.rest.assured.config;

import com.yolo.api.test.suite.utils.Constants;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfigurations {
	
	
	public RequestSpecification postRequestSpecification() {		
		RestAssured.baseURI = Constants.base_URL;
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	
	public RequestSpecification putRequestSpecification(Integer userCreationId) {		
		RestAssured.baseURI = Constants.base_URL+Constants.createNewUser+"/"+userCreationId;
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	

}
