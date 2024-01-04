package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	RequestSpecification req ;
	ResponseSpecification res;
	PrintStream log ; 
	public RequestSpecification requestSpecification() throws Exception {
	    log= new PrintStream(new FileOutputStream("logs.text",true));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURL")).addQueryParam("key","qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		return req;
	}
	
	public ResponseSpecification responseSpecification() {
		res = new ResponseSpecBuilder().expectStatusCode(200).build();
		return res;
	}
	
	public String extractJson(String response , String attribute) {
		JsonPath js = new JsonPath(response);
		String value = js.getString(attribute);
		return value;
		
	}
	public static String getGlobalValues(String k) throws Exception {
		Properties prop = new Properties();
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/global.properties");
		prop.load(f);
		String value= prop.getProperty(k);
		return value;
	}
}

