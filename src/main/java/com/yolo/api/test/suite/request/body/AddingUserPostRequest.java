package com.yolo.api.test.suite.request.body;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AddingUserPostRequest {

	@JsonInclude(JsonInclude.Include.NON_NULL)

	private String title;
	private String body;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
