package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoclasses.AddPlace;
import pojoclasses.Location;
import resources.TestDataBuild;
import resources.Utilities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utilities {


    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild dataBuild = new TestDataBuild();

    @Given("Add Place Payload")
    public void add_place_payload() throws FileNotFoundException {

        res = given()
                .spec(requestSpecifications())
                .body(dataBuild.addPlacePayLoad());
    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String api) {
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = res
                .when().post(api)
                .then().spec(resSpec).extract().response();
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode);

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        assertEquals(jsonPath.get(key).toString(), value);

    }
}
