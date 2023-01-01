package stepdefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        //write a code that will give you place id
        //execute this code when place_id is null
        StepDefinitions definitions=new StepDefinitions();
        if(StepDefinitions.place_id==null){
            definitions.add_place_payload_with("Daniel","Spanish","Zaragoza");
            definitions.user_calls_with_http_request("addPlaceAPI","POST");
            definitions.verify_place_id_created_that_points_to_using("Daniel","getPlaceAPI");
        }


    }
}
