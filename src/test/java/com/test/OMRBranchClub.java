package com.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;
import com.endpoint.Endpoints;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_output_pojo;
import com.pojo.ChangeProfilePic;
import com.pojo.Delete_Output_pojo;
import com.pojo.Delete_input_pojo;
import com.pojo.GetAddress_Output_Pojo;
import com.pojo.Login;
import com.pojo.UpdateAddress_input__pojo;
import com.pojo.UpdateAddress_output_pojo;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class OMRBranchClub extends BaseClass {
	String logtoken;
	String value;

	@Test
	public void Login_output() throws FileNotFoundException, IOException {
		addHeader("accept", "application/json");
		basicauth(getPropertyFileValue("username"), getPropertyFileValue("password"));
		Response response = requestType("POST", Endpoints.POSTMANBASICAUTHLOGIN);

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Login output = response.as(Login.class);
		logtoken = output.getData().getLogtoken();
		String first_name = output.getData().getFirst_name();
		System.out.println(first_name);

		Assert.assertEquals(statusCode, 200, "verify status");
		Assert.assertEquals(first_name, "Hareesh", "verify firstname");
	}

	@Test(priority = 1)
	public void addAddress() {

		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo("Hareesh", "kumar", "9789939905",
				"WhiteHOuse", 33, 3378, 101, "600044", "New York Nagaram", "Vacation");
		addBody(addAddress_Input_Pojo);

		Response response = requestType("POST", Endpoints.ADDADRESS);

		int statusCode1 = getStatusCode(response);
		System.out.println(statusCode1);

		AddAddress_output_pojo addAddress_output_pojo = response.as(AddAddress_output_pojo.class);
		int address_id = addAddress_output_pojo.getAddress_id();
		value = String.valueOf(address_id);
		System.out.println("address_id" + value);
		Object message = addAddress_output_pojo.getMessage();
		Assert.assertEquals(statusCode1, 200, "verify response");
		Assert.assertEquals(message, "Address added successfully", "verify message");
	}

	@Test(priority = 2)
	public void updateaddress() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

		UpdateAddress_input__pojo updateAddress_input__pojo = new UpdateAddress_input__pojo(value, "Hareesh", "kumar",
				"8549939905", "BlackHouse", 33, 3378, 101, "600084", "Brooklyn New York Nagaram", "Vacation");
		addBody(updateAddress_input__pojo);

		Response response = requestType("PUT", Endpoints.UPDATEDRESS);

		int statusCode1 = getStatusCode(response);
		System.out.println(statusCode1);
		UpdateAddress_output_pojo updateAddress_output_pojo = response.as(UpdateAddress_output_pojo.class);
		String message = updateAddress_output_pojo.getMessage();
		Assert.assertEquals(statusCode1, 200, "verify response");
		Assert.assertEquals(message, "Address updated successfully", "verify message");
	}

	@Test(priority = 3)
	public void deleteAddress() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

		Delete_input_pojo delete_input_pojo = new Delete_input_pojo(value);
		addBody(delete_input_pojo);

		Response response = requestType("DELETE", Endpoints.DELETEADDRESS);

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Delete_Output_pojo delete_Output_pojo = response.as(Delete_Output_pojo.class);
		String message = delete_Output_pojo.getMessage();
		Assert.assertEquals(message, "Address deleted successfully", "Verify Deleted message");
		Assert.assertEquals(statusCode, 200, "Verify response");
	}

	@Test(priority = 4)
	public void getAddress() {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		h.add(h1);
		h.add(h2);
		addHeaders(h);

		Response response = requestType("GET", Endpoints.GETADDRESS);

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		GetAddress_Output_Pojo getAddress_Output_Pojo = response.as(GetAddress_Output_Pojo.class);
		String message = getAddress_Output_Pojo.getMessage();
		Assert.assertEquals(message, "OK", "Verify Deleted message");
		Assert.assertEquals(statusCode, 200, "Verify response");
	}
	
	@Test(priority=5)
	public void changeProfilePic() throws FileNotFoundException, IOException {
		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);
		
		formdata("file");
		
		Response response = requestType("POST", Endpoints.CHANGEPROFILEPIC);
		ChangeProfilePic changeProfilePic = response.as(ChangeProfilePic.class);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String message = changeProfilePic.getMessage();
		
		Assert.assertEquals(statusCode, 200, "Verify response");
		Assert.assertEquals(message, "Profile updated Successfully", "Verify Profile updated Successfully");
		
	}

}
