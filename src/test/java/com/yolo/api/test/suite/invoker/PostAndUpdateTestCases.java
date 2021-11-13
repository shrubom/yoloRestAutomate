package com.yolo.api.test.suite.invoker;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.Test;

import com.yolo.api.test.suite.response.body.CreateNewUserResponse;
import com.yolo.api.test.suite.response.body.UpdateExistingUserResponse;
import com.yolo.api.test.suite.utils.BaseClass;

import junit.framework.Assert;

public class PostAndUpdateTestCases extends BaseClass{
	
	private static final Logger log = LogManager.getLogger(PostAndUpdateTestCases.class);
	public static int userCreationId;
	public String userNameReq = null, updatedUserEmailReq = null;
	public String userNameRes = null, userGender = null, userStatus = null, userEmail = null;
	HashSet<UpdateExistingUserResponse> userUpdateRes = null;
	HashSet<CreateNewUserResponse> newUserRes = null;
	
	//Create a new User
	@Test(groups = "yoloAPITest", priority = 0)
	public void postCreateUser() throws JSONException, IOException {
		log.info("*****************************************************************************************************");
		log.info("Creating a new user details");
		newUserRes = createNewUser();
		Iterator<CreateNewUserResponse> newUserIterator = newUserRes.iterator();
		while (newUserIterator.hasNext()) {
			CreateNewUserResponse userItem = newUserIterator.next();
			this.userCreationId = userItem.getId();
			this.userNameRes = userItem.getName();
			this.userEmail = userItem.getEmail();
			userNameReq = userNameRequestGen();
			//Validating the newely created user name
			log.info("Validating the newely created user name");
			Assert.assertEquals(userNameReq, userNameRes);
			log.info(userNameReq);
			log.info("*****************************************************************************************************");
		}		
	}
	
	//Update the user details

	@Test(groups = "yoloAPITest", priority = 1)
	public void putUpdateUser() throws IOException, JSONException {
		log.info("*****************************************************************************************************");
		log.info("Updating user details");
		userUpdateRes = updateExistingUserDetails(userCreationId,userNameRes);
		updatedUserEmailReq = userNameRequestGen();
		System.out.println(updatedUserEmail);
		System.out.println(userEmail);
		Iterator<UpdateExistingUserResponse> existingUserIterator = userUpdateRes.iterator();
//		while (existingUserIterator.hasNext()) {
//			UpdateExistingUserResponse oldUserItem = existingUserIterator.next();
//			
//		}
		//Validating the newely created user name
		log.info("Validating the updated email of an existing user");
		Assert.assertNotSame(updatedUserEmailReq!=userEmail, existingUserIterator);
		
		
	}
	
}
