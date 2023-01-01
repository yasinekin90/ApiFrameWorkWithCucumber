package stepdefinitions;

import apis.APIResources;
import io.cucumber.java.en.*;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.TestDataBuild;
import resources.Utilities;


import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utilities {


    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
   static String place_id;
    TestDataBuild dataBuild = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        res = given()
                .spec(requestSpecifications())
                .body(dataBuild.addPlacePayLoad(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources apiResources = APIResources.valueOf(resource);

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        response = getResponse(method, res, apiResources);


    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode);

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {

        assertEquals(getJsonPath(response, key), value);
    }

    @Then("verify place_Id created that points to {string} using {string}")
    public void verify_place_id_created_that_points_to_using(String expectedNmae, String resource) throws IOException {

        place_id = getJsonPath(response, "place_id");
        res = given()
                .spec(requestSpecifications()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedNmae);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpecifications()).body(dataBuild.deletePlacePayload(place_id));
    }
}
