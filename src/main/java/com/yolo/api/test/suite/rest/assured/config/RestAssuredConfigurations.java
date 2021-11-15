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
	
	public RequestSpecification addPostRequestSpecification(Integer userCreationId) {		
		RestAssured.baseURI = Constants.base_URL+Constants.createNewUser+"/"+userCreationId+"/posts";
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	
	public RequestSpecification addTodoRequestSpecification(Integer userCreationId) {		
		RestAssured.baseURI = Constants.base_URL+Constants.createNewUser+"/"+userCreationId+"/todos";
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	
	public RequestSpecification getUserListRequestSpecification() {		
		RestAssured.baseURI = Constants.base_URL+Constants.createNewUser;
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	
	public RequestSpecification getPostListRequestSpecification() {		
		RestAssured.baseURI = Constants.base_URL+Constants.getListOfPosts;
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	
	public RequestSpecification getTodoListRequestSpecification() {		
		RestAssured.baseURI = Constants.base_URL+Constants.getListOfTodos;
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	
	public RequestSpecification getCommentsListRequestSpecification() {		
		RestAssured.baseURI = Constants.base_URL+Constants.getListOfComments;
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	
	public RequestSpecification deleteUserRequestSpecification(Integer userCreationId) {		
		RestAssured.baseURI = Constants.base_URL+Constants.createNewUser+"/"+userCreationId;
		return RestAssured.given().queryParam("access-token",Constants.goRest_Access_Token).header(Constants.contentType,Constants.contentType_Value);
	}
	
	
	

}
