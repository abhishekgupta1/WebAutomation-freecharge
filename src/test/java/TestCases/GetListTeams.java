package TestCases;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import constants.Constants;





public class GetListTeams {


    @Test
    public void GetListTeams()
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = Constants.BASEURI;

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.Make sure you specify the resource name.
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.given(). header("Authorization", Constants.Authorization_Token);

        // Make a GET request call directly by using RequestSpecification.get() method.
        //
        Response response = httpRequest.get(Constants.ORGS_COMPANY);

        // Response.asString method will directly return the content of the body
        // as String.
        System.out.println("Response Body is =>  " + response.asString());
    }
}
