package com.yolo.api.test.suite.invoker;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.yolo.api.test.suite.utils.BaseClass;

import io.restassured.response.Response;

public class DeleteTestCases extends BaseClass{
	
	private static final Logger log = LogManager.getLogger(GetTestCases.class);
	Response deleteUser;
	
	@Test(groups = "yoloAPITest", priority = 8)
	public void deleteUser() throws JSONException, IOException {
		
		log.info(
				"*****************************************************************************************************");
		log.info("Delete a user by providing the user id");
		deleteUser = deleteUserRes(BaseClass.userCreationId);
		log.info("Validating the status code of the response");
		Assert.assertEquals(deleteUser.statusCode(), 204);	
		log.info(
				"*****************************************************************************************************");
		
	}

}
