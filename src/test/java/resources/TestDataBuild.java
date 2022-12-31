package resources;

import pojoclasses.AddPlace;
import pojoclasses.Location;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(){

        AddPlace addPlace = new AddPlace(63, "Mahmut", "1233346483"
                , "istanbul/basaksehir", "www.xyz.com", "english"
                , new Location(-38.2535, 63.456), new ArrayList<>(Arrays.asList("shoe park", "shop", "shake")));

        return addPlace;
    }
}
