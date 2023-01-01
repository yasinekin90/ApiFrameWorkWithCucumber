package resources;

import apis.APIResources;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utilities {
    public static RequestSpecification req;
    public RequestSpecification requestSpecifications() throws IOException {

             if(req==null){
                 PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
                 req = new RequestSpecBuilder()
                         .setBaseUri(getGlobalValue("baseUrl"))
                         .addQueryParam("key", "qaclick123")
                         .addFilter(RequestLoggingFilter.logRequestTo(log)) //log the request in logging.txt file
                         .addFilter(ResponseLoggingFilter.logResponseTo(log)) //log the response in logging.txt file
                         .setContentType(ContentType.JSON).build();

                 return req;
             }
       return req;
    }

    public String getGlobalValue(String globalValue) throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fis);

        return prop.getProperty(globalValue);
    }

    public String getJsonPath(Response response, String key){
        String resp = response.asString();
       JsonPath jsonPath = new JsonPath(resp);

       return jsonPath.get(key).toString();
    }

    public Response getResponse(String method, RequestSpecification res, APIResources apiResources){

        Response response;
        if (method.equalsIgnoreCase("post")) {
            response = res
                    .when().post(apiResources.getResource());
        } else if (method.equalsIgnoreCase("get")) {
            response = res
                    .when().get(apiResources.getResource());
        } else {
            response = res
                    .when().delete(apiResources.getResource());
        }

        return response;
    }

}
