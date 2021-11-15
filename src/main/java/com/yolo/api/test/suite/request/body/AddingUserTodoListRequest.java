package com.yolo.api.test.suite.request.body;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AddingUserTodoListRequest {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	
	private String title;
	private String status;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
