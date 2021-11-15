package com.yolo.api.test.suite.invoker;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.Test;

import com.yolo.api.test.suite.response.body.AddingUserPostResponse;
import com.yolo.api.test.suite.response.body.AddingUserTodoListResponse;
import com.yolo.api.test.suite.response.body.CreateNewUserResponse;
import com.yolo.api.test.suite.response.body.UpdateExistingUserResponse;
import com.yolo.api.test.suite.utils.BaseClass;

import junit.framework.Assert;

public class PostAndUpdateTestCases extends BaseClass {

	private static final Logger log = LogManager.getLogger(PostAndUpdateTestCases.class);
	public static int userCreationId;
	public String userNameReq = null, updatedUserEmailReq = null, userPostTitleRequest = null;
	public String userNameRes = null, userGender = null, userStatus = null, userEmail = null, userPostTitle = null, userTodoTitle = null;
	HashSet<AddingUserPostResponse> addUserPostRes = null;
	HashSet<UpdateExistingUserResponse> userUpdateRes = null;
	HashSet<CreateNewUserResponse> newUserRes = null;
	HashSet<AddingUserTodoListResponse> addUserTodoRes = null;

	// Create a new User
	@Test(groups = "yoloAPITest", priority = 0)
	public void postCreateUser() throws JSONException, IOException {
		log.info(
				"*****************************************************************************************************");
		log.info("Creating a new user details");
		newUserRes = createNewUser();
		Iterator<CreateNewUserResponse> newUserIterator = newUserRes.iterator();
		while (newUserIterator.hasNext()) {
			CreateNewUserResponse userItem = newUserIterator.next();
			this.userCreationId = userItem.getId();
			this.userNameRes = userItem.getName();
			this.userEmail = userItem.getEmail();
			userNameReq = userNameRequestGen();
			// Validating the newely created user name
			log.info("Validating the newely created user name");
			Assert.assertEquals(userNameReq, userNameRes);
			
		}
	}

	// Update the user details

	@Test(groups = "yoloAPITest", priority = 1)
	public void putUpdateUser() throws IOException, JSONException {
		log.info(
				"*****************************************************************************************************");
		log.info("Updating user details");
		userUpdateRes = updateExistingUserDetails(userCreationId, userNameRes);
		updatedUserEmailReq = userNameRequestGen();
		Iterator<UpdateExistingUserResponse> existingUserIterator = userUpdateRes.iterator();
		// Validating the newely created user name
		log.info("Validating the updated email of an existing user");
		Assert.assertNotSame(updatedUserEmailReq != userEmail, existingUserIterator);
		
	}

	@Test(groups = "yoloAPITest", priority = 2)
	public void addUserPost() throws IOException, JSONException {
		log.info(
				"*****************************************************************************************************");
		log.info("Adding user Post details");
		addUserPostRes = addingUserPost(userCreationId);
		userPostTitleRequest = userPostTitleRequestGen();
		Iterator<AddingUserPostResponse> addUserPostIterator = addUserPostRes.iterator();
		while (addUserPostIterator.hasNext()) {
			AddingUserPostResponse userPostItem = addUserPostIterator.next();
			this.userPostTitle = userPostItem.getTitle();
			// Validating the newely created user name
			log.info("Validating the title of the user post");
			Assert.assertEquals(userPostTitleRequest, userPostTitle);
			
		}

	}
	
	@Test(groups = "yoloAPITest", priority = 3)
	public void addUserTodo() throws IOException, JSONException {
		log.info(
				"*****************************************************************************************************");
		log.info("Adding user Todo List details");
		addUserTodoRes = addingUserTodo(userCreationId);
		userTodoTitleRequest = userTodoTitleRequestGen();
		Iterator<AddingUserTodoListResponse> addUserTodoIterator = addUserTodoRes.iterator();
		while (addUserTodoIterator.hasNext()) {
			AddingUserTodoListResponse userTodoItem = addUserTodoIterator.next();
			this.userTodoTitle = userTodoItem.getTitle();
			// Validating the newely created user name
			log.info("Validating the title of the user Todo List");
			Assert.assertEquals(userTodoTitleRequest, userTodoTitle);
			
		}

	}
}
