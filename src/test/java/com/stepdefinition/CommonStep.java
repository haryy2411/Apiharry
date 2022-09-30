package com.stepdefinition;


import org.junit.Assert;

import com.baseclass.BaseClass;

import cucumber.api.java.en.Then;

public class CommonStep extends BaseClass {
	@Then("User should verify the status code is {int}")
	public void userShouldVerifyTheStatusCodeIs(int int1) {
		int statusCode = getStatusCode(LoginStep.response);
		Assert.assertEquals("Verify firstname", int1, statusCode);
	}
}
