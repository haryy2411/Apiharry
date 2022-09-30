package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.baseclass.BaseClass;
import com.endpoint.Endpoints;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_output_pojo;
import com.pojo.Delete_Output_pojo;
import com.pojo.Delete_input_pojo;
import com.pojo.GetAddress_Output_Pojo;
import com.pojo.UpdateAddress_input__pojo;
import com.pojo.UpdateAddress_output_pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddressStep extends BaseClass {

	static String value;
	Response response;

	@Given("User should add header and bearer authorization for accessing address endpoints")
	public void userShouldAddHeaderAndBearerAuthorizationForAccessingAddressEndpoints() {
		String logtoken = LoginStep.logtoken;
System.out.println(logtoken);
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + LoginStep.logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

	}

	@When("User should add request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string}and{string}")
	public void userShouldAddRequestBodyForAddNewAddressAnd(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {

		Integer state1 = Integer.valueOf(state);
		Integer city1 = Integer.valueOf(city);
		Integer country1 = Integer.valueOf(country);

		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo(first_name, last_name, mobile,
				apartment, state1, city1, country1, zipcode, address, address_type);
		addBody(addAddress_Input_Pojo);
	}

	@When("User should send {string} request for add new address")
	public void userShouldSendRequestForAddNewAddress(String POST) {
		response = requestType(POST, Endpoints.ADDADRESS);
	}

	@Then("User should verify the created address response message matches {string}")
	public void userShouldVerifyTheCreatedAddressResponseMessageMatches(String successfullMessage) {
		AddAddress_output_pojo addAddress_output_pojo = response.as(AddAddress_output_pojo.class);
		int address_id = addAddress_output_pojo.getAddress_id();
		value = String.valueOf(address_id);
		System.out.println("address------"+value);
		Object message = addAddress_output_pojo.getMessage();
		Assert.assertEquals("Verify", successfullMessage, message);
	}

	@When("User should add request body for add update address {string},{string},{string},{string},{string},{string},{string},{string},{string}and{string}")
	public void userShouldAddRequestBodyForAddUpdateAddressAnd(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {

		Integer state1 = Integer.valueOf(state);
		Integer city1 = Integer.valueOf(city);
		Integer country1 = Integer.valueOf(country);

		UpdateAddress_input__pojo updateAddress_input__pojo = new UpdateAddress_input__pojo(value, first_name,
				last_name, mobile, apartment, state1, city1, country1, zipcode, address, address_type);
		addBody(updateAddress_input__pojo);

	}

	@When("User should send {string} request for add update address")
	public void userShouldSendRequestForAddUpdateAddress(String put) {
		response = requestType(put, Endpoints.UPDATEDRESS);
	}

	@Then("User should verify the update address response message matches {string}")
	public void userShouldVerifyTheUpdateAddressResponseMessageMatches(String UpdatesuccessfullMessage) {
		UpdateAddress_output_pojo updateAddress_output_pojo = response.as(UpdateAddress_output_pojo.class);
		String message = updateAddress_output_pojo.getMessage();
		Assert.assertEquals("Verify", UpdatesuccessfullMessage, message);

	}

	@When("User should add request body for delete address address_id")
	public void userShouldAddRequestBodyForDeleteAddressAddress_id() {
		Delete_input_pojo delete_input_pojo = new Delete_input_pojo(value);
		addBody(delete_input_pojo);
	}

	@When("User should send {string} request for delete address")
	public void userShouldSendRequestForDeleteAddress(String delete) {
		response = requestType(delete, Endpoints.DELETEADDRESS);
	}

	@Then("User should verify the delete address response message matches {string}")
	public void userShouldVerifyTheDeleteAddressResponseMessageMatches(String successfullMessage) {
		Delete_Output_pojo delete_Output_pojo = response.as(Delete_Output_pojo.class);
		String message = delete_Output_pojo.getMessage();
		Assert.assertEquals("Verify", successfullMessage, message);
	}

	@When("User should send {string} request for get address")
	public void userShouldSendRequestForGetAddress(String get) {
		response = requestType(get, Endpoints.GETADDRESS);
	}

	@Then("User should verify the get address response message matches {string}")
	public void userShouldVerifyTheGetAddressResponseMessageMatches(String successfullMessage) {
		GetAddress_Output_Pojo getAddress_Output_Pojo = response.as(GetAddress_Output_Pojo.class);
		String message = getAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify", successfullMessage, message);
	}

}
