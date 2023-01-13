Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if Place is being Sucessfully added using AddPlaceAPI
     Given Add Place Payload with "<name>" "<language>" "<address>"
     When user calls "addPlaceAPI" with "Post" http request
     Then the API call got sucess with status code 200
     And "status" in response body is "OK"
     And "scope" in response body is "APP"
     And verify place_Id created maps to "<name>" using "getPlaceAPI"
     
 
 Examples:
 |name   |language|address    |
 |AAhouse| Hindi  |Hindu Center| 
# |BBhouse|English |Sea Center  | 

@DeletePlace
Scenario: verify if Delete place functionality is working 

		Given DeletePlace Payload
		When user calls "deletePlaceAPI" with "Post" http request	
		 Then the API call got sucess with status code 200
		 And "status" in response body is "OK"
		 
		 
		
		
		
		
		
																																											   