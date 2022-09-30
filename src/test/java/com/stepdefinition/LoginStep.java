package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.baseclass.BaseClass;
import com.endpoint.Endpoints;
import com.pojo.Login;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class LoginStep extends BaseClass {
	static String logtoken;
	static Response response;
	
	@Given("User add header")
	public void userAddHeader() {
		addHeader("accept", "application/json");
	}

	@Given("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() throws FileNotFoundException, IOException {

		basicauth(getPropertyFileValue("username"), getPropertyFileValue("password"));

	}

	@When("User send {string} request for login endpoint")
	public void userSendRequestForLoginEndpoint(String string) {
		 response = requestType(string, Endpoints.POSTMANBASICAUTHLOGIN);
		 String asPrettyString = response.asPrettyString();
		 System.out.println(asPrettyString);
	}

	@Then("User verify the login response body firstname present as {string} and get the logtoken")
	public void userVerifyTheLoginResponseBodyFirstnamePresentAsAndGetTheLogtoken(String string) {
		Login output = response.as(Login.class);
		logtoken = output.getData().getLogtoken();
		System.out.println(logtoken);
		String first_name = output.getData().getFirst_name();		
		Assert.assertEquals("Verify firstname", string, first_name);
	}

}
