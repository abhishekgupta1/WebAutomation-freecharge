package newTestCases;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.jayway.jsonpath.JsonPath;
import constants.StartEmu;

import helpers.PropertyOperations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class getRequest  {

	PropertyOperations po = new PropertyOperations();
	static Logger log = Logger.getLogger(getRequest.class.getName());
	ExtentTest ParentTest;
	ExtentTest ChildTest;
	
	@BeforeClass
	public void SetUp() {
		PropertyConfigurator.configure("log4j.properties");
		ParentTest = StartEmu.extent.createTest("Cricket Score Card Commentary" + " Test Cases");
		log.debug("Log4j appender configuration is successful !!");
	}

	@BeforeMethod
	public void ForResult(ITestResult testResult) {
		ChildTest = ParentTest.createNode(testResult.getMethod().getDescription());
	}

	@AfterMethod
	public void CheckResult(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			ChildTest.log(Status.INFO, MarkupHelper.createLabel("Test Failed due to ", ExtentColor.RED));
			ChildTest.log(Status.FAIL, testResult.getThrowable());
			ChildTest.log(Status.INFO,
					MarkupHelper.createLabel("screen shot for the failed test case", ExtentColor.RED));
			}
		 else if (testResult.getStatus() == ITestResult.SUCCESS) {
			ChildTest.log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
			ChildTest.log(Status.INFO,
					MarkupHelper.createLabel("screen shot for the passed test case", ExtentColor.GREEN));
			
			
		} else if (testResult.getStatus() == ITestResult.SKIP) {
			ChildTest.log(Status.SKIP, testResult.getThrowable());
			ChildTest.log(Status.PASS, MarkupHelper.createLabel("Test Skipped", ExtentColor.YELLOW));
			ChildTest.log(Status.INFO,
					MarkupHelper.createLabel("screen shot for the Skipped test case", ExtentColor.GREEN));
			
		}
	}
	
	
	@Test(priority=1,description ="get Ok Request")
	public void getOkRequest(){
	
		try {
			RestAssured.baseURI = po.readFromProps("apiBaseUrl", "api.properties" );
		} catch (IOException e) {}
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("api/users?page=2");
		ResponseBody body = response.getBody();
		System.out.println("Response Body is: " + body.asString());
		System.out.println(response.statusCode());
		log.debug("Hello this is a debug message");
	      log.info("Hello this is an info message");
	}
	
	@Test(priority=2,description ="schema Validation")
	public void schemaValidation() throws UnsupportedEncodingException, IOException {

		try {
			RestAssured.baseURI = po.readFromProps("apiBaseUrl", "api.properties" );
		} catch (IOException e) {}
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("api/users?page=2");
		ResponseBody body = response.getBody();
		System.out.println("Response Body is: " + body.asString());
		System.out.println(response.statusCode());

		String schemaFileContent = new String(Files.readAllBytes(Paths.get("src\\main\\java\\constants\\test.json")), "UTF-8");
		JSONObject rawSchema = new JSONObject(schemaFileContent);
		Schema schema = SchemaLoader.load(rawSchema);
		Object json = new JSONTokener(body.asString()).nextValue();
		if (json instanceof JSONObject) {
			try {
				schema.validate(new JSONObject(body.asString()));
				System.out.println("pass");
			} catch (ValidationException exception) {
				System.out.println("fail");
			}
		} else if (json instanceof JSONArray) {
			try {
				schema.validate(new JSONArray(body.asString()));
				System.out.println("pass2");
			} catch (ValidationException exception) {
				System.out.println("fail2");
			}
		}

	}

	@Test(priority=3,description="xpath Validation")
	public void xpathValidation() throws ParseException{

		try{
			RestAssured.baseURI = po.readFromProps("apiBaseUrl", "api.properties" );
		} catch (IOException e) {}
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("api/users?page=2");
		ResponseBody body = response.getBody();
		System.out.println(response.getTime());
		System.out.println("Response Body is: " + body.asString());
		String firstName = JsonPath.read(body.asString(), "$.data[0].email");
		System.out.println(firstName);

		
		
}
	
//	milliseconds  print and Seconds Methods that will print in extent report
	
//	
//	@Test(priority=4)
//	public void measureReponseTime(){
//	}
}
