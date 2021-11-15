package com.yolo.api.test.suite.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yolo.api.test.suite.request.body.AddingUserPostRequest;
import com.yolo.api.test.suite.request.body.AddingUserTodoListRequest;
import com.yolo.api.test.suite.request.body.CreateNewUserRequest;
import com.yolo.api.test.suite.request.body.UpdateExistingUserRequest;
import com.yolo.api.test.suite.response.body.AddingUserPostResponse;
import com.yolo.api.test.suite.response.body.AddingUserTodoListResponse;
import com.yolo.api.test.suite.response.body.CreateNewUserResponse;
import com.yolo.api.test.suite.response.body.UpdateExistingUserResponse;
import com.yolo.api.test.suite.rest.assured.config.RestAssuredConfigurations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	private static final Logger log = LogManager.getLogger(BaseClass.class);
	public static String userNameRequest = null, updatedUserEmail = null, userPostTitleRequest = null,
			userTodoTitleRequest = null;
	public static Response response = null;
	public static Integer userCreationId;
	RequestSpecification postReqSpec = new RestAssuredConfigurations().postRequestSpecification();

	public HashSet<CreateNewUserResponse> createNewUser() throws JSONException, IOException {
		String userName = null, userGender = null, userEmail = null, userStatus = null;
		RandomNameGenerator nameGenerator = new RandomNameGenerator();
		HashSet<CreateNewUserResponse> newUserHash = new HashSet();
		InputStream inputFile = getClass().getClassLoader().getResourceAsStream("object.properties");
		Properties prop = new Properties();
		prop.load(inputFile);
		userNameRequest = prop.getProperty("user.name") + nameGenerator.randomAlphaGen();

		CreateNewUserRequest newUserRequest = new CreateNewUserRequest();
		newUserRequest.setName(userNameRequest);
		newUserRequest.setGender(prop.getProperty("user.gender"));
		newUserRequest.setEmail(nameGenerator.randomAlphaGen() + prop.getProperty("user.email"));
		newUserRequest.setStatus(prop.getProperty("user.status"));
		RequestSpecification request = RestAssured.given().spec(postReqSpec);
		response = request.body(newUserRequest).when().post(Constants.createNewUser);
		String strResponse = response.asString();
		JSONObject jsonUserObj = new JSONObject(strResponse);

		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 201) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

		userCreationId = Integer.parseInt(jsonUserObj.getJSONObject("data").getString("id"));
		userName = jsonUserObj.getJSONObject("data").getString("name");
		userGender = jsonUserObj.getJSONObject("data").getString("gender");
		userEmail = jsonUserObj.getJSONObject("data").getString("email");

		// Setting the values of the parameters to the response class object
		CreateNewUserResponse userObj = new CreateNewUserResponse();
		userObj.setId(userCreationId);
		userObj.setName(userName);
		userObj.setEmail(userEmail);
		userObj.setGender(userGender);
		userObj.setStatus(userStatus);

		newUserHash.add(userObj);

		return newUserHash;

	}

	public HashSet<UpdateExistingUserResponse> updateExistingUserDetails(int userId, String existingUserName)
			throws IOException, JSONException {

		String updatedEmail = null, userStatus = null, userGender = null;
		HashSet<UpdateExistingUserResponse> existingUserHash = new HashSet();
		RequestSpecification putReqSpec = new RestAssuredConfigurations().putRequestSpecification(userCreationId);
		InputStream inputFile = getClass().getClassLoader().getResourceAsStream("object.properties");
		Properties prop = new Properties();
		prop.load(inputFile);
		updatedUserEmail = existingUserName.replaceAll("\\s+", "") + prop.getProperty("updated.user.email");

		UpdateExistingUserRequest existingUserReq = new UpdateExistingUserRequest();
		existingUserReq.setName(existingUserName);
		existingUserReq.setEmail(updatedUserEmail);
		existingUserReq.setStatus(prop.getProperty("user.status"));

		RequestSpecification request = RestAssured.given().spec(putReqSpec);
		response = request.body(existingUserReq).when().put();
		String strResponse = response.asString();
		JSONObject jsonUserObj = new JSONObject(strResponse);

		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

		userGender = jsonUserObj.getJSONObject("data").getString("gender");
		updatedEmail = jsonUserObj.getJSONObject("data").getString("email");
		userStatus = jsonUserObj.getJSONObject("data").getString("status");

		// Setting the values of the parameters to the response class object
		UpdateExistingUserResponse userObj = new UpdateExistingUserResponse();
		userObj.setName(existingUserName);
		userObj.setEmail(updatedEmail);
		userObj.setGender(userGender);
		userObj.setStatus(userStatus);

		existingUserHash.add(userObj);

		return existingUserHash;

	}

	public HashSet<AddingUserPostResponse> addingUserPost(int userCreationId) throws IOException, JSONException {

		int userPostId = 0;
		String postTitle = null, postBody = null;
		HashSet<AddingUserPostResponse> addUserPostHash = new HashSet();
		RequestSpecification addReqSpec = new RestAssuredConfigurations().addPostRequestSpecification(userCreationId);
		InputStream inputFile = getClass().getClassLoader().getResourceAsStream("object.properties");
		Properties prop = new Properties();
		prop.load(inputFile);

		userPostTitleRequest = prop.getProperty("user.post.title");

		AddingUserPostRequest addUserPost = new AddingUserPostRequest();
		addUserPost.setTitle(userPostTitleRequest);
		addUserPost.setBody(prop.getProperty("user.post.body"));

		RequestSpecification request = RestAssured.given().spec(addReqSpec);
		response = request.body(addUserPost).when().post();
		String strResponse = response.asString();
		JSONObject jsonUserObj = new JSONObject(strResponse);

		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 201) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

		userPostId = Integer.parseInt(jsonUserObj.getJSONObject("data").getString("id"));
		postTitle = jsonUserObj.getJSONObject("data").getString("title");
		postBody = jsonUserObj.getJSONObject("data").getString("body");

		AddingUserPostResponse addingUserPostResponse = new AddingUserPostResponse();
		addingUserPostResponse.setId(userPostId);
		addingUserPostResponse.setTitle(postTitle);
		addingUserPostResponse.setBody(postBody);

		addUserPostHash.add(addingUserPostResponse);

		return addUserPostHash;

	}

	public HashSet<AddingUserTodoListResponse> addingUserTodo(int userCreationId) throws IOException, JSONException {

		int userTodoId = 0;
		String todoTitle = null, todoStatus = null, todoDueOn = null;
		HashSet<AddingUserTodoListResponse> addUserTodoHash = new HashSet();
		RequestSpecification addReqSpec = new RestAssuredConfigurations().addTodoRequestSpecification(userCreationId);
		InputStream inputFile = getClass().getClassLoader().getResourceAsStream("object.properties");
		Properties prop = new Properties();
		prop.load(inputFile);

		userTodoTitleRequest = prop.getProperty("user.todo.title");

		AddingUserTodoListRequest addUserTodo = new AddingUserTodoListRequest();
		addUserTodo.setTitle(userTodoTitleRequest);
		addUserTodo.setStatus(prop.getProperty("user.todo.status.complete"));

		RequestSpecification request = RestAssured.given().spec(addReqSpec);
		response = request.body(addUserTodo).when().post();
		String strResponse = response.asString();
		JSONObject jsonUserObj = new JSONObject(strResponse);

		log.info("Validating the status code of the response");
		int statusCode = response.getStatusCode();
		if (statusCode == 201) {
			log.info("Status Code :" + statusCode);
		} else {
			log.error("Error Message :" + strResponse);
		}

		userTodoId = Integer.parseInt(jsonUserObj.getJSONObject("data").getString("id"));
		todoTitle = jsonUserObj.getJSONObject("data").getString("title");
		todoDueOn = jsonUserObj.getJSONObject("data").getString("due_on");
		todoStatus = jsonUserObj.getJSONObject("data").getString("status");

		AddingUserTodoListResponse addingUserTodoResponse = new AddingUserTodoListResponse();
		addingUserTodoResponse.setId(userTodoId);
		addingUserTodoResponse.setTitle(todoTitle);
		addingUserTodoResponse.setDue_on(todoDueOn);
		addingUserTodoResponse.setStatus(todoStatus);

		addUserTodoHash.add(addingUserTodoResponse);

		return addUserTodoHash;

	}

	public Response userListRes() {
		RequestSpecification getReqSpec = new RestAssuredConfigurations().getUserListRequestSpecification();
		RequestSpecification request = RestAssured.given().spec(getReqSpec);
		return response = request.when().get();

	}
	
	public Response postListRes() {
		RequestSpecification getReqSpec = new RestAssuredConfigurations().getPostListRequestSpecification();
		RequestSpecification request = RestAssured.given().spec(getReqSpec);
		return response = request.when().get();

	}
	
	public Response todosListRes() {
		RequestSpecification getReqSpec = new RestAssuredConfigurations().getTodoListRequestSpecification();
		RequestSpecification request = RestAssured.given().spec(getReqSpec);
		return response = request.when().get();

	}
	
	public Response commentsListRes() {
		RequestSpecification getReqSpec = new RestAssuredConfigurations().getCommentsListRequestSpecification();
		RequestSpecification request = RestAssured.given().spec(getReqSpec);
		return response = request.when().get();

	}
	
	public Response deleteUserRes(Integer userCreationId) {
		RequestSpecification getReqSpec = new RestAssuredConfigurations().deleteUserRequestSpecification(userCreationId);
		RequestSpecification request = RestAssured.given().spec(getReqSpec);
		return response = request.when().delete();

	}

	public String userNameRequestGen() {
		return userNameRequest;
	}

	public String updatedUserEmailRequestGen() {
		return updatedUserEmail;
	}

	public String userPostTitleRequestGen() {
		return userPostTitleRequest;
	}

	public String userTodoTitleRequestGen() {
		return userTodoTitleRequest;
	}

}
