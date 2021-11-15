package com.yolo.api.test.suite.invoker;

import java.io.IOException;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.yolo.api.test.suite.utils.BaseClass;

import io.restassured.response.Response;

public class GetTestCases extends BaseClass{
	
	private static final Logger log = LogManager.getLogger(GetTestCases.class);
	Response userList,postList,todoList,commentsList;
	
	
	@Test(groups = "yoloAPITest", priority = 4)
	public void getListOfUsers() throws JSONException, IOException {
		
		log.info(
				"*****************************************************************************************************");
		log.info("Get the list of all the available users");
		userList = userListRes();
		log.info("Validating the status code of the response");
		Assert.assertEquals(userList.statusCode(), 200);		
		
	}
	
	
	@Test(groups = "yoloAPITest", priority = 5)
	public void getListOfPosts() throws JSONException, IOException {
		
		log.info(
				"*****************************************************************************************************");
		log.info("Get the list of all the available posts");
		postList = postListRes();
		log.info("Validating the status code of the response");
		Assert.assertEquals(userList.statusCode(), 200);	
		
	}
	
	@Test(groups = "yoloAPITest", priority = 6)
	public void getListOfTodos() throws JSONException, IOException {
		
		log.info(
				"*****************************************************************************************************");
		log.info("Get the list of Todos of each user");
		todoList = todosListRes();
		log.info("Validating the status code of the response");
		Assert.assertEquals(userList.statusCode(), 200);	
		
	}
	
	@Test(groups = "yoloAPITest", priority = 7)
	public void getListOfComments() throws JSONException, IOException {
		
		log.info(
				"*****************************************************************************************************");
		log.info("Get the list of Todos of each user");
		commentsList = commentsListRes();
		log.info("Validating the status code of the response");
		Assert.assertEquals(userList.statusCode(), 200);	
		
	}

}
