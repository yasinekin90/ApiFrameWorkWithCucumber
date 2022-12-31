package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Utilities {
    RequestSpecification req;
    public RequestSpecification requestSpecifications() throws FileNotFoundException {

        PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
         req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                 .addFilter(RequestLoggingFilter.logRequestTo(log)) //log the request in logging.txt file
                 .addFilter(ResponseLoggingFilter.logResponseTo(log)) //log the response in logging.txt file
                .setContentType(ContentType.JSON).build();

         return req;
    }

}
