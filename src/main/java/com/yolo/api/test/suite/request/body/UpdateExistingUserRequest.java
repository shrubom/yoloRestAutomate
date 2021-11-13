package com.yolo.api.test.suite.request.body;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UpdateExistingUserRequest {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	
	private String name;
	private String email;
	private String status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}