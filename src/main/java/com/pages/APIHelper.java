package com.pages;

import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;



public class APIHelper {
	
	public static boolean getresponse() {
		boolean flag;
		String endPointURL = "https://testapplication-1e725.firebaseio.com/title_new_task.json?auth=KFeulKpmMvKvL87UG4SUK0y0dTFZDCpjU6cqHilt";
				
		Response response = RestAssured.given().when().get(endPointURL);//post(endPointURL);
		

		String json = response.asString();

		Assert.assertTrue(response.getStatusCode() == 200,
				"Response code:" + response.getStatusCode() + "Message: " + json);		
		System.out.println("json: "+json);
		
		if(json.contains("Test")){
		flag=true;
		}
		else{
			flag=false;
		}

		return flag;
	}

}
