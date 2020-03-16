import Constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostCreateTeam {

    @Test
    public void PostCreateTeam()
    {
        RestAssured.baseURI = Constants.BASEURI;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "SRE"); // Cast
        requestParams.put("description", "A great team");
        requestParams.put("permission", "admin");
        requestParams.put("privacy", "closed");

        request.body(requestParams.toJSONString());
        RestAssured.given(). header("Authorization", Constants.Authorization_Token);


        Response response = request.post("/orgs/ComapnyName/teams");


        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, "201");
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }

}

