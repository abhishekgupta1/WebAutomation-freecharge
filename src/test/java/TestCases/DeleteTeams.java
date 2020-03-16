package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class DeleteTeams {



    @Test
    public void deleteTeamId() {

        int teamid = 15410;

        RestAssured.baseURI = Constants.BASEURI;
        RequestSpecification request = RestAssured.given();

        // Add a header stating the Request body is a JSON

        request.header("Authorization", Constants.Authorization_Token);

        // Delete the request and check the response
        Response response = request.delete("/delete/"+ teamid);

        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 204);

    }
}

