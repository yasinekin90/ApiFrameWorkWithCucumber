package resources;

import pojoclasses.AddPlace;
import pojoclasses.Location;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String name, String language, String address){

        AddPlace addPlace = new AddPlace(63, name, "1233346483"
                , address, "www.xyz.com", language
                , new Location(-38.2535, 63.456), new ArrayList<>(Arrays.asList("shoe park", "shop", "shake")));

        return addPlace;
    }
    public String deletePlacePayload(String place_id){

        return "{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}";
    }
}
