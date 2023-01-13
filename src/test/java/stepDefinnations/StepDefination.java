package stepDefinnations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	 static String place_id;
	 
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address)  throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		//REsponseSpec instance--
		//resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		 res = given().spec(requestspecification()).body(data.addPlacePayload(name,language,address));
	}
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource,String method) {
	    // Write code here that turns the phrase above into concrete actions
	//Constructor will be called with value of resource which you pass.	
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getresource());
		
		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("Post"))
		response = res.when().post(resourceAPI.getresource());
		else if (method.equalsIgnoreCase("Get"))
			response = res.when().get(resourceAPI.getresource());
		
		}
	@Then("the API call got sucess with status code {int}")
	public void the_api_call_got_sucess_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);  //need to import static assertjunit package.
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	       
	      assertEquals(getJsonPath(response, keyValue),Expectedvalue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}") 
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
	    
	    // requestSpec
	     place_id= getJsonPath(response,"place_id");  
	    res = given().spec(requestspecification()).queryParam("place_id", place_id);
	    
	    user_calls_with_http_request(resource,"GET");
	    String actualName = getJsonPath(response,"name");
	    assertEquals(actualName,expectedName);
	    
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    res = given().spec(requestspecification()).body(data.deletePlacePayload(place_id));
	}

	    
	    
	


}
