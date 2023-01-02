Feature: Validating Place API's
@AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created that points to "<name>" using "getPlaceAPI"

   Examples:
      | name    | language | address       |
      | george  | french   | crossAVM      |
      #| Michael | spanish  | newyorkcenter |
@DeletePlace
  Scenario:Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
