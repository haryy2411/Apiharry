package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.baseclass.BaseClass;
import com.endpoint.Endpoints;
import com.pojo.ChangeProfilePic;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;

public class ChangeProfilePicStep extends BaseClass {
	@Given("User add header and bearer authorization and multipart\\/form-data for accessing changeprofilepic endpoints")
	public void userAddHeaderAndBearerAuthorizationAndMultipartFormDataForAccessingChangeprofilepicEndpoints() throws FileNotFoundException, IOException {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + LoginStep.logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);
		formdata("file");
	
	}

	@When("User send {string} request for changeprofilepic")
	public void userSendRequestForChangeprofilepic(String string) {
		response = requestType("POST", Endpoints.CHANGEPROFILEPIC);
	
	}

	@Then("User verify the delete address response message matches {string}")
	public void userVerifyTheDeleteAddressResponseMessageMatches(String successfullMessage) {
		ChangeProfilePic changeProfilePic = response.as(ChangeProfilePic.class);
		String message = changeProfilePic.getMessage();
		Assert.assertEquals("Verify", successfullMessage, message);
	}


}
