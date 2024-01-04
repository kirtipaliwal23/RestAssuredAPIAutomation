package testClasses;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class GoogleMAPAPIsTest extends Utils {
	String placeID, actualAddress;
	String newAddress ="US";
	TestDataBuild data = new TestDataBuild();
 

	@Test(priority =1,dataProvider = "add place",groups ="smoke")
	public void verifyAddPlaceAPI(int accuracy , String num) throws Exception {
		String response = given().spec(requestSpecification()).body(data.addPlacePayload("20 add","Italian","Xavier house",accuracy , num)).header("Content-Type","application/json").when()
				.post(APIResources.AddPlaceAPI.getResourceAPI()).then().spec(responseSpecification()).extract().response().asString();
		placeID= extractJson(response, "place_id");
	}
	
	@Test(priority =2)
	public void verifyUpdatePlaceAPI() throws Exception {
		 given().spec(requestSpecification()).body(data.UpdatePlace(placeID, newAddress)).header("Content-Type","application/json").when()
		.put(APIResources.UpdatePlaceAPI.getResourceAPI()).then().spec(responseSpecification()).body("msg",equalTo("Address successfully updated"));
	}
	
	@Test(priority =3)
	public void verifyGetPlaceAPI() throws Exception {
		String response2 =given().spec(requestSpecification()).queryParam("place_id", placeID).when().get(APIResources.GetPlaceAPI.getResourceAPI()).then()
				.spec(responseSpecification()).extract().response().asString();
		actualAddress = extractJson(response2, "address");
		Assert.assertEquals(newAddress,actualAddress,"Both addresses are not same");
	}
	

	@Test(priority =4,groups="delete")
	public void verifyDeletePlaceAPI() throws Exception {
		given().spec(requestSpecification()).body(data.deletePlace(placeID)).header("Content-Type","application/json").when()
		.delete(APIResources.DeletePlaceAPI.getResourceAPI()).then().spec(responseSpecification());
	}
	
	@DataProvider(name="add place")
	public Object[][] addPlaceDiffValues() {
		Object[][]obj = new Object[2][2];
		{
		obj [0][0] =50;
		obj [0][1] ="+(91)-6879283982";
		obj [1][0] =51;
		obj [1][1] ="+(91)-6779283982";
		
		}
		return obj;
	}
}
