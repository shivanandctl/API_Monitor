package com.monitor.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiValidator {
	
	public boolean validateApi(String apiEndpoint) {
		boolean isApiHealthy = false;
		try {
			
			String query = apiEndpoint;
			Response response;
			response = RestAssured.given().relaxedHTTPSValidation().header("Content-type", "application/json").and()
					.when().get(query).then().extract().response();
			int statCode = response.getStatusCode();
			
			if(statCode==200) {
				isApiHealthy = true;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return isApiHealthy;
	}
	
	public static void main(String[] args) {
		String apiEndpoint1 = "https://reqres.inpi/usrs/2";
		ApiValidator av = new ApiValidator();
		if (av.validateApi(apiEndpoint1)) {
			System.out.println("Api is Healthy");
		} else {
			System.out.println("Api is unHealthy");
		}
	}

	
}
